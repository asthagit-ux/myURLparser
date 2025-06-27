package com.ritesh.demo.htmlfilewoker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ritesh.demo.htmlfilewoker.service.URLProcessor;

@Service
public class KafkaMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageListener.class);

    @Autowired
    URLProcessor urlProcessor;

    @KafkaListener(topics="html_topic", groupId = "group_id")
    public void consume(String message){   
        LOG.info("Message: {}", message);
        urlProcessor.process(message);
    }
}
