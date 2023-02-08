package com.NPU.json;

import com.NPU.json.bean.Person;
import com.NPU.json.bean.PersonInfo;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonToExcel {
    static int totalSize = 250000;

    static SXSSFWorkbook wb;

    static FileOutputStream fos;

    private static final Class<?> clazz = Person.class;

    private static String pathName = null;

    private static final String readFilePath = "C:\\Download\\json\\person_info.json";

    private static void close() throws IOException {
        if (fos != null) {
            fos.flush();
            fos.close();
        }
    }

    private static List<JSONObject> beanToJson(List<Person> fileContent) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        fileContent.forEach(list -> {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(list));
            jsonObjects.add(jsonObject);
        });
        return jsonObjects;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("INFO: 报告导出功能-开始导出报告");


        if (pathName == null) {
            System.err.println("Error: 报告导出模块-未输入导出文件路径的名称，默认指定为D盘");
            pathName = "D:\\" + +System.currentTimeMillis() + " Person.xlsx";
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(readFilePath));

        String line = bufferedReader.readLine();
        List<Person> list = new ArrayList<>();
        int count = 0;
        while (line != null) {
            Person person = null;
            try {
                person = JSONObject.parseObject(line, Person.class);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(line);
            }
            if (person != null) {
                count++;
                list.add(person);
                System.out.println("INFO: 报告导出模块-excel内容已加载：" + count / (totalSize / 100) + "%");
            }

            line = bufferedReader.readLine();
        }

        System.out.println("INFO: 报告导出模块-开始向报告中填入参数");
        System.out.println("INFO: 报告导出功能-已完成报告内容的填入");
        System.out.println("INFO: 报告导出功能-开始导出报告");

//        writeExcelByPOI(list);
        writeExcelByEasyExcel(list);
    }

    private static void writeExcelByEasyExcel(List<Person> list) {
        List<PersonInfo> personInfoList = new ArrayList<>();
        for (Person p : list) {
            if (p.get_source() != null) {
                personInfoList.add(p.get_source());
            }
        }
        // 写法3
        // 这里 需要指定写用哪个class去写
        try (ExcelWriter excelWriter = EasyExcel.write(pathName, PersonInfo.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(personInfoList, writeSheet);
        }
    }

    private static void writeExcelByPOI(List<Person> list) throws IOException {
        // 创建文件输出流
        fos = new FileOutputStream(pathName);

        wb = new SXSSFWorkbook(100);
        SXSSFSheet sheet = wb.createSheet("Sheet1");
        SXSSFRow row = sheet.createRow(0);

        // 给单元格设置样式
        CellStyle cellStyle = wb.createCellStyle();
        // 设置字体样式
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        cellStyle.setFont(font);
        // 设置单元格的背景颜色
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 设置单元格填充样式
//        cellStyle.setFillPattern(IndexedColors.WHITE.getIndex());
        List<String> strFields = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            SXSSFCell cell = row.createCell(j);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(fields[j].getName());
            strFields.add(fields[j].getName());
            cell.setCellStyle(cellStyle);
        }


        List<JSONObject> jsonObjects = beanToJson(list);

        if (jsonObjects.size() != 0) {
            int total = jsonObjects.size();

            for (int j = 1; j <= jsonObjects.size(); j++) {

                if (total >= 100 && j % (total / 100) == 0) {
                    System.out.println("INFO: 报告导出功能-程序已导出excel内容：完成百分比：" + j / (total / 100));
                }
                SXSSFRow dataRow = sheet.createRow(j);
                for (int k = 0; k < strFields.size(); k++) {
                    SXSSFCell dataCell = dataRow.createCell(k);
                    dataCell.setCellType(CellType.STRING);
                    dataCell.setCellValue(jsonObjects.get(j - 1).get(strFields.get(k)).toString());
                }
            }
            wb.write(fos);
            wb.close();
            System.out.println("INFO: 报告导出功能-文件路径：" + pathName + "文件生成成功");
        }
        close();
    }
}
