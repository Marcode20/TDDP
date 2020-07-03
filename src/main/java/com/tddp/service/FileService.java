package com.tddp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {

    File createFile(String fileContent, String name) throws IOException;
    File convertToFile(MultipartFile file, String name) throws IOException;
    String setUniqueFileName(String originalFileName);
}
