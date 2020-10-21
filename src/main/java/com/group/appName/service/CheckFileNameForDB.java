package com.group.appName.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.io.FilenameUtils.getExtension;


public class CheckFileNameForDB {

       public static String checkFileNameBeforeUploadToDB (File fileName) throws IOException {
           String fileExtension = getExtension(fileName.getPath());
           if (fileName != null) {
               if ((fileExtension.equals("xlsx")) || (fileExtension.equals("xls"))) {
                   if (isFileNameExistInList(FireService.getAll(), fileName)) {
                       return DownloadStatus.FILE_ALREADY_EXIST.getStatus();
                   } else {
                       FireService.addNewFile(fileName);
                       return DownloadStatus.SUCCESS.getStatus();
                   }
               } else {
                   return DownloadStatus.CHECK_FILE_EXTENSION.getStatus();
               }
           } else {
               return DownloadStatus.NO_FILE_SELECTED.getStatus();
           }
    }
    private static boolean isFileNameExistInList(List<String> fileList, File fileName) {
        for (String s : fileList) {

            if (s.equalsIgnoreCase(fileName.getName())) {
                return true;
            }
        }
        return false;
    }
}

