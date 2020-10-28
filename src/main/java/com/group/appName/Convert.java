package com.group.appName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.appName.model.FileEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.io.FilenameUtils.getExtension;


public class Convert {
    public static String convertToJsonString(final String path) throws IOException {
        List<Fire> fires = readExcelFile(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(fires);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static List<Fire> readExcelFile(final String filePath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(filePath));
        return getListOfFires(filePath, excelFile);
    }
    private static List<Fire> getListOfFires (String filePath, FileInputStream excelFile) throws IOException
    {
        if (getExtension(filePath).equals("xlsx")) {
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheet("Таблица по выездам");
            int lastRowIndex = sheet.getLastRowNum();
            List<Fire> listFires = readRows(lastRowIndex, sheet);
            workbook.close();
            return listFires;
        } else if(getExtension(filePath).equals("xls")){
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheet("Таблица по выездам");
            int lastRowIndex = sheet.getLastRowNum();
            List<Fire> listFires = readRows(lastRowIndex, sheet);
            workbook.close();
            return listFires;
        } else {
            return null;
        }
    }
    private static List<Fire> readRows(int lastRowIndex, final Sheet sheet){
        List <Fire> firesList = new ArrayList<Fire>();

        for (int i = 5; i <= lastRowIndex; i++){
            Fire fire = new Fire();
            Row currentRow = sheet.getRow(i);
            int cellIndex = 0;
            firesList.add(setFireInfoByCategory(fire, currentRow, cellIndex));
        }
        return firesList;
    }
    private static Fire setFireInfoByCategory(Fire fire, final Row row, int cellIndex) {
        int lastInterestingCellIndex = 35;
        for(int i = cellIndex; i <= lastInterestingCellIndex; i ++){
            Cell cell = row.getCell(i);

            if (cellIndex == 0) {
                fire.setId(sortCellData(cell));
            } else if (cellIndex == 1) {
                fire.setDate(sortCellData(cell));
            } else if (cellIndex == 2) {
                fire.setMessage(sortCellData(cell));
            } else if (cellIndex == 3) {
                fire.setAddressObjectFireFeature(sortCellData(cell));
            } else if (cellIndex == 4) {
                fire.setDistrict(sortCellData(cell));
            } else if (cellIndex == 5) {
                fire.setFireStation(sortCellData(cell));
            } else if (cellIndex == 6) {
                fire.setDestination(sortCellData(cell));
            } else if (cellIndex == 7) {
                fire.setWhereWasTheFire(sortCellData(cell));
            } else if (cellIndex == 8) {
                fire.setRescueWorks(sortCellData(cell));
            } else if (cellIndex == 9) {
                fire.setAmountOfRescuedPeople(sortCellData(cell));
            } else if (cellIndex == 10) {
                fire.setAmountOfEvacuatedPeople(sortCellData(cell));
            } else if (cellIndex == 11) {
                fire.setFireChiefRank(sortCellData(cell));
            } else if (cellIndex == 12) {
                fire.setFireChiefName(sortCellData(cell));
            } else if (cellIndex == 13) {
                fire.setAmountOfSmokeGroups(sortCellData(cell));
            } else if (cellIndex == 14) {
                fire.setSmokeTime(sortCellData(cell));
            } else if (cellIndex == 15) {
                fire.setExtinguishingAgents(sortCellData(cell));
            } else if (cellIndex == 16) {
                fire.setFirstEngine(sortCellData(cell));
            } else if (cellIndex == 17) {
                fire.setSecondEngine(sortCellData(cell));
            } else if (cellIndex == 18) {
                fire.setThirdEngine(sortCellData(cell));
            } else if (cellIndex == 19) {
                fire.setFirstReserve(sortCellData(cell));
            } else if (cellIndex == 20) {
                fire.setSecondReserve(sortCellData(cell));
            } else if (cellIndex == 21) {
                fire.setFirstSquadron(sortCellData(cell));
            } else if (cellIndex == 22) {
                fire.setSecondSquadron(sortCellData(cell));
            } else if (cellIndex == 23) {
                fire.setUsingHydrants(sortCellData(cell));
            } else if (cellIndex == 24) {
                fire.setReportPDF(sortCellData(cell));
            } else if (cellIndex == 25) {
                fire.setLocality(sortCellData(cell));
            } else if (cellIndex == 26) {
                fire.setFireRank(sortCellData(cell));
            } else if (cellIndex == 27) {
                fire.setDetectionTime(sortCellData(cell));
            } else if (cellIndex == 28) {
                fire.setMessageTime(sortCellData(cell));
            } else if (cellIndex == 29) {
                fire.setArrivalTime(sortCellData(cell));
            } else if (cellIndex == 30) {
                fire.setFirstNozzleTime(sortCellData(cell));
            } else if (cellIndex == 31) {
                fire.setLocalizationTime(sortCellData(cell));
            } else if (cellIndex == 32) {
                fire.setBurningLiquidationTime(sortCellData(cell));
            } else if (cellIndex == 33) {
                fire.setTotalLiquidationTime(sortCellData(cell));
            } else if (cellIndex == 34) {
                fire.setComment(sortCellData(cell));
            }
            cellIndex++;
        }
        return fire;
    }
    private static String sortCellData(final Cell cell) {
        String result = "";

        switch (cell.getCellType()) {
            case STRING:
            case FORMULA:
            case BOOLEAN:
            case BLANK:
                result = cell.toString();
                break;
            case NUMERIC:
                if (DateUtil.isCellInternalDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();

                    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
                    SimpleDateFormat formatYearOnly = new SimpleDateFormat("yyyy");
                    SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
                    SimpleDateFormat newFormatTime = new SimpleDateFormat("HH:mm");
                    String timeStamp =formatTime.format(date);
                    String dateStamp = formatYearOnly.format(date);

                    if (dateStamp.equals("1899")){
                        return newFormatTime.format(date);
                    } else if (timeStamp.equals("00:00:00")){
                        return newDateFormat.format(date);
                    }
                } else {
                    double doub = cell.getNumericCellValue();
                    int value = (int) doub;
                    result = Integer.toString(value);
                }
                break;
            default:
                break;
        }
        return result;
    }

    public static Byte[] convertFromJsonToBytesArrayWrapper(String jsonString) throws UnsupportedEncodingException {
        byte[] bytes = jsonString.getBytes("utf-8");
        Byte[] bytesWrapArray = new Byte[bytes.length];
        int i = 0;
        for(byte b : bytes){
            bytesWrapArray[i++] = b;
        }
        return bytesWrapArray;
    }

    public static String convertFromBytesWrapArrayToString(Byte[] bytesWrapArray){
        byte[] bytes = new byte[bytesWrapArray.length];
        int i=0;
        for(Byte b: bytesWrapArray) {
            bytes[i++] = b.byteValue();
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}

