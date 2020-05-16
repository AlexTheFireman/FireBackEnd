package com.group.appName;

import com.group.appName.model.FileEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
            FileEntity obj = new FileEntity();
            String s = Convert.start(file.getPath());
            String name = file.getName();
            obj.setFileName(name);
            obj.setFileData(s);
            session.save(obj);
            session.getTransaction()
                    .commit();
        } finally {
            session.close();
        }
    }

    public String getFile(String fileName) {

        FileEntity obj;
        Session session = sessionFactory.getCurrentSession();

            obj = session.get(FileEntity.class, fileName);

        if (obj != null) {
            return obj.getFileData();
        }
         return null;
    }

    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List list = session.createQuery("SELECT files_upload.fileName FROM FileEntity files_upload ").list();

        return list;
    }
}
