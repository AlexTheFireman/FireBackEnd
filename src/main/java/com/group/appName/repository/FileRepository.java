package com.group.appName.repository;

import com.group.appName.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {

    FileEntity findByFileName(String name);

    void deleteByFileName(String name);

    @Query("SELECT f.fileName from FileEntity f")
    List<String> getAllFileNames();
}

