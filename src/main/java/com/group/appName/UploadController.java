package com.group.appName;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)

    public @ResponseBody String doUpload (@RequestParam("file") MultipartFile multipartFile) throws IOException {
        File file = new File("C:/Users/Alex/IdeaProjects");
        multipartFile.transferTo(file);
        return "Uploaded:" + multipartFile.getSize() + "bytes";
    }
}
