package com.cas.filemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FileMonitoringSystemApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(FileMonitoringSystemApplication.class, args);
	}

}