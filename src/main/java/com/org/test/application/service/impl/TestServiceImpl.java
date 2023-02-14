package com.org.test.application.service.impl;

import com.org.test.application.model.entity.Student;
import com.org.test.application.model.repository.StudentRepository;
import com.org.test.application.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final StudentRepository studentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class, // 모든 예외상황에 대해 롤백 (기본적으로 Unchecked Exception, Error 만을 rollback)
            readOnly = false, // 읽기 전용 off
            isolation = Isolation.DEFAULT, // 격리수준
            // DEFAULT : DBMS 의 기본 격리 수준 이용
            // READ_UNCOMMITED (level 0) : Dirty read, Nonrepeatable read, Phantom read 모두발생
            // READ_COMMITED (level 1) : Dirty read 방지, Postgres, SQL Server 및 Oracle의 기본 수준
            // REPEATEABLE_READ (level 2) : Dirty read, Nonrepeatable read 방지, MySQL의 기본 수준, Oracle은 미지원
            // SERIALIZABLE (level 3) : 모든 부작용을 방지, 가장 높은 격리 수준이지만, 동시 호출을 순차적으로 실행하므로 성능 저하의 우려
            propagation = Propagation.REQUIRED) // 전파속성
            // REQUIRED (default) : 활성 트랜잭션이 있는지 확인하고, 아무것도 없으면 새 트랜잭션을 생성
            // SUPPORTS : 활성 트랜잭션이 있는지 확인하고, 있으면 기존 트랜잭션 사용. 없으면 트랜잭션 없이 실행
            // REQUIRES_NEW : 항생 새로운 트랜잭션을 생성한다. 이미 진행중인 트랜잭션이 있다면 잠깐 보류하고 해당 트랜잭션 작업을 먼저 진행
            // NESTED : 부모 트랜잭션이 커밋될 때 같이 커밋, 자식 트랜잭션의 롤백은 부모 트랜잭션에 영향 없음
    public void transactionalTest() {
        Student student = studentRepository.findById(1L).get();
        try{
            //trans1(student);
            trans2(student);
            student.setGender(2);
            studentRepository.save(student);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void trans1(Student student){
        student.setAge(18);
        studentRepository.save(student);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void trans2(Student student){
        student.setAge(17);
        studentRepository.save(student);
        throw new RuntimeException();
    }
}
