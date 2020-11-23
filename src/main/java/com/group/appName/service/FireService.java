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

@EnableAutoConfiguration
@EnableTransactionManagement
@Service
public class FireService {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
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

    @Transactional
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
    @Transactional
    public void deleteFile (String fileName) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println("File name = " + fileName);
        session.createQuery("DELETE FROM FileEntity WHERE fileName = :fileName")
                .setParameter("fileName", fileName)
                .executeUpdate();
    }

    @Transactional
    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        List list = session.createQuery("SELECT FE.fileName FROM FileEntity AS FE ")
                .list();
        return list;
    }

}


