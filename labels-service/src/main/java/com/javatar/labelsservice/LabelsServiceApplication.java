package com.javatar.labelsservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync
@EnableRabbit
public class LabelsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabelsServiceApplication.class, args);
	}

}
