package com.group.appName.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.appName.dto.FileDto;
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
import java.util.*;

import static org.apache.commons.io.FilenameUtils.getExtension;


public class FileConverter {
    public static String convertToJsonString(final String path) throws IOException {
        List<FileDto> fires = readExcelFile(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(fires);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<FileDto> readExcelFile(final String filePath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(filePath));
        return getListOfFires(filePath, excelFile);
    }

    private static List<FileDto> getListOfFires(String filePath, FileInputStream excelFile) throws IOException {
        Workbook workbook = getExtension(filePath).equals("xlsx") ?
                new XSSFWorkbook(excelFile) :
                new HSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet("Таблица по выездам");
        List<FileDto> fireList = readRows(sheet);
        workbook.close();
        return fireList;
    }

    private static List<FileDto> readRows(Sheet sheet) {
        List<FileDto> fireList = new ArrayList<>();
        int lastRowIndex = setLastRowIndex(sheet);
        for (int i = 5; i <= lastRowIndex; i++) {
            FileDto fire = new FileDto();
            Row currentRow = sheet.getRow(i);
            int cellIndex = 0;
            fireList.add(setFireInfoByCategory(fire, currentRow, cellIndex));
        }
        return fireList;
    }

    private static int setLastRowIndex(Sheet sheet) {
        int lastRowIndex = 0;
        while (sheet.getRow(lastRowIndex)
                .getCell(1) != null) {
            lastRowIndex++;
        }
        return lastRowIndex - 1;
    }

    private static FileDto setFireInfoByCategory(FileDto fileDto, final Row row, int cellIndex) {
        int lastInterestingCellIndex = 35;
        for (int i = cellIndex; i <= lastInterestingCellIndex; i++) {
            Cell cell = row.getCell(i);
            if (cellIndex == 0) {
                fileDto.setId(sortCellData(cell));
            } else if (cellIndex == 1) {
                fileDto.setDate(sortCellData(cell));
            } else if (cellIndex == 2) {
                fileDto.setMessage(sortCellData(cell));
            } else if (cellIndex == 3) {
                fileDto.setAddressObjectFireFeature(sortCellData(cell));
            } else if (cellIndex == 4) {
                fileDto.setDistrict(sortCellData(cell));
            } else if (cellIndex == 5) {
                fileDto.setFireStation(sortCellData(cell));
            } else if (cellIndex == 6) {
                fileDto.setDestination(sortCellData(cell));
            } else if (cellIndex == 7) {
                fileDto.setWhereWasTheFire(sortCellData(cell));
            } else if (cellIndex == 8) {
                fileDto.setRescueWorks(sortCellData(cell));
            } else if (cellIndex == 9) {
                fileDto.setAmountOfRescuedPeople(sortCellData(cell));
            } else if (cellIndex == 10) {
                fileDto.setAmountOfEvacuatedPeople(sortCellData(cell));
            } else if (cellIndex == 11) {
                fileDto.setFireChiefRank(sortCellData(cell));
            } else if (cellIndex == 12) {
                fileDto.setFireChiefName(sortCellData(cell));
            } else if (cellIndex == 13) {
                fileDto.setAmountOfSmokeGroups(sortCellData(cell));
            } else if (cellIndex == 14) {
                fileDto.setSmokeTime(sortCellData(cell));
            } else if (cellIndex == 15) {
                fileDto.setExtinguishingAgents(sortCellData(cell));
            } else if (cellIndex == 16) {
                fileDto.setFirstEngine(sortCellData(cell));
            } else if (cellIndex == 17) {
                fileDto.setSecondEngine(sortCellData(cell));
            } else if (cellIndex == 18) {
                fileDto.setThirdEngine(sortCellData(cell));
            } else if (cellIndex == 19) {
                fileDto.setFirstReserve(sortCellData(cell));
            } else if (cellIndex == 20) {
                fileDto.setSecondReserve(sortCellData(cell));
            } else if (cellIndex == 21) {
                fileDto.setFirstSquadron(sortCellData(cell));
            } else if (cellIndex == 22) {
                fileDto.setSecondSquadron(sortCellData(cell));
            } else if (cellIndex == 23) {
                fileDto.setUsingHydrants(sortCellData(cell));
            } else if (cellIndex == 24) {
                fileDto.setReportPDF(sortCellData(cell));
            } else if (cellIndex == 25) {
                fileDto.setLocality(sortCellData(cell));
            } else if (cellIndex == 26) {
                fileDto.setFireRank(sortCellData(cell));
            } else if (cellIndex == 27) {
                fileDto.setDetectionTime(sortCellData(cell));
            } else if (cellIndex == 28) {
                fileDto.setMessageTime(sortCellData(cell));
            } else if (cellIndex == 29) {
                fileDto.setArrivalTime(sortCellData(cell));
            } else if (cellIndex == 30) {
                fileDto.setFirstNozzleTime(sortCellData(cell));
            } else if (cellIndex == 31) {
                fileDto.setLocalizationTime(sortCellData(cell));
            } else if (cellIndex == 32) {
                fileDto.setBurningLiquidationTime(sortCellData(cell));
            } else if (cellIndex == 33) {
                fileDto.setTotalLiquidationTime(sortCellData(cell));
            } else if (cellIndex == 34) {
                fileDto.setComment(sortCellData(cell));
            }
            cellIndex++;
        }
        return fileDto;
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
                    String timeStamp = formatTime.format(date);
                    String dateStamp = formatYearOnly.format(date);

                    if (dateStamp.equals("1899")) {
                        return newFormatTime.format(date);
                    } else if (timeStamp.equals("00:00:00")) {
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
        for (byte b : bytes) {
            bytesWrapArray[i++] = b;
        }
        return bytesWrapArray;
    }

    public static String convertFromBytesWrapArrayToString(Byte[] bytesWrapArray) {
        byte[] bytes = new byte[bytesWrapArray.length];
        int i = 0;
        for (Byte b : bytesWrapArray) {
            bytes[i++] = b.byteValue();
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}

