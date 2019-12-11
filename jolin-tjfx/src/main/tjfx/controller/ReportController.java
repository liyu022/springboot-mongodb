package com.hdsx.lwgl.tjfx.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.hdsx.lwgl.statanalysis.entity.ExperienceData;
import com.hdsx.lwgl.statanalysis.entity.ResumeData;
import com.hdsx.lwgl.statanalysis.service.ITrfficFlowService;
import com.hdsx.lwgl.statanalysis.service.IWscltjService;
import com.hdsx.lwgl.statanalysis.service.IYdpmService;
import com.hdsx.lwgl.statanalysis.util.excel.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
@Api(description = "报告生成服务")
public class ReportController {

    @Autowired
    private IYdpmService ydpmService;

    @Autowired
    private ITrfficFlowService trfficFlowService;

    @Autowired
    private IWscltjService wscltjService;


    @ApiOperation(value = "生成word文档")
    @GetMapping("/wordReport")
    public static void init() throws IOException {
        ResumeData datas = new ResumeData();
        datas.setPortrait(new PictureRenderData(100, 100, "src/test/resources/sayi.png"));
        datas.setName("卅一");
        datas.setJob("BUG工程师");
        datas.setPhone("18080809090");
        datas.setSex("男");
        datas.setProvince("杭州");
        datas.setBirthday("2000.08.19");
        datas.setEmail("adasai90@gmail.com");
        datas.setAddress("浙江省杭州市西湖区古荡1号");
        datas.setEnglish("CET6 620");
        datas.setUniversity("美国斯坦福大学");
        datas.setProfession("生命工程");
        datas.setEducation("学士");
        datas.setRank("班级排名 1/36");
        datas.setHobbies("音乐、画画、乒乓球、旅游、读书\nhttps://github.com/Sayi");

        // 技术栈部分
        TextRenderData textRenderData = new TextRenderData("SpringBoot、SprigCloud、Mybatis");
        Style style = new Style();
        style.setFontSize(10);
        style.setColor("7F7F7F");
        style.setFontFamily("微软雅黑");
        textRenderData.setStyle(style);
        datas.setStack(new NumbericRenderData(Arrays.asList(textRenderData, textRenderData, textRenderData)));

        // 模板文档循环合并
        List<ExperienceData> experiences = new ArrayList<ExperienceData>();
        ExperienceData data0 = new ExperienceData();
        data0.setCompany("香港微软");
        data0.setDepartment("Office 事业部");
        data0.setTime("2001.07-2020.06");
        data0.setPosition("BUG工程师");
        textRenderData = new TextRenderData("负责生产BUG，然后修复BUG，同时有效实施招聘行为");
        textRenderData.setStyle(style);
        data0.setResponsibility(new NumbericRenderData(Arrays.asList(textRenderData, textRenderData)));
        ExperienceData data1 = new ExperienceData();
        data1.setCompany("自由职业");
        data1.setDepartment("OpenSource 项目组");
        data1.setTime("2015.07-2020.06");
        data1.setPosition("研发工程师");
        textRenderData = new TextRenderData("开源项目的迭代和维护工作");
        textRenderData.setStyle(style);
        TextRenderData textRenderData1 = new TextRenderData("持续集成、Swagger文档等工具调研");
        textRenderData1.setStyle(style);
        data1.setResponsibility(new NumbericRenderData(Arrays.asList(textRenderData, textRenderData1, textRenderData)));
        experiences.add(data0);
        experiences.add(data1);
        experiences.add(data0);
        experiences.add(data1);
        datas.setExperience(new DocxRenderData(new File("E:\\HDSX\\idea\\lwgl2\\lwgl-statanalysis\\src\\main\\resources\\resume\\jianli_segment.docx"), experiences));

        XWPFTemplate template = XWPFTemplate.compile("E:\\HDSX\\idea\\lwgl2\\lwgl-statanalysis\\src\\main\\resources\\resume\\jianli.docx").render(datas);
        FileOutputStream out = new FileOutputStream("E:\\HDSX\\idea\\lwgl2\\lwgl-statanalysis\\src\\main\\resources\\resume\\out_简历.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }
    @ApiOperation(value = "生成excel文档")
    @GetMapping("/excelReport")
    public void initExcel(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "begintime", required = true) String begintime, @RequestParam(value = "endtime", required = true) String endtime, HttpServletResponse response) throws IOException {
        List<List<Map<String, Object>>> list = new ArrayList();
        List<Map<String, Object>> lksj = new ArrayList();
        List<Map<String, Object>> qxsj = new ArrayList();
        List<Map<String, Object>> jdsj = new ArrayList();
        List<Map<String, Object>> wsclsj = new ArrayList();
        List<Map<String, Object>> yjsjsj = new ArrayList();
        List<Map<String, Object>> yhsjsj = new ArrayList();
        List<Map<String, Object>> wxpcsj = new ArrayList();
        List<String[]> nameAry = new ArrayList<String[]>();
        List<String[]> ary = new ArrayList<String[]>();
        List<String> sheetName = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginData = new Date();
        Date endData = new Date();
        try {
            beginData = sdf.parse(begintime);
            endData = sdf.parse(endtime);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        try {
            if (type.indexOf("lk") > -1) {
                nameAry.add(new String[]{"路线名称", "路线编码", "路线总长(米)", "拥堵总长(米)"});
                ary.add(new String[]{"LXMC", "LXBM", "LEN", "CNT"});
                sheetName.add("路况数据");
                lksj = ydpmService.getExportTriffic(beginData, endData);
                list.add(lksj);
            } if (type.indexOf("qx") > -1){
                nameAry.add(new String[]{"路线名称", "路线编码", "路线总长(米)", "拥堵总长(米)"});
                ary.add(new String[]{"LXMC", "LXBM", "LEN", "CNT"});
                sheetName.add("气象数据");
                qxsj = ydpmService.getExportTriffic(beginData, endData);
                list.add(qxsj);
            } if (type.indexOf("jdsj") > -1) {
                nameAry.add(new String[]{"路线编码", "交通量观测站名称", "交通量"});
                ary.add(new String[]{"LXBM", "GCZMC", "JTL"});
                sheetName.add("交调站数据");
                jdsj = trfficFlowService.getExportTrifficFlow(beginData, endData);
                list.add(jdsj);
            } if (type.indexOf("wscl") > -1) {
                nameAry.add(new String[]{"车牌号","行政区划", "分布城市", "停留时间","时间段"});
                ary.add(new String[]{"plateNumbers","xzqh", "name", "coun", "time"});
                sheetName.add("外省车辆数据");
                wsclsj = wscltjService.getExportWscl(beginData, endData);
                list.add(wsclsj);
            } if (type.indexOf("yjsjsj") > -1) {
                nameAry.add(new String[]{"事件内容", "事件级别", "事件状态", "发生时间"});
                ary.add(new String[]{"ID", "ID", "ID", "ID"});
                sheetName.add("应急事件数据");
                yjsjsj = null;
                list.add(yjsjsj);
            } if (type.indexOf("yhsjsj") > -1) {
                nameAry.add(new String[]{"事件内容", "上报单位", "完成时间", "发生时间"});
                ary.add(new String[]{"ID", "ID", "ID", "ID"});
                sheetName.add("养护事件数据");
                yhsjsj = null;
                list.add(yhsjsj);
            } if (type.indexOf("wxpcsj") > -1) {
                nameAry.add(new String[]{"事件内容", "事件级别", "事件状态", "发生时间"});
                ary.add(new String[]{"ID", "ID", "ID", "ID"});
                sheetName.add("危险品数据");
                wxpcsj = null;
                list.add(wxpcsj);
            }
            XSSFWorkbook workbook = new XSSFWorkbook();
            response.addHeader("Content-Length", "" + list.size());
            response.setContentType("application/msexcel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename="+ java.net.URLEncoder.encode("陕西省交通运输监测中心数据.xls", "UTF-8"));
            OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
            for (int i = 0; i < sheetName.size(); i++) {
                try {
                    new ExcelUtils().exportExcel(workbook, i, sheetName.get(i), nameAry.get(i), ary.get(i),list.get(i), outputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(workbook);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
