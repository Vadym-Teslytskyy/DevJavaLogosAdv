package ua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ua.repository.ComponentRepository;

@SpringBootApplication
public class DevJavaSpringBootRestaurantApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DevJavaSpringBootRestaurantApplication.class, args);
		ComponentRepository repository = run.getBean(ComponentRepository.class);
		System.out.println(repository.findAll());
		
	}

}
