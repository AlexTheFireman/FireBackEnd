package com.group.appName;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class FirstRestController {

    @RequestMapping(value = "/1", method = RequestMethod.GET, produces = "application/json")
    public String stub() throws IOException {
        Convert convert = new Convert();
        String filePath = "D://R2.xlsx";
        String s = convert.start(filePath);
        return s;
    }
}
