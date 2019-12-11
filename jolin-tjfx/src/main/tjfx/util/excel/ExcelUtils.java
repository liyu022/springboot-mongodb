package com.hdsx.lwgl.statanalysis.util.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils{
    /**
     * 工具类
     *
     * @param workbook
     * @param sheetNum       第几个sheet
     * @param sheetTitle     sheet的名称
     * @param headers        表头
     * @param headersEnglish 表头对饮的字段名
     * @param result         数据
     * @param out
     * @throws Exception
     */
    public void exportExcel(XSSFWorkbook workbook, int sheetNum, String sheetTitle, String[] headers, String[] headersEnglish,
                            List<Map<String, Object>> result, OutputStream out) throws Exception {
        // 第一步，创建一个webbook，对应一个Excel以xsl为扩展名文件
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        //设置列宽度大小
        sheet.setDefaultColumnWidth((short) 30);
        //第二步， 生成表格第一行的样式和字体
        XSSFCellStyle xssfCellStyleHeader = getAndSetXSSFCellStyleHeader(workbook);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell((short) i);
            cell.setCellStyle(xssfCellStyleHeader);
            // 序号
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }
        // 第三步：遍历集合数据，产生数据行，开始插入数据
        if (result != null) {
            int index = 1;
            for (int rownum = 1; rownum < result.size(); rownum++) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (int i = 0; i < headersEnglish.length; i++) {
                    XSSFCell cell = row.createCell((int) cellIndex);
                    //当区不到数据时，说明是序号
                    String value = (result.get(rownum - 1).get(headersEnglish[i])) == null ? rownum + "" : (result.get(rownum - 1).get(headersEnglish[i])) + "";
                    cell.setCellValue(value);
                    cellIndex++;
                }
                index++;
            }
        }
        setSheet(sheet);
    }

    /**
     * 获取并设置header样式 SXSSFWorkbook
     */
    private XSSFCellStyle getAndSetXSSFCellStyleHeader(XSSFWorkbook sxssfWorkbook) {
        XSSFCellStyle xssfCellStyle = sxssfWorkbook.createCellStyle();
        Font font = sxssfWorkbook.createFont();
        // 字体大小
        font.setFontHeightInPoints((short) 15);
        // 字体粗细
        font.setBoldweight((short) 40);
        // 将字体应用到样式上面
        xssfCellStyle.setFont(font);
        // 是否自动换行
        xssfCellStyle.setWrapText(false);
        // 水平居中
        xssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        xssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return xssfCellStyle;
    }


    /**
     * 设置sheet
     */
    private void setSheet(Sheet sheet) {
        // 设置各列宽度(单位为:字符宽度的1/256)
        sheet.setColumnWidth(0, 32 * 80);//序号
        sheet.setColumnWidth(1, 32 * 160);//医院编号
        sheet.setColumnWidth(2, 32 * 250);//医院名称
        sheet.setColumnWidth(3, 32 * 160);//所属区县
        sheet.setColumnWidth(4, 32 * 160);//区县代码
        sheet.setColumnWidth(5, 32 * 160);//医院等级
        sheet.setColumnWidth(6, 32 * 160);//上报病案数
        sheet.setColumnWidth(7, 32 * 160);//填报中
        sheet.setColumnWidth(8, 32 * 200);//上报完成率（%）
    }


    //测试单个或多个sheet页同时导出
    public static void main(String[] args) {
        //单个sheet页导出
        Map<String, String> result = new HashMap<String, String>();
        //获取桌面路径,用于下载文件
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory();
        String excelExportDestfilepath = com + "/陕西省交通运行监测中心.xlsx";
        try {
            OutputStream out = new FileOutputStream(excelExportDestfilepath);
            //表头
            String[] nameAry = {"序号", "医院编号"};
            //表头对应的字段
            String[] ary = {"xh", "ID"};
            // 要导出的数据
            Map<String, Object> mapData= new HashMap<>();
            mapData.put("xh","123");
            mapData.put("ID","liyiyu");
            List<Map<String, Object>> list = new ArrayList();
            list.add(mapData);
            //把城市的名称放到数组中，用作不同sheet的名称
            String[] cityName = {"第一季度", "第二季度", "第三季度", "第四季度"};
            //3、生成格式是xlsx可存储103万行数据，如果是xls则只能存不到6万行数据
            XSSFWorkbook workbook = new XSSFWorkbook();
            for (int i = 0; i < cityName.length; i++) {
                new ExcelUtils().exportExcel(workbook, i, cityName[i], nameAry, ary,list, out);
            }
            //将所有的数据一起写入，然后再关闭输入流。
            workbook.write(out);
            result.put("data", "200");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("data", "300");
        }
    }
}