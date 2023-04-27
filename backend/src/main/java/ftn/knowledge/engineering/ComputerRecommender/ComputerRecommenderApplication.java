package ftn.knowledge.engineering.ComputerRecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ComputerRecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputerRecommenderApplication.class, args);
	}

}
