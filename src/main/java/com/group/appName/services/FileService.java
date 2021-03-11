package com.group.appName.services;

import com.group.appName.repositories.FileRepository;
import com.group.appName.enums.DownloadStatus;
import com.group.appName.utils.FileConverter;
import com.group.appName.models.FileEntity;

import com.group.appName.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

import java.util.List;

import static com.group.appName.utils.FileConverter.convertFromBytesWrapArrayToString;
import static com.group.appName.utils.FileConverter.convertFromJsonToBytesArrayWrapper;

@Transactional
@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    public DownloadStatus addNewFileIfNotExists(File file) throws IOException {
        if (FileUtils.checkFileExtensionBeforeUploadToDB(file)) {
            if (this.getFile(file.getName()) == null) {
                String dataAsJSON = FileConverter.convertToJsonString(file.getPath());
                Byte[] arrayOfBytesWrapper = convertFromJsonToBytesArrayWrapper(dataAsJSON);
                String name = file.getName();

                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileName(name);

                fileEntity.setFileData(arrayOfBytesWrapper);
                fileRepository.save(fileEntity);
                return DownloadStatus.SUCCESS;
            } else {
                return DownloadStatus.FILE_ALREADY_EXISTS;
            }
        } else {
            return DownloadStatus.CHECK_FILE_EXTENSION;
        }
    }

    public String getFile(String fileName) {
        FileEntity fileEntity = fileRepository.findByFileName(fileName);
        return convertFromBytesWrapArrayToString(fileEntity.getFileData());
    }

    public void deleteFile(String fileName) {
        fileRepository.deleteByFileName(fileName);
    }

    public List<String> getAll() {
        return fileRepository.getAllFileNames();
    }
}
