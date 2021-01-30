package com.group.appName.service;

import com.group.appName.Convert;
import com.group.appName.model.FileEntity;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

import java.util.List;

import static org.apache.commons.io.FilenameUtils.getExtension;

@EnableAutoConfiguration
@EnableTransactionManagement
@Transactional
@Service
public class FireService {

    @Autowired
    SessionFactory sessionFactory;


    public void addNewFile(File file) throws IOException, IllegalStateException, NullPointerException {
        Session session = sessionFactory.getCurrentSession();
        FileEntity object = new FileEntity();
        String dataAsJSON = Convert.convertToJsonString(file.getPath());
        Byte[] arrayOfBytesWrapper = Convert.convertFromJsonToBytesArrayWrapper(dataAsJSON);
        String name = file.getName();
        object.setFileName(name);
        object.setFileData(arrayOfBytesWrapper);
        session.save(object);
    }


    public String getFile(String fileName) {
        FileEntity obj = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            obj = session.get(FileEntity.class, fileName);
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction()
                        .rollback();
            }
        }
        if (obj != null) {
            return Convert.convertFromBytesWrapArrayToString(obj.getFileData());
        }
        return null;
    }

    public void deleteFile (String fileName) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE FROM FileEntity WHERE fileName = :fileName")
                .setParameter("fileName", fileName)
                .executeUpdate();
    }

    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        List list = session.createQuery("SELECT FE.fileName FROM FileEntity AS FE ")
                .list();
        return list;
    }

    public Enum<DownloadStatus> checkFileNameBeforeUploadToDB (File fileName) throws IOException {
        String fileExtension = getExtension(fileName.getPath());
        if ((fileExtension.equals("xlsx")) || (fileExtension.equals("xls"))) {
            if (isFileNameExistInList(getAll(), fileName)) {
                return DownloadStatus.FILE_ALREADY_EXISTS;
            } else {

                addNewFile(fileName);
                return DownloadStatus.SUCCESS;
            }
        } else {
            return DownloadStatus.CHECK_FILE_EXTENSION;
        }
    }

    boolean isFileNameExistInList(List<String> fileList, File fileName) {
        for (String s : fileList) {
            if (s.equalsIgnoreCase(fileName.getName())) {
                return true;
            }
        }
        return false;
    }
}


