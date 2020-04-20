package com.group.appName;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class FiresController {

    @RequestMapping(value = "/fires", method = RequestMethod.GET, produces = "application/json")
    public String stub(String fileName) throws IOException {
        Convert convert = new Convert();
        FileUploadController fileUploadController = new FileUploadController();
        String filePath = "D:/R2.xlsx"; //+ fileUploadController.handleFileUpload("R2.xlsx");
        String s = convert.start(filePath);
        return s;

    }
}





