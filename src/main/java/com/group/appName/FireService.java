package com.group.appName;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import org.hibernate.cfg.Configuration;

@Service
public class FireService {
    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(FileEntity.class)
            .buildSessionFactory();

    public void addNewFile(File file) throws IOException {

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            FileEntity obj;
            String s = Convert.start(file.getPath());
            String name = file.getName();
            obj = new FileEntity();
            obj.setFileName(name);
            obj.setFileData(s);
            session.save(obj);
            session.getTransaction()
                    .commit();
        } finally {
            session.close();
        }


    }

    // Method 2: This Method Is Used To Display The Records From The Database Table

    public String getFile(String fileName) {

        FileEntity obj = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            obj = session.get(FileEntity.class, fileName);

            /*if (obj != null) {
                System.out.println("Что-то получилось " + obj.getFileData()); ?????????
            }*/
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
        return obj.getFileData();

    }
}
