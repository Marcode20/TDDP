package com.tddp.model;


import java.util.Date;


public class S3Object {
    public String bucketName ;
    public String key ;
    public String eTag;
    public long size;
    public Date lastModified;
    public String storageClass;

    public Owner owner;

}
