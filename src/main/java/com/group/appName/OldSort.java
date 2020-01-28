package com.group.appName;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.SimpleDateFormat;

public class OldSort {
    public static String sortCellData(final Cell cell) {
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

}
