package com.cas.filemonitor.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student registerStudent(String id, String name, String address) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAddress(address);
        if (this.studentRepository == null) {
            throw new NullPointerException("Repository is null");
        }
        return student;
    }
}
