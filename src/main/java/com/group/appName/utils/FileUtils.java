package com.group.appName.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.commons.io.FilenameUtils.getExtension;


public class FileUtils {

    public static File convertFromMultipartToFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        File convertFile = new File(originalFilename);

        try (FileOutputStream fos = new FileOutputStream(convertFile)){
            fos.write(file.getBytes());
        }

        return convertFile;
    }

    public static boolean checkFileExtensionBeforeUploadToDB (File file){
        String fileExtension = getExtension(file.getPath());
        return (fileExtension.equals("xlsx")) || (fileExtension.equals("xls"));
    }

}

