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

  public static String start () throws IOException {
    // Step 1: Read Excel File into Java List Objects
    List<Fires> fires = readExcelFile("D://R2.xlsx");

    // Step 2: Convert Java Objects to JSON String
    String jsonString = convertObjects2JsonString(fires);

    System.out.println(jsonString);
      return jsonString;
  }


  public static String choise (Cell cell) {
	  SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
	  String result = "";
	  Cell currentCell = cell;
    switch(currentCell.getCellType()) {
	 case STRING:
		 result = currentCell.getRichStringCellValue().getString();
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
		 result = currentCell.getCellFormula().toString();
		 break;
	 case BLANK:
		 System.out.println();
		 break;
	 default:
		 break;
	}
    return result;
  }  
  
  /**
   * Read Excel File into Java List Objects
   * 
   * @param filePath
   * @return
 * @throws IOException 
   */
  private static List<Fires> readExcelFile(String filePath) throws IOException{
	  	FileInputStream excelFile = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet("Таблица по выездам");
        Iterator<Row> rows = sheet.iterator();

        ArrayList<Fires> lstFires = new ArrayList<Fires>();
        int rowNumber = 0;
        while (rows.hasNext()) {
          Row currentRow = rows.next();

            // skip header
            if(rowNumber == 0) {
                rowNumber++;
                continue;
            }

          Iterator<Cell> cellsInRow = currentRow.iterator();
          Fires fire = new Fires();
          int cellIndex = 0;
          while (cellsInRow.hasNext()) {
            Cell cell = cellsInRow.next();
          
              if(cellIndex==0) { // ID
                fire.setId(choise(cell));
              } else if(cellIndex==1) { // Date
            	fire.setDate(choise(cell));
              } else if(cellIndex==2) { // Message
            	fire.setMessage(choise(cell));
              } else if(cellIndex==3) { // Address
            	fire.setAddress_object_fireFeature(choise(cell));
              } else if(cellIndex==4) { // District
            	fire.setDistrict(choise(cell));
              } else if(cellIndex==5) { // Fire Station
            	fire.setFireStation(choise(cell));
              }
              cellIndex++;
          }
          lstFires.add(fire);
         }
        
        // Close WorkBook
        workbook.close();
        
        return lstFires;
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
        jsonString = mapper.writeValueAsString(fires);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      
      return jsonString; 
  }
}