package com.tddp.service;

import com.tddp.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface AWSS3Service {
    List<S3Object> listObjects();
    String uploadObject( File file, String S3ObjectName);
    File convertToFile(MultipartFile file, String name) throws IOException;
    String setUniqueFileName(String originalFileName);
}
