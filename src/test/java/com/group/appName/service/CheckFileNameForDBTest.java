//package com.group.appName.service;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CheckFileNameForDBTest {
//
//    @Test
//    void isFileNameExistInList() {
//        File file = new File("C:/Users/Alex/IdeaProjects/appName/src/test/resources/TablesFolder");
//        List<File> files = Arrays.asList(file.listFiles());
//        List <String> fileNames = new ArrayList<>();
//        files.forEach(f -> fileNames.add(f.getName()));
//        fileNames.forEach(fileName -> System.out.println(fileName));
//        File testFile = new File("C:/Users/Alex/IdeaProjects/appName/src/test/resources/TablesFolder/TestTable.xlsx");
//
//        assertTrue(isFileExistInDB.isExist(fileNames, testFile));
//    }
//}
