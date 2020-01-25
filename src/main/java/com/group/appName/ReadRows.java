package com.group.appName;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class ReadRows {
    public static List<Fires> readRows (int lastRowIndex, Sheet sheet){
        List list = new ArrayList<Fires>();

        for (int i = 1; i <= lastRowIndex; i++){
            Fires fire = new Fires();
            Row currentRow = sheet.getRow(i);
            int cellIndex = 0;
            System.out.println(DirectToCategory.directToCategory(fire, currentRow, cellIndex));
            list.add(DirectToCategory.directToCategory(fire, currentRow, cellIndex));
        }

        return list;
    }
}
