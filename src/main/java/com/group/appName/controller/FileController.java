package com.group.appName.controller;

import com.group.appName.service.CheckFileNameForDB;
import com.group.appName.service.DownloadStatus;
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
public class FileController {

    @Autowired
    private FireService fireService;

    @Autowired
    private FilterManager filterManager;

    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile multiPartFile) throws IOException, JSONException, NullPointerException {
        File file = convertFromMultipartToFile(multiPartFile);
        return CheckFileNameForDB.checkFileNameBeforeUploadToDB(file);
    }

    private static File convertFromMultipartToFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    @RequestMapping(value = "/api/get/{fileName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getFile(@PathVariable String fileName) {
        return fireService.getFile(fileName);
    }

    @RequestMapping(value = "/api/delete/{fileName}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteFile(@PathVariable String fileName) {
        fireService.deleteFile(fileName);
    }

    @RequestMapping(value = "/api/get/{fileName}", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public String getDataFromFile(@PathVariable String fileName, @RequestBody String params) throws IOException {
        return filterManager.filterByParams(fileName, params);
    }

    @RequestMapping(value = "/api/get/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List getFileList() {
        return fireService.getAll();
    }
}

