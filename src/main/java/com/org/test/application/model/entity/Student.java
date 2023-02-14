package com.org.test.application.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "student")
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @Column(name = "ssn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ssn;
    @Column(name = "name")
    String name;
    @Column(name = "age")
    Integer age;
    @Column(name = "gender")
    Integer gender;
    @CreationTimestamp()
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;
}
