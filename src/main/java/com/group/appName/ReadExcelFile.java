package com.group.appName;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ReadExcelFile {

    public static List<Fires> readExcelFile(String filePath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet("Таблица по выездам");
        int lastRowIndex = sheet.getLastRowNum();
        List<Fires> listFires = ReadRows.readRows(lastRowIndex, sheet);

        // Close WorkBook
        workbook.close();

        return listFires;
    }
}
