package com.group.appName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Convert {

    public static String start() throws IOException {
        // Step 1: Read Excel File into Java List Objects
        List<Fires> fires = readExcelFile("D://R2.xlsx");

        // Step 2: Convert Java Objects to JSON String
        String jsonString = convertToJsonString(fires);

        //System.out.println(jsonString);
        return jsonString;
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

    public static Fires directToCategory (Fires fire, Iterator<Cell> cellsInRow, int cellIndex) {
        while (cellsInRow.hasNext()) {
            Cell cell = cellsInRow.next();

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

    public static List<Fires> readExcelFile(String filePath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet("Таблица по выездам");
        int lastRowIndex = sheet.getLastRowNum();
        List<Fires> listFires = readRows(lastRowIndex, sheet);

        // Close WorkBook
        workbook.close();

        return listFires;
    }

    public static List<Fires> readRows (int lastRowIndex, Sheet sheet){
        List list = new ArrayList<Fires>();

        for (int i = 1; i <= lastRowIndex; i++){
            Fires fire = new Fires();
            Row currentRow = sheet.getRow(i);
            int cellIndex = 0;
            list.add(directToCategory(fire, currentRow, cellIndex));
        }

        return list;
    }

    public static Fires directToCategory (Fires fire, Row row, int cellIndex) {
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

    private static String convertToJsonString(List<Fires> fires) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(fires);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }
}