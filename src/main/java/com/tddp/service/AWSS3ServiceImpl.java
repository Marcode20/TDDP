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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class AWSS3ServiceImpl implements AWSS3Service {

    private final AmazonS3 amazonS3;
    @Value("${aws.s3.bucket}")
    private String BUCKET_NAME;

    @Value("${aws.s3.region}")
    private String region;

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

    public String uploadObject( File file, String s3ObjectName){
        this.amazonS3.putObject(BUCKET_NAME,  s3ObjectName, file);
        System.out.println("Se ha subido el archivo");
        String imageURL = "https://" + BUCKET_NAME + "s3." + region + ".amazonaws.com/" + file.getName();
        return imageURL;
    }

    public File convertToFile(MultipartFile file, String name) throws IOException {
        String nameWithLocation = "src/main/resources/data/" + name;
        System.out.println("nameWithLocation : " + nameWithLocation);
        File convFile = new File(nameWithLocation);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public String setUniqueFileName(String originalFileName) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-hhmmss");
        Random random = new Random();

        String formatedUniqueImageName = String.format("%s-%s-%s", sdf.format(new Date()), random.nextInt(9), originalFileName);
        return formatedUniqueImageName;
    }
}
