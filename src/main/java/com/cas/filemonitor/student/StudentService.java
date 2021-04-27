package com.cas.filemonitor.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Student> getStudents() {
        return Stream.of(
                new Student("1", "Sunil", "Dinnapur"),
                new Student("2", "Anil", "Dinnapur"),
                new Student("3", "Nandu", "Dinnapur"),
                new Student("3", "Bandu", "Dinnapur")
        ).collect(Collectors.toList());
    }
}
