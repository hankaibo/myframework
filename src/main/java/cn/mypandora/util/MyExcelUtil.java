/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @ClassName: MyExcelUtil
 * @Description: Excel工具类
 * @Author: kaibo
 * @date: 2014-7-5
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-7-5 下午7:41:21
 * @UpdateRemark: What is modified?
 */
/**
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class MyExcelUtil {
    // 2007 版本以上 最大支持1048576行
    public final static String EXCEl_FILE_2007 = ".xlsx";
    // 2003 版本 最大支持65536 行
    public final static String EXCEL_FILE_2003 = ".xls";
    private static final Logger logger = LoggerFactory.getLogger(MyExcelUtil.class);

    /**
     * @param excelFile
     * @param sheetName 指定的sheet名称，没有时默认取第一个。
     * @return List<String>
     * @Title: scanExcelTitles
     * @Description: 扫描Excel第一行的Title
     */
    public static List<String> scanExcelTitles(File excelFile, String... sheetName) {
        List<String> titles = new ArrayList<String>();
        try {
            String fileName = excelFile.getName();
            Workbook workbook;
            if (fileName.substring(fileName.lastIndexOf(".")).equalsIgnoreCase(EXCEl_FILE_2007)) {
                workbook = new XSSFWorkbook(new FileInputStream(excelFile));
            } else {
                workbook = new HSSFWorkbook(new FileInputStream(excelFile));
            }
            Sheet sheet;
            if (sheetName.length == 0) {
                sheet = workbook.getSheetAt(0);
            } else {
                sheet = workbook.getSheet(sheetName[0]);
            }
            Row row = sheet.getRow(0);
            if (row != null) {
                int i = 0;
                while (true) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        break;
                    }
                    titles.add(cell.getStringCellValue());
                    i++;
                }
            }
        } catch (Exception e) {
            logger.debug("Scan Excel [" + excelFile.getPath() + excelFile.getName() + "] Error");
            throw new RuntimeException(e);
        }
        return titles;
    }

    /**
     * @param excelFile  Excel文件对象
     * @param fieldNames Map的Key列表，Value为相应的sheet一行中各列的值
     * @param sheetName  用于指定所需读取数据的表
     * @return List<Map<String,String>>
     * @Title: importExcelToMap
     * @Description: 导入Excel文件 内容以List<Map<String K,String V>>的方式存放
     */
    public static List<Map<String, String>> importExcelToMap(File excelFile, String fieldNames, String... sheetName) {
        List<Map<String, String>> list = Collections.EMPTY_LIST;
        String fileName = excelFile.getName();
        Workbook workbook;
        try {
            if (fileName.substring(fileName.lastIndexOf(".")).equalsIgnoreCase(EXCEl_FILE_2007)) {
                workbook = new XSSFWorkbook(new FileInputStream(excelFile));
            } else {
                workbook = new HSSFWorkbook(new FileInputStream(excelFile));
            }
            list = executeImport(workbook, fieldNames, sheetName);
        } catch (IOException e) {
            logger.error("导入表格出错，信息:" + e);
        }
        return list;

    }

    /**
     * @param excelFile  输入流
     * @param fileName   文件名称
     * @param fieldNames Key
     * @param sheetName  sheet名称
     * @return
     */
    public static List<Map<String, String>> importExcelToMap(InputStream excelFile, String fileName, String fieldNames, String... sheetName) {
        List<Map<String, String>> list = Collections.EMPTY_LIST;
        try {
            Workbook workbook;
            if (fileName.substring(fileName.lastIndexOf(".")).equalsIgnoreCase(EXCEl_FILE_2007)) {
                workbook = new XSSFWorkbook(excelFile);
            } else {
                workbook = new HSSFWorkbook(excelFile);
            }
            list = executeImport(workbook, fieldNames, sheetName);
        } catch (Exception e) {
            logger.error("导入表格出错，信息:" + e);
        }
        return list;
    }

    /**
     *
     * @param workbook
     * @param fieldNames
     * @param sheetName
     * @return
     */
    private static List<Map<String, String>> executeImport(Workbook workbook, String fieldNames, String... sheetName) {
        String[] strKey = fieldNames.split(",");
        List<Map<String, String>> listMap = new ArrayList<>();
        int i = 1;
        try {
            Sheet sheet;
            if (sheetName.length == 0) {
                sheet = workbook.getSheetAt(0);
            } else {
                sheet = workbook.getSheet(sheetName[0]);
            }
            while (true) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    break;
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("rowid", String.valueOf(row.getRowNum()));
                for (int keyIndex = 0; keyIndex < strKey.length; keyIndex++) {
                    Cell cell = null;
                    cell = row.getCell(keyIndex);
                    String cellvalue = "";
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            // 如果当前Cell的Type为NUMERIC
                            case Cell.CELL_TYPE_NUMERIC: {
                                // 判断当前的cell是否为Date
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    // 如果是Date类型则，取得该Cell的Date值
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    cellvalue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                                }
                                // 如果是纯数字
                                else {
                                    // 取得当前Cell的数值
                                    Integer num = new Integer((int) cell.getNumericCellValue());
                                    cellvalue = String.valueOf(num);
                                }
                                break;
                            }
                            // 如果当前Cell的Type为STRIN
                            case Cell.CELL_TYPE_STRING:
                                // 取得当前的Cell字符串
                                cellvalue = cell.getRichStringCellValue().getString();
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                System.out.println(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                System.out.println(cell.getCellFormula());
                                break;
                            // 默认的Cell值
                            default:
                                cellvalue = " ";
                        }
                    }
                    map.put(strKey[keyIndex], cellvalue);
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
     * @param filepath    文档保存路径
     * @param sheetTitle  Sheet的名称
     * @param fieldTitles Sheet各列的标题（第一行各列的名称）
     * @return void
     * @Title: exportExcel
     * @Description: 导出Excel表格, 该表格只有第一行标题有内容，其它为空。
     */
    public static void exportExcel(String filepath, String sheetTitle, String fieldTitles) {
        // 创建工作簿（Excel文件）
        Workbook workbook;
        if (filepath.substring(filepath.lastIndexOf(".")).equalsIgnoreCase(EXCEl_FILE_2007)) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }

        // 创建Excel工作簿的第一个Sheet页
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(0, sheetTitle);

        // 创建Sheet页的文件头（第一行）
        createTitle(sheet, fieldTitles);

        // 保存Excel文件
        saveExcelFile(workbook, filepath);
    }

    /**
     * @param filepath    文档保存路径
     * @param sheetTitle  Sheet的名称
     * @param fieldTitles Sheet各列的标题（第一行各列的名称）
     * @param objList     数据源
     * @param fieldNames  各列对应objClass中field的名称
     * @return void
     * @Title: exportExcel
     * @Description: 导出Excel文件 数据源的数据格式为List<Map<String K,String V>>
     */
    public static void exportExcel(String filepath, String sheetTitle, String fieldTitles, List<Map<String, String>> objList, String fieldNames) {
        Workbook workbook;
        if (filepath.substring(filepath.lastIndexOf(".")).equalsIgnoreCase(EXCEl_FILE_2007)) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }

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
            for (int i = 0; i < strArray.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                if (map.get(strArray[i]) != null)
                    cell.setCellValue(map.get(strArray[i]).toString());
                else {
                    cell.setCellValue("");
                }
            }
        }

        // 保存Excel文件
        saveExcelFile(workbook, filepath);
    }

    /**
     * @param filepath    文档保存路径
     * @param sheetTitle  Sheet的名称
     * @param fieldTitles Sheet各列的标题（第一行各列的名称）
     * @param objList     数据源
     * @param objClass    数据源中的数据类型
     * @param fieldNames  各列对应objClass中field的名称
     * @return void
     * @Title: exportExcel
     * @Description: 导出Excle文档
     */
    public static void exportExcel(String filepath, String sheetTitle, String fieldTitles, List<?> objList, Class<?> objClass, String fieldNames) {
        // 初始化工作簿
        Workbook workbook;
        if (filepath.substring(filepath.lastIndexOf(".")).equalsIgnoreCase(EXCEl_FILE_2007)) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }

        Sheet sheet = workbook.createSheet();// 创建Excel工作簿的第一个Sheet页
        workbook.setSheetName(0, sheetTitle);

        createTitle(sheet, fieldTitles);// 创建Sheet页的文件头（第一行）
        createBody(sheet, objList, objClass, fieldNames);// 创建Sheet页的文件体（后续行）
        // 保存Excel文件
        saveExcelFile(workbook, filepath);
    }

    /**
     * @param sheet       Excel工作簿的一个sheet
     * @param fieldTitles sheet头信息列表(sheet第一行各列值)
     * @return void
     * @Title: createTitle
     * @Description: 创建Excel当前sheet页的头信息
     */
    private static void createTitle(Sheet sheet, String fieldTitles) {
        Row row = sheet.createRow(0); // 创建该页的一行
        Cell cell = null;

        String[] strArray = fieldTitles.split(",");
        for (int i = 0; i < strArray.length; i++) {
            cell = row.createCell(i); // 创建该行的一列
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(strArray[i]);
        }
    }

    /**
     * @param sheet      工作簿的sheet页
     * @param objList    数据源
     * @param objClass   数据源中的数据类型
     * @param fieldNames 各列对应objClass中field的名称
     * @return void
     * @Title: createBody
     * @Description: 创建Excel当前sheet页的内容
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
                            cell.setCellType(Cell.CELL_TYPE_STRING);
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
     * @param targetMethod 对象所包含的方法列
     * @param obj          待处理的对象
     * @return String
     * @Title: referenceInvoke
     * @Description: 方法返回的是一个对象的引用（如：getHomeplace.getName类型的方法序列）
     * 按方法序列逐层调用直到最后放回基本类型的值
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

    /**
     * @param workbook   Excel工作簿
     * @param outputPath 文件保存路径
     * @return void
     * @Title: saveExcelFile
     * @Description: 保存Excel文件
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

    public static void main(String[] args) {
        // scanExcelTitles(new File("石油管道.xls"), "08级");
        List<Map<String, String>> listMap = importExcelToMap(new File("石油管道.xlsx"), "姓名, 性别, 身份证号, 学号, 年级, 系部代码, 系部, 专业", "06、07级B");
    }
}
