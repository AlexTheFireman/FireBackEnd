package com.group.appName;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class DirectToCategory {
    public static Fires directToCategory (Fires fire, Row row, int cellIndex) {
        for(int i = cellIndex; i <= 6; i ++){
            Cell cell = row.getCell(i);

            if (cellIndex == 0) { // ID
                fire.setId(SortCellData.sortCellData(cell));
            } else if (cellIndex == 1) { // Date
                fire.setDate(SortCellData.sortCellData(cell));
            } else if (cellIndex == 2) { // Message
                fire.setMessage(SortCellData.sortCellData(cell));
            } else if (cellIndex == 3) { // Address
                fire.setAddress_object_fireFeature(SortCellData.sortCellData(cell));
            } else if (cellIndex == 4) { // District
                fire.setDistrict(SortCellData.sortCellData(cell));
            } else if (cellIndex == 5) { // Fire Station
                fire.setFireStation(SortCellData.sortCellData(cell));
            }
            cellIndex++;
        }
        return fire;
    }
}
