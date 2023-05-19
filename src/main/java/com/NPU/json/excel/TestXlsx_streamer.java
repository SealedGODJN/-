package com.NPU.json.excel;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestXlsx_streamer {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream is = new FileInputStream(new File("C:/Users/HJN13/Desktop/5.xlsx"));
        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
                .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .open(is);            // InputStream or File for XLSX file (required)
        for (Sheet sheet :
                workbook) {
            System.out.println(sheet.getSheetName());
            for (Row r :
                    sheet) {
                for (Cell c :
                        r) {
                    System.out.println(c.getStringCellValue());
                }
            }
        }
    }
}
