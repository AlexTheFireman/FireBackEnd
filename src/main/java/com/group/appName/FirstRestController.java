<<<<<<< HEAD
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
=======
package com.group.appName;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class FirstRestController {

    //@GetMapping(value = "/1", produces = "Content-type=application/json")
    @RequestMapping(value = "/1", method = RequestMethod.GET, produces = "application/json")
    public String stub() throws IOException {
        Convert convert = new Convert();

        String s = convert.start();
        return s;
    }
}
>>>>>>> 06239f2f2d695d5a9dbb68da5239365020286f7d
