package br.com.cd2tec.sigabem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SigaBemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigaBemApplication.class, args);
	}

}
