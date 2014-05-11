/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.util 
 * @ClassName: MyFileConversionUtil 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-7 下午2:43:33 
 *
 */
package cn.mypandora.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * @ClassName: MyFileConversionUtil
 * @Description: 文件转换工具类:包括word、excel、ppt转pdf;pdf转swf。
 * @Author: kaibo
 * @date: 2014-5-7
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-7 下午2:43:33
 * @UpdateRemark: What is modified?
 */
public class MyFileConversionUtil {
    private static final Logger logger = LoggerFactory.getLogger(MyFileConversionUtil.class);

    static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
    static final int wdFormatPDF = 17;// PDF 格式
    static final int ppSaveAsPDF = 32;// ppt转PDF格式

    // SWFTOOLS的环境安装路径
    public static String SWFTOOLS_PATH = "D:" + File.separator + "Program Files" + File.separator + "SWFTools"
            + File.separator;
    // 播放器样式文件rfxview.swf的路径
    public static String RFXVIEW_SWF_PATH = "D:" + File.separator + "Program Files" + File.separator + "SWFTools"
            + File.separator + "rfxview.swf";

    /**
     * @Title: word2pdf
     * @Description: 将word转换为pdf文件。
     * @return void
     */
    public static void word2pdf(String source, String target) {
        System.out.println("启动Word...");
        ActiveXComponent app = null;
        try {
            // 1、打开Word应用程序并设置word不可见
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);

            // 2、调用Application对象的Documents属性，获得Documents对象
            Dispatch docs = app.getProperty("Documents").toDispatch();

            // 3.1、打开文件
            System.out.println("打开文档..." + source);
            Dispatch doc = Dispatch.call(docs,//
                    "Open", // 调用Documents对象的Open方法
                    source,// 输入文件路径全名
                    false,// ConfirmConversions，设置为false表示不显示转换框
                    true // ReadOnly
                    ).toDispatch();

            System.out.println("转换文档到PDF..." + target);
            File tofile = new File(target);
            if (tofile.exists()) {
                tofile.delete();
            }
            // 3.2、执行转换
            Dispatch.call(doc,//
                    "SaveAs", //
                    target, // 要保存的PDF文件名
                    wdFormatPDF// 转换后的文件格式宏，值为17，可查询MSDN得到
            );

            // 4.1、关闭打开的Word文件
            Dispatch.call(doc, "Close", false);
        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            if (app != null)
                // 4.2、关闭Word应用程序
                app.invoke("Quit", wdDoNotSaveChanges);
        }
    }

    /**
     * @Title: ppt2pdf
     * @Description: 将ppt转换为pdf文件。
     * @param source
     * @param target
     * @return void
     */
    private static void ppt2pdf(String source, String target) {
        logger.info("启动ppt...");
        ActiveXComponent app = null;
        try {
            // 1、打开PPT应用程序
            app = new ActiveXComponent("Powerpoint.Application");

            // 2、调用Application对象的Presentations属性，获得Presentations对象
            Dispatch presentations = app.getProperty("Presentations").toDispatch();

            // 3.1、打开文件
            System.out.println("打开文档..." + source);
            Dispatch presentation = Dispatch.call(presentations,//
                    "Open", // 调用Documents对象的Open方法
                    source,// 输入文件路径全名
                    true, // ReadOnly
                    true, // Untitled 指定文件是否有标题
                    false // WithWindow 指定文件是否可见。
                    ).toDispatch();

            logger.info("转换文档到PDF..." + target);
            File tofile = new File(target);
            if (tofile.exists()) {
                tofile.delete();
            }
            // 3.2、执行转换
            Dispatch.call(presentation,//
                    "SaveAs", //
                    target, // 要保存的PDF文件名
                    ppSaveAsPDF // 转换后的文件格式宏，值为32，可查询MSDN得到
            );

            // 4.1、关闭打开的Word文件
            Dispatch.call(presentation, "Close");
        } catch (Exception e) {
            logger.info("========Error:文档转换失败：" + e.getMessage());
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            // 4.2、关闭Word应用程序
            if (app != null) {
                app.invoke("Quit");
            }
        }
    }

    /**
     * @Title: excel2pdf
     * @Description: 将Excle转换成pdf文件。
     * @param source
     * @param target
     * @return void
     */
    public static void excel2pdf(String source, String target) {
        logger.info("启动Excel...");
        ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动excel(Excel.Application)
        try {
            app.setProperty("Visible", false);

            Dispatch workbooks = app.getProperty("Workbooks").toDispatch();

            System.out.println("打开文档" + source);
            Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method,
                    new Object[] { source, new Variant(false), new Variant(false) }, new int[3]).toDispatch();
            Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] { target, new Variant(57),
                    new Variant(false), new Variant(57), new Variant(57), new Variant(false), new Variant(true),
                    new Variant(57), new Variant(true), new Variant(true), new Variant(true) }, new int[1]);
            Variant f = new Variant(false);
            logger.info("转换文档到PDF " + target);

            Dispatch.call(workbook, "Close", f);
        } catch (Exception e) {
            logger.debug("========Error:文档转换失败：" + e.getMessage());
        } finally {
            if (app != null) {
                app.invoke("Quit", new Variant[] {});
            }
        }
    }

    public static int  pdf2swf(String sourcePath, String target, String fileName) throws IOException {
        // 目标路径不存在则建立目标路径
        File dest = new File(target);
        if (!dest.exists()) {
            dest.mkdirs();
        }

        // 源文件不存在则返回
        File source = new File(sourcePath);
        if (!source.exists()) {
            return 0;
        }

        String[] envp = new String[1];
        envp[0] = "PATH=" + SWFTOOLS_PATH;
        // 调用pdf2swf命令进行转换
        String command = "cmd /c \"" + SWFTOOLS_PATH + "pdf2swf\" -z -s flashversion=9 " + sourcePath + " -o " + target
                + fileName;
        System.out.println("cmd:" + command);
        Process process = Runtime.getRuntime().exec(command, envp); // 调用外部程序
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (br.readLine() != null) {
        }
        try {
            process.waitFor();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        // 套播放器
        command = "cmd /c \"" + SWFTOOLS_PATH + "swfcombine\" " + RFXVIEW_SWF_PATH + " viewport=" + target + fileName
                + " -o " + target + fileName;
        process = Runtime.getRuntime().exec(command, envp);
        br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (br.readLine() != null) {
        }
        try {
            process.waitFor();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        return process.exitValue();

    }

    public static void main(String[] args) {
        String source1 = "C:/Users/kaibo/Documents/word2pdf.docx";
        String source2 = "C:/Users/kaibo/Documents/ppt2pdf.pptx";
        String source3 = "C:/Users/kaibo/Documents/excel2pdf.xlsx";
        String target1 = "C:/Users/kaibo/Documents/word2pdf.pdf";
        String target2 = "C:/Users/kaibo/Documents/ppt2pdf.pdf";
        String target3 = "C:/Users/kaibo/Documents/excel2pdf.pdf";
        // word2pdf();
        // ppt2pdf(source2, target2);
        // excel2pdf(source3, target3);

        String destPath = "D:\\";
        String fileName = "Javssa.swf";
        try {
            MyFileConversionUtil.pdf2swf(target3, destPath, fileName);
        } catch (Exception ex) {
            System.out.println("error");
        }
        System.out.println("success");
    }
}
