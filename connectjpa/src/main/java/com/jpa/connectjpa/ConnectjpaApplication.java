package com.jpa.connectjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ConnectjpaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ConnectjpaApplication.class);
	private static ApplicationContext appContext;
	
	public static void main(String[] args) {
		SpringApplication.run(ConnectjpaApplication.class, args);

//		ApplicationContext appContext = new AnnotationConfigApplicationContext(ConnectjpaApplication.class);

//		appContext = SpringApplication.run(ConnectjpaApplication.class, args);
//
//		String[] allBeanNames = appContext.getBeanDefinitionNames();
//		for(String beanName : allBeanNames) {
//			System.out.println(beanName);
//		}

	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			repository.save(new Customer("Test", "Fer"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
