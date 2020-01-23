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
        String jsonString = convertObjects2JsonString(fires);

        System.out.println(jsonString);
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

    public static List readingRows (int rowNumber, Iterator <Row> rows, Fires fire){
        List list = new ArrayList();

        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // skip header
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cellsInRow = currentRow.iterator();
            int cellIndex = 0;
            list.add(directToCategory(fire, cellsInRow, cellIndex));

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
    private static List readExcelFile(String filePath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet("Таблица по выездам");
        Iterator<Row> rows = sheet.iterator();
        List listFires;
        int rowNumber = 0;
        Fires fire = new Fires();

        listFires = readingRows(rowNumber, rows, fire);
        /*while (rows.hasNext()) {
            Row currentRow = rows.next();

            // skip header
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cellsInRow = currentRow.iterator();
            Fires fire = new Fires();
            int cellIndex = 0;
            directToCategory(fire, cellsInRow, cellIndex);

            listFires.add(fire);
        }*/

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