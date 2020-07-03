package com.tddp.service;

import com.tddp.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface AWSS3Service {
    List<S3Object> listObjects();
    void uploadObject( File file);
    File convertToFile(MultipartFile file) throws IOException;
}
