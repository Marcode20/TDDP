package com.tddp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class FIleServiceImpl implements FileService{
    public File createFile(String fileContent, String name) throws IOException {
        String nameWithLocation = "src/main/resources/data/" + name;
        File myObj = new File(nameWithLocation);
        if (myObj.createNewFile()) {
            System.out.println("File creado: " + myObj.getName());
        } else {
            System.out.println("File ya existe.");
        }

        FileWriter myWriter = new FileWriter(nameWithLocation);
        myWriter.write(fileContent);
        myWriter.close();
        System.out.println("File escrito exitosamente");
        return myObj;
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
