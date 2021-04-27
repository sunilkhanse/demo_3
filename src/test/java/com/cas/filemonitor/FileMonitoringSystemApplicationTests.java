package com.cas.filemonitor;

import com.cas.filemonitor.student.Student;
import com.cas.filemonitor.student.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = Stude)
class FileMonitoringSystemApplicationTests {

    @Autowired
    private StudentService studentService;

	@Test
	void contextLoads() {
        System.out.println("in test method");
        Student student = this.studentService.registerStudent("123", "Sunil Khanse", "Warje, Pune");
        Assert.notNull(student, "Noll pointer");
	}

}
