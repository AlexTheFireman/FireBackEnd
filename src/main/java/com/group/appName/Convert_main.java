package com.group.appName;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Convert_main {

    public static void main(String[] args) throws IOException {
        // Step 1: Read Excel File into Java List Objects
        List<Fires> fires = readExcelFile("D://Apparently.xlsx");

        // Step 2: Convert Java Objects to JSON String
        String jsonString = convertObjects2JsonString(fires);

        //Print out result for simplicity of observation
        System.out.println(jsonString);
        //return jsonString;
    }


    public static String sortCellData (final Cell cell) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        String result = "";
        Cell currentCell = cell;
        switch (currentCell.getCellType()) {
            case STRING:
                result = currentCell.getRichStringCellValue()
                        .getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellInternalDateFormatted(currentCell)) {
                    result = sdf.format(currentCell.getDateCellValue());
                } else {
                    result = Double.toString(currentCell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                result = Boolean.toString(currentCell.getBooleanCellValue());
                break;
            case FORMULA:
                result = currentCell.getCellFormula()
                        .toString();
                break;
            case BLANK:
                System.out.println();
                break;
            default:
                break;
        }
        return result;
    }

    public static Fires directToCategory (Fires fire, Row row, int cellIndex, Sheet sheet) {
        for(int i = cellIndex; i <= 6; i ++){
            Cell cell = row.getCell(i);

            if (cellIndex == 0) { // ID
                fire.setId(sortCellData(cell));
            } else if (cellIndex == 1) { // Date
                fire.setDate(sortCellData(cell));
            } else if (cellIndex == 2) { // Message
                fire.setMessage(sortCellData(cell));
            } else if (cellIndex == 3) { // Address
                fire.setAddress_object_fireFeature(sortCellData(cell));
            } else if (cellIndex == 4) { // District
                fire.setDistrict(sortCellData(cell));
            } else if (cellIndex == 5) { // Fire Station
                fire.setFireStation(sortCellData(cell));
            }
            cellIndex++;
        }
        return fire;
    }

    public static List<Fires> readingRows (int rowNumber, int lastRowIndex, Iterator <Row> rows, Fires fire, Sheet sheet){
        List  list = new ArrayList<Fires>();

        for (int i = 5; i <= 7; i++){
            Row currentRow = sheet.getRow(i);
            int cellIndex = 0;
            list.add(directToCategory(fire, currentRow, cellIndex, sheet));
            System.out.println(directToCategory(fire, currentRow, cellIndex, sheet));
        }

        return list;
    }
    /**
     * Read Excel File into Java List Objects
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private static List<Fires> readExcelFile(String filePath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet("Таблица по выездам");
        Iterator<Row> rows = sheet.iterator();
        int lastRowIndex = sheet.getLastRowNum();
        //System.out.println("lastROwIndex " + lastRowIndex);
        int rowNumber = 0;
        Fires fire = new Fires();
        List<Fires> listFires = readingRows(rowNumber, lastRowIndex, rows, fire, sheet);

        // Close WorkBook
        workbook.close();

        return listFires;
    }

    /**
     * Convert Java Objects to JSON String
     *
     * @param fires
     */
    private static String convertObjects2JsonString(List<Fires> fires) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            return mapper.writeValueAsString(fires);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }
}