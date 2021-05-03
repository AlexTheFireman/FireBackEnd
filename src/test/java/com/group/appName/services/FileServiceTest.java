package com.group.appName.services;


import com.group.FileTestConfiguration;
import com.group.appName.AppNameApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsIterableContaining.hasItem;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppNameApplication.class)
@ContextConfiguration(classes = FileTestConfiguration.class)
@Transactional
public class FileServiceTest {

    @Autowired
    FileService fileService;

    @Test
    public void addNewFileIfNotExists_success() throws IOException {

        File file1 = new File("./src/test/resources/TablesFolder/Table1.xls");
        File file2 = new File("./src/test/resources/TablesFolder/Table2.xls");
        fileService.addNewFileIfNotExists(file1);
        fileService.addNewFileIfNotExists(file2);
        List<String> originalList = fileService.getAll();
        File file3 = new File("./src/test/resources/TablesFolder/Table3.xls");

        if (!(originalList.contains(file3.getName()))){
            fileService.addNewFileIfNotExists(file3);
            List<String> listWithNewFile = fileService.getAll();
            org.junit.Assert.assertThat(listWithNewFile, hasItem(file3.getName()));
        }
    }
}