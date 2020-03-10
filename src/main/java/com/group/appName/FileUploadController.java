package com.group.appName;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    private FireService fireService;

    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public @ResponseBody JSONObject uploadFile(@RequestParam("file") MultipartFile multiPartFile) throws IOException, JSONException {
        File file = convert(multiPartFile);
        fireService.addNewFire(file);
        JSONObject json = fireService.getFire(file.getName());

        return json;
    }

    public static File convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }
}