package com.group.appName.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.io.FilenameUtils.getExtension;


public class CheckFileNameForDB {

       public static String checkFileNameBeforeUploadToDB (File fileName) throws IOException {
           if (fileName != null) {
               if ((getExtension(fileName.getPath()).equals("xlsx")) || (getExtension(fileName.getPath()).equals("xls"))) {
                   if (isFileNameExistInList(FireService.getAll(), fileName)) {
                       return "FileAlreadyHas";
                   } else {
                       FireService.addNewFile(fileName);
                       return "Success";
                   }
               } else {
                   return "Check";
               }
           } else {
               return "Null";
           }
    }
    private static boolean isFileNameExistInList(List<String> fileList, File fileName) {
        for (String s : fileList) {

            if (s.equals(fileName.getName())) {
                return true;
            }
        }
        return false;
    }
}

