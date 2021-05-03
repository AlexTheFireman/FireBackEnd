package com.group.appName.repository;

import com.group.FileTestConfiguration;
import com.group.appName.AppNameApplication;
import com.group.appName.model.FileEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Import(FileTestConfiguration.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppNameApplication.class)
@Transactional
public class FileRepositoryTest {

    @Autowired
    protected FileRepository fileRepository;

    private FileEntity fileEntity = new FileEntity();
    private FileEntity savedFileEntity1;
    private FileEntity savedFileEntity2;

    @Before
    public void setUp() {

        fileEntity.setFileName("Test1.xls");
        savedFileEntity1 = fileRepository.save(fileEntity);

        fileEntity.setFileName("Test2.xls");
        savedFileEntity2 = fileRepository.save(fileEntity);
    }

    @Test
    public void getFileByName() {
        FileEntity originFile = fileRepository.findByFileName(savedFileEntity1.getFileName());

        assertNotNull(originFile);
    }

    @Test
    public void getAllFiles() {
        List<String> originFileNames = fileRepository.getAllFileNames();

        assertTrue(originFileNames.containsAll(Arrays.asList(savedFileEntity1.getFileName(), savedFileEntity2.getFileName())));
    }

    @Test
    public void deleteFileEntityByFileName(){
        FileEntity savedFileEntity = fileRepository.save(fileEntity);

        fileRepository.deleteByFileName(savedFileEntity.getFileName());
        assertNull(fileRepository.findByFileName(savedFileEntity.getFileName()));
    }
}