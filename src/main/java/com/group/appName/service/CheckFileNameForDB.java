//package com.group.appName.service;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import static org.apache.commons.io.FilenameUtils.getExtension;
//
//public class CheckFileNameForDB {
//
//       public static Enum<DownloadStatus> checkFileNameBeforeUploadToDB (File fileName) throws IOException {
//           String fileExtension = getExtension(fileName.getPath());
//           FireService service = new FireService();
//           if ((fileExtension.equals("xlsx")) || (fileExtension.equals("xls"))) {
//               if (isFileNameExistInList(service.getAll(), fileName)) {
//                   return DownloadStatus.FILE_ALREADY_EXIST;
//               } else {
//
//                   service.addNewFile(fileName);
//                   return DownloadStatus.SUCCESS;
//               }
//           } else {
//               return DownloadStatus.CHECK_FILE_EXTENSION;
//           }
//       }
//
//    private static boolean isFileNameExistInList(List<String> fileList, File fileName) {
//        for (String s : fileList) {
//
//            if (s.equalsIgnoreCase(fileName.getName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//}

