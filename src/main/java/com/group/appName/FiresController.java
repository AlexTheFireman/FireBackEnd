package com.group.appName;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class FiresController {

    @RequestMapping(value = "/api/fires", method = RequestMethod.GET, produces = "application/json")
    public String stub(String fileName) throws IOException {
        Convert convert = new Convert();
        FileUploadControllerToPC fileUploadController = new FileUploadControllerToPC();
        //String filePath = "D:/upload/" + fileUploadController.handleFileUpload(fileName);
        //String s = convert.start(filePath);
        //return s;
        return null;
    }
}





