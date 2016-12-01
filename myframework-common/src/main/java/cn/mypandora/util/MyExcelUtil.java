/**
 * Copyright © 2015.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Excel工具类
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class MyExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(MyExcelUtil.class);

    /**
     * 扫描Excel第一行的Title
     *
     * @param file  Excel文件对象
     * @param inputStream Excel inputStream对象
     * @param sheetName 指定的sheet名称，没有时默认取第一个。
     * @return
     */
    public static List<String> scanExcelTitles(File file, InputStream inputStream, String... sheetName) {
        List<String> titles = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file == null ? inputStream : new FileInputStream(file));
            Sheet sheet = sheetName.length == 0 ? workbook.getSheetAt(0) : workbook.getSheet(sheetName[0]);
            Row row = sheet.getRow(0);
            if (row != null) {
                int i = 0;
                while (true) {
                    Cell cell = row.getCell(i);
                    if (cell == null || StringUtils.isBlank(cell.getStringCellValue())) {
                        break;
                    }
                    titles.add(cell.getStringCellValue());
                    i++;
                }
            }
        } catch (Exception e) {
            logger.debug("Scan Excel Error");
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return titles;
    }

    /**
     * 读取Excel文件 内容以List<Map<String K,String V>>的方式存放
     *
     * @param file  Excel文件对象
     * @param inputStream Excel inputStream对象
     * @param fieldNames Map的Key列表，Value为相应的sheet一行中各列的值
     * @param sheetName  用于指定所需读取数据的表
     * @return
     */
    public static List<Map<String, String>> readExcelToMap(File file, InputStream inputStream, String fieldNames, String... sheetName) {
        List<Map<String, String>> list = Collections.EMPTY_LIST;

        try {
            Workbook workbook = WorkbookFactory.create(file == null ? inputStream : new FileInputStream(file));
            list = execRead(workbook, fieldNames, sheetName);
        } catch (Exception e) {
            logger.error("导入表格出错，信息:" + e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    /**
     * @param workbook 工作薄对象
     * @param fieldNames 要读取的列
     * @param sheetName 要操作的工作表，没有时取默认第一个
     * @return
     */
    private static List<Map<String, String>> execRead(Workbook workbook, String fieldNames, String... sheetName) {
        String[] strKey = fieldNames.split(",");
        List<Map<String, String>> listMap = new ArrayList<>();
        int i = 1;
        try {
            Sheet sheet = sheetName.length == 0 ? workbook.getSheetAt(0) : workbook.getSheet(sheetName[0]);
            while (true) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    break;
                }
                Map<String, String> map = new LinkedHashMap<String, String>();
                map.put("rowid", String.valueOf(row.getRowNum()));
                for (int keyIndex = 0; keyIndex < strKey.length; keyIndex++) {
                    Cell cell;
                    cell = row.getCell(keyIndex);
                    String cellValue = "";
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC: {
                                // 判断当前的cell是否为Date
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    // 如果是Date类型则，取得该Cell的Date值
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                                }
                                // 如果是纯数字
                                else {
                                    // 取得当前Cell的数值
                                    Integer num = new Integer((int) cell.getNumericCellValue());
                                    cellValue = String.valueOf(num);
                                }
                                break;
                            }
                            case Cell.CELL_TYPE_STRING:
                                cellValue = cell.getRichStringCellValue().getString();
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                System.out.println(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                System.out.println(cell.getCellFormula());
                                break;
                            default:
                                cellValue = " ";
                        }
                    }
                    map.put(strKey[keyIndex], cellValue);
                }
                listMap.add(map);
                i++;
            }
        } catch (Exception e) {
            logger.debug("导入中断，错误位置：第" + i + "行数据！");
            throw new RuntimeException(e);
        }
        return listMap;
    }

    /**
     * 导出Excel表格, 该表格只有第一行标题有内容，其它为空。
     *
     * @param filePath    文档保存路径
     * @param sheetTitle  Sheet的名称
     * @param fieldTitles Sheet各列的标题（第一行各列的名称）
     */
    public static void writeExcel(String filePath, String sheetTitle, String fieldTitles) {
        Workbook[] wbs = new Workbook[]{new HSSFWorkbook(), new XSSFWorkbook()};
        for (int i = 0; i < wbs.length; i++) {
            Workbook wb = wbs[i];
            // 创建Excel工作簿的第一个Sheet页
            Sheet sheet = wb.createSheet();
            wb.setSheetName(0, sheetTitle);

            // 创建Sheet页的文件头（第一行）
            createTitle(sheet, fieldTitles);

            // 保存Excel文件
            saveExcelFile(wb, filePath);
        }
    }

    /**
     * 导出Excel文件 数据源的数据格式为List<Map<String K,String V>>
     *
     * @param filePath    文档保存路径
     * @param sheetTitle  Sheet的名称
     * @param fieldTitles Sheet各列的标题（第一行各列的名称）
     * @param objList     数据源
     * @param fieldNames  各列对应objClass中field的名称
     */
    public static void writeExcel(String filePath, String sheetTitle, String fieldTitles, List<Map<String, String>> objList, String fieldNames) {
        Workbook[] wbs = new Workbook[]{new HSSFWorkbook(), new XSSFWorkbook()};
        for (int j = 0; j < wbs.length; j++) {
            Workbook workbook = wbs[j];
            CreationHelper creationHelper = workbook.getCreationHelper();

            // 创建Excel工作簿的第一个Sheet页
            Sheet sheet = workbook.createSheet(sheetTitle);
            workbook.setSheetName(0, sheetTitle);

            // 创建Sheet页的文件头（第一行）
            createTitle(sheet, fieldTitles);

            // 创建Sheet页的文件体（后续行）
            String[] strArray = fieldNames.split(",");
            for (int objIndex = 0; objIndex < objList.size(); objIndex++) {
                Map<String, String> map = objList.get(objIndex);
                Row row = sheet.createRow(objIndex + 1);
                for (int cellNum = 0; cellNum < strArray.length; cellNum++) {
                    Cell cell = row.createCell(cellNum);
                    cell.setCellType(CellType.STRING);
                    if (map.get(strArray[cellNum]) != null)
                        cell.setCellValue(map.get(strArray[cellNum]).toString());
                    else {
                        cell.setCellValue("");
                    }
                }
            }

            // 保存Excel文件
            saveExcelFile(workbook, filePath);
        }

    }

    /**
     * 导出Excle文档
     *
     * @param filePath    文档保存路径
     * @param sheetTitle  Sheet的名称
     * @param fieldTitles Sheet各列的标题（第一行各列的名称）
     * @param objList     数据源
     * @param objClass    数据源中的数据类型
     * @param fieldNames  各列对应objClass中field的名称
     */
    public static void writeExcel(String filePath, String sheetTitle, String fieldTitles, List<?> objList, Class<?> objClass, String fieldNames) {
        Workbook[] wbs = new Workbook[]{new HSSFWorkbook(), new XSSFWorkbook()};
        for (int j = 0; j < wbs.length; j++) {
            Workbook workbook = wbs[j];
            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet();
            workbook.setSheetName(0, sheetTitle);

            createTitle(sheet, fieldTitles);
            createBody(sheet, objList, objClass, fieldNames);
            // 保存Excel文件
            saveExcelFile(workbook, filePath);
        }

    }

    /**
     * 创建Excel当前sheet页的头信息
     *
     * @param sheet       Excel工作簿的一个sheet
     * @param fieldTitles sheet头信息列表(sheet第一行各列值)
     */
    private static void createTitle(Sheet sheet, String fieldTitles) {
        Row row = sheet.createRow(0);
        Cell cell;

        String[] strArray = fieldTitles.split(",");
        for (int i = 0; i < strArray.length; i++) {
            cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(strArray[i]);
        }
    }

    /**
     * 创建Excel当前sheet页的内容
     *
     * @param sheet      工作簿的sheet页
     * @param objList    数据源
     * @param objClass   数据源中的数据类型
     * @param fieldNames 各列对应objClass中field的名称
     */
    private static void createBody(Sheet sheet, List<?> objList, Class<?> objClass, String fieldNames) {
        String[] targetMethod = fieldNames.split(",");
        Method[] ms = objClass.getMethods();
        Pattern pattern = Pattern.compile("^get.*");

        // 循环objList对象列表（生成sheet的行）
        for (int objIndex = 0; objIndex < objList.size(); objIndex++) {
            Object obj = objList.get(objIndex);
            Row row = sheet.createRow(objIndex + 1);
            // 循环strBody目标方法数组（生成sheet的列）
            for (int strIndex = 0; strIndex < targetMethod.length; strIndex++) {
                String targetMethodName = targetMethod[strIndex];
                // 循环ms方法数组，找到目标方法（strBody中指定的方法）并调用
                for (int i = 0; i < ms.length; i++) {
                    Method srcMethod = ms[i];
                    if (pattern.matcher(srcMethod.getName()).matches()) {
                        int len = targetMethodName.indexOf(".") < 0 ? targetMethodName.length() : targetMethodName.indexOf(".");
                        if (srcMethod.getName().equals(("get" + String.valueOf(targetMethodName.substring(0, len).charAt(0)).toUpperCase() + targetMethodName.substring(1, len)))) {
                            Cell cell = row.createCell(strIndex);
                            cell.setCellType(CellType.STRING);
                            try {
                                // 如果方法返回一个引用类型的值
                                if (targetMethodName.contains(".")) {
                                    cell.setCellValue(referenceInvoke(targetMethodName, obj));
                                    // 如果方法返回一个普通属性
                                } else {
                                    cell.setCellValue((srcMethod.invoke(obj)).toString());
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * 保存Excel文件
     *
     * @param workbook   Excel工作簿
     * @param outputPath 文件保存路径
     */
    private static void saveExcelFile(Workbook workbook, String outputPath) {
        try {
            FileOutputStream fos = new FileOutputStream(outputPath);
            workbook.write(fos);

            fos.flush();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 方法返回的是一个对象的引用（如：getHomeplace.getName类型的方法序列）
     * 按方法序列逐层调用直到最后放回基本类型的值
     *
     * @param targetMethod 对象所包含的方法列
     * @param obj          待处理的对象
     * @return
     */
    private static String referenceInvoke(String targetMethod, Object obj) {
        // 截取方法序列的第一个方法(即截取属于obj对象的方法：getHomeplace())
        String refMethod = targetMethod.substring(0, targetMethod.indexOf("."));
        // 获得后续方法序列(getName())
        targetMethod = targetMethod.substring(targetMethod.indexOf(".") + 1);
        try {
            // 获得第一个方法的执行结果(即obj方法执行的结果：obj.getHomeplace())
            obj = obj.getClass().getMethod("get" + String.valueOf(refMethod.charAt(0)).toUpperCase() + refMethod.substring(1)).invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果方法序列没到最后一节
        if (targetMethod.contains(".")) {
            return referenceInvoke(targetMethod, obj);
            // 如果方法序列到达最后一节
        } else {
            try {
                // 通过obj对象获得该方法链的最后一个方法并调用
                Method tarMethod = obj.getClass().getMethod("get" + String.valueOf(targetMethod.charAt(0)).toUpperCase() + targetMethod.substring(1));
                return tarMethod.invoke(obj).toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        // 读取测试
        String fileName = "C:\\Users\\JUSFOUN\\Desktop\\10.xlsx";
        List<String> titles = scanExcelTitles(new File(fileName), null);
        List<Map<String, String>> listMap = readExcelToMap(new File(fileName), null, StringUtils.join(titles, ','), "Sheet1");

        // 生成测试
        writeExcel("C:\\Users\\JUSFOUN\\Desktop\\12.xlsx", "test1", "name,leader", listMap, StringUtils.join(titles, ','));

        String abc="1:00   02:23";
        System.out.println(abc.split("").length);
    }
}
