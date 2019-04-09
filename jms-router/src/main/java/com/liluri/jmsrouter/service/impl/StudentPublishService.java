package com.liluri.jmsrouter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liluri.jmsrouter.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Qualifier("student")
public class StudentPublishService implements PublishService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentPublishService.class);

    private final JmsTemplate jmsTemplate;

    @Autowired
    public StudentPublishService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void publish(Object object) {
        jmsTemplate.convertAndSend("student", parseObject(object));
    }

    private String parseObject(Object object) {
        String string = null;
        try {
            string = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("JsonException: ", e);
        }
        return string;
    }
}
