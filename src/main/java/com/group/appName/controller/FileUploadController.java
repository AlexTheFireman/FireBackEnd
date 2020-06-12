package com.group.appName.controller;

import com.group.appName.service.FireService;
import com.group.appName.service.FilterManager;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class FileUploadController {

    @Autowired
    private FireService fireService;

    @Autowired
    private FilterManager filterManager;

    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFile(@RequestParam("file") MultipartFile multiPartFile) throws IOException, JSONException {
        File file = convert(multiPartFile);
        fireService.addNewFile(file);
        return "Success";
    }

    public static File convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    @RequestMapping(value = "/api/get/{fileName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getFile(@PathVariable String fileName) throws IOException {
        String firesInfo = fireService.getFile(fileName);
        return firesInfo;
    }

    @RequestMapping(value = "/api/get/{fileName}", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public String getInfo(@PathVariable String fileName, @RequestBody String params) throws IOException {
        return filterManager.filterOne(fileName, params);
    }

    @RequestMapping(value = "/api/get/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List getFileList() {
        List fileList = fireService.getAll();
        return fileList;
    }
}

