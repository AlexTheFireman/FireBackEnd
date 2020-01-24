package com.group.appName;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class ReadRows {
    public static List<Fires> readRows (int lastRowIndex, Fires fire, Sheet sheet){
        List  list = new ArrayList<Fires>();

        for (int i = 5; i <= lastRowIndex; i++){
            Row currentRow = sheet.getRow(i);
            int cellIndex = 0;
            list.add(DirectToCategory.directToCategory(fire, currentRow, cellIndex));
        }

        return list;
    }
}
