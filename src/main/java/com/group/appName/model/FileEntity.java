package com.group.appName;

import javax.persistence.*;

@Entity
@Table(name ="files_upload")
public class FileEntity {
    private String fileName;
    private String fileData;

    @Id
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "file_data")
    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

}

