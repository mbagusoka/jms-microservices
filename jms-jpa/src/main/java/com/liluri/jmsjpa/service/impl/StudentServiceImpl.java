package com.liluri.jmsjpa.service.impl;

import com.liluri.jmsjpa.dto.StudentDTO;
import com.liluri.jmsjpa.entity.Student;
import com.liluri.jmsjpa.repository.StudentRepository;
import com.liluri.jmsjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(StudentDTO studentDTO) {
        studentRepository.save(new Student(studentDTO));
    }
}
