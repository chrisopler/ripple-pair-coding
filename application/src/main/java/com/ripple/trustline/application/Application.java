package com.ripple.trustline.application;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	@Resource(name = "logger")
	private Logger log;
	
	@Value("${peer}")
	private String peer;
	
	@Value("${name}")
	private String name;
	
	@Bean(name = "logger")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	Logger getLogger() {
		return LoggerFactory.getLogger("trustline");
	}

	@Bean(name="trustline")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)

	public TrustLine getTrustline() {
		return new TrustLine();
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	
	public static void main(String[] args) {
		System.out.println("Server Starts...");
		System.out.println("Welcome to Trustine");
		System.out.println("");
		SpringApplication.run(Application.class, args);
	}
	

}
