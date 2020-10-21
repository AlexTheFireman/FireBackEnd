package com.group.appName.service;

public enum DownloadStatus {
    FILE_ALREADY_EXIST ("Файл с таким именем уже существует в базе данных"),
    SUCCESS("Файл успешно загружен"),
    CHECK_FILE_EXTENSION("Принимаются файлы только с расширением \"xlsx\" или \"xls\""
            +System.lineSeparator()+
            "Проверьте расширение файла"),
    NO_FILE_SELECTED("Файл не выбран");

    private String status;

    DownloadStatus(String downStatus) {
        status = downStatus;
    }

     public String getStatus(){
        return this.status;
    }


}
