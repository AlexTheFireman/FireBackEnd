package com.group.appName.controller;

public class UploadFileResponse {
    private String fileName;
    private String downloadUri;

    public UploadFileResponse (String fileName, String downloadUri){
        this.fileName = fileName;
        this.downloadUri = downloadUri;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }

    public void setDownloadUri(String downloadUri){
        this.downloadUri = downloadUri;
    }

    public String getDownloadUri(){
        return getDownloadUri();
    }
}

