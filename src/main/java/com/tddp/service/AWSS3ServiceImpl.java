package com.tddp.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.tddp.model.Owner;
import com.tddp.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AWSS3ServiceImpl implements AWSS3Service {

    private final AmazonS3 amazonS3;
    @Value("${aws.s3.bucket}")
    private String BUCKET_NAME;

    public AWSS3ServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }


    public List<S3Object> listObjects(){
        List<S3Object> s3Objects = new ArrayList<S3Object>();
        ObjectListing s3ObjectListing =  this.amazonS3.listObjects(BUCKET_NAME);
        for (S3ObjectSummary objectSummary: s3ObjectListing.getObjectSummaries()) {
            S3Object s3Object = new S3Object();
            s3Object.bucketName = objectSummary.getBucketName();
            s3Object.eTag = objectSummary.getETag();
            s3Object.key = objectSummary.getKey();
            s3Object.lastModified = objectSummary.getLastModified();
            s3Object.size = objectSummary.getSize();
            s3Object.storageClass = objectSummary.getStorageClass();

            //Owner owner = new Owner(objectSummary.getOwner().getDisplayName(), objectSummary.getOwner().getId());

            //s3Object.owner = owner;
            log.info("Objecto: " + objectSummary);
            //System.out.println("Objecto: " + objectSummary);
            s3Objects.add(s3Object);
        }
        return s3Objects;
    }

    public void uploadObject( File file){
        this.amazonS3.putObject(BUCKET_NAME,  file.getName(), file);
        System.out.println("Se ha subido el archivo");
    }

    public File convertToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
