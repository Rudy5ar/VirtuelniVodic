package com.pmf.pris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EntityScan("model")
@SpringBootApplication
public class VirtuelniVodicApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtuelniVodicApplication.class, args);
	}

}
