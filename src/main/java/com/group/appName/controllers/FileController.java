package com.group.appName.controllers;

import com.group.appName.enums.DownloadStatus;
import com.group.appName.services.FileService;
import com.group.appName.services.FilterManager;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.group.appName.utils.FileUtils.convertFromMultipartToFile;
import static com.group.appName.utils.FileUtils.checkFileExtensionBeforeUploadToDB;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FilterManager filterManager;

    @PostMapping(value = "/upload")
    public DownloadStatus uploadFile(@RequestParam("file") MultipartFile multiPartFile) throws IOException, JSONException, NullPointerException {
        File file = convertFromMultipartToFile(multiPartFile);
        return fileService.addNewFileIfNotExists(file);
    }

    @GetMapping(value = "/get/{fileName}", produces = "application/json")
    public String getFile(@PathVariable String fileName) {
        return fileService.getFile(fileName);
    }

    @DeleteMapping(value = "/delete/{fileName}")
    public void deleteFile(@PathVariable String fileName) {
        fileService.deleteFile(fileName);
    }

    @PostMapping(value = "/get/{fileName}", produces = "application/json",
                    consumes = "application/json")
    public String getDataFromFile(@PathVariable String fileName, @RequestBody String params) throws IOException {
        return filterManager.filteringByAllSelectedFilters(fileName, params);
    }

    @GetMapping(value = "/get/all", produces = "application/json")
    public List getFileList() {
        return fileService.getAll();
    }
}

