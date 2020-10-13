package com.group.appName.service;

import com.group.appName.Convert;
import com.group.appName.model.FileEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FireService {
    private static SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(FileEntity.class)
            .buildSessionFactory();

    static void addNewFile(File file) throws IOException, IllegalStateException, NullPointerException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        FileEntity obj = new FileEntity();
        String s = Convert.convertToJsonString(file.getPath());
        String name = file.getName();
        obj.setFileName(name);
        obj.setFileData(s);
        session.save(obj);
        session.getTransaction()
                .commit();
    }

    public String getFile(String fileName) {
        FileEntity obj = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            obj = session.get(FileEntity.class, fileName);
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction()
                        .rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        if (obj != null) {
            return obj.getFileData();
        }
        return null;
    }

    public void deleteFile (String fileName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        System.out.println("File name = " + fileName);
        session.createQuery("DELETE FROM FileEntity WHERE fileName = :fileName")
                .setParameter("fileName", fileName)
                .executeUpdate();
        session.close();
    }
    public static List getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List list = session.createQuery("SELECT FE.fileName FROM FileEntity AS FE ")
                .list();

        if (session != null) {
            session.close();
        }
        return list;
    }
}


