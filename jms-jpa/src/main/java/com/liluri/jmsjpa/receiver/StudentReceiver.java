package com.liluri.jmsjpa.receiver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liluri.jmsjpa.dto.StudentDTO;
import com.liluri.jmsjpa.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StudentReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentReceiver.class);

    private final StudentService studentService;

    @Autowired
    public StudentReceiver(StudentService studentService) {
        this.studentService = studentService;
    }

    @JmsListener(destination = "student")
    public void receive(String data) {
        studentService.save(parseToDTO(data));
    }

    private StudentDTO parseToDTO(String data) {
        StudentDTO studentDTO = new StudentDTO();
        try {
            studentDTO = new ObjectMapper().readValue(data, StudentDTO.class);
        } catch (JsonParseException | JsonMappingException e) {
            LOGGER.error("JsonException: ", e);
        } catch (IOException e) {
            LOGGER.error("IOException: ", e);
        }
        return studentDTO;
    }
}
