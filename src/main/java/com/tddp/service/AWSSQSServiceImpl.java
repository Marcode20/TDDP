package com.tddp.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AWSSQSServiceImpl implements AWSSQSService {

    final AmazonSQS amazonSQS;

    final FileService fileService;

    final AWSS3Service awss3Service;

    public AWSSQSServiceImpl(AmazonSQS amazonSQS, FileService fileService, AWSS3Service awss3Service) {
        this.amazonSQS = amazonSQS;
        this.fileService = fileService;
        this.awss3Service = awss3Service;
    }

    @Value("${aws.sqs.queue}")
    private String SQS_URL;


    @Override
    public void sendToQueue(String message, String s3ObjectKey) {
        MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
        messageAttributeValue.setStringValue(s3ObjectKey);

        SendMessageRequest smreq = new SendMessageRequest(SQS_URL, message);
        smreq.addMessageAttributesEntry("s3ObjectKey", messageAttributeValue);
        SendMessageResult smres = amazonSQS.sendMessage(smreq);

        if (smres.getSdkHttpMetadata().getHttpStatusCode() != 200){
            throw new RuntimeException("Ocurrio un error");
        }
        System.out.println("Mensaje enviado a la cola");

    }

    @Override
    public void readQueueAndSendToS3() throws IOException {

        ReceiveMessageRequest rmreq = new ReceiveMessageRequest()
                .withQueueUrl(SQS_URL)
                .withMaxNumberOfMessages(1)
                .withWaitTimeSeconds(3);
        while (true){
            ReceiveMessageResult rmres = amazonSQS.receiveMessage(rmreq);
            if (rmres.getSdkHttpMetadata().getHttpStatusCode() != 200){
                System.out.println("Ocurrio un error en " + SQS_URL + " -> " + rmres.getSdkHttpMetadata());
                return;
            }
            System.out.println("Obteniendo " + rmres.getMessages().size() + "mensajes desde la cola: "+ SQS_URL);

            List<Message> messages = rmres.getMessages();
            for (Message message: messages){
                String body = message.getBody();

                Map<String, String> attributes = new HashMap<>();
                attributes = message.getAttributes();

                String s3ObjectName = attributes.get("s3ObjectKey");
                File file = fileService.createFile(body, s3ObjectName);

                System.out.println(s3ObjectName);

                awss3Service.uploadObject(file, s3ObjectName);


                System.out.println("Body = " + body);

            }
        }
    }
}
