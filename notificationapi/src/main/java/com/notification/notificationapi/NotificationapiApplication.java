package com.notification.notificationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class NotificationapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationapiApplication.class, args);
	}

}
