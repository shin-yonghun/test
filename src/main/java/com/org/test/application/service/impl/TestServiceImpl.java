package com.org.test.application.service.impl;

import com.org.test.application.model.entity.Student;
import com.org.test.application.model.repository.StudentRepository;
import com.org.test.application.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public void transactionalTest() {
        Student s1 = new Student();
        s1.setName("test");
        s1.setAge(19);
        s1.setGender(1);
        studentRepository.save(s1);
    }

    public int trans1(){
        return 1;
    }

    private int trans2(){
        return 2;
    }
}
