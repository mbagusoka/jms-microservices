package com.liluri.jmsrouter.controller;

import com.liluri.jmsrouter.dto.StudentDTO;
import com.liluri.jmsrouter.service.PublishService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    private static final String SUCCESS = "success";
    private static final String PARAM_REQ = "parameter required";

    private final PublishService publishService;

    public StudentRestController(@Qualifier("student") PublishService publishService) {
        this.publishService = publishService;
    }

    @PostMapping
    public String postStudent(@RequestBody StudentDTO studentDTO) {
        if (null != studentDTO.getName()) {
            publishService.publish(studentDTO);
        } else {
            throw new InvalidParameterException(PARAM_REQ);
        }

        return SUCCESS;
    }
}
