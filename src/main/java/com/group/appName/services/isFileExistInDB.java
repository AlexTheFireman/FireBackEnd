package com.group.appName.services;

import java.io.File;
import java.util.List;

public class isFileExistInDB {

    public static boolean isExist(List <String> fileList, File fileName) {
        for (String s : fileList) {

            if (s.equals(fileName.getName())) {
                return true;
            }
        }
        return false;
    }
}


