package com.tddp.service;


import com.tddp.model.OrdenCompra;

import java.io.IOException;
import java.util.Queue;

public interface AWSSQSService {
    void sendToQueue(String message, String s3ObjectKey);
    void readQueueAndSendToS3() throws IOException;
}
