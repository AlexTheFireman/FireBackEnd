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
        List<Fires> fires = ReadExcelFile.readExcelFile("D://R2.xlsx");

        // Step 2: Convert Java Objects to JSON String
        String jsonString = ConvertToJsonString.convertToJsonString(fires);

        return jsonString;
    }
}