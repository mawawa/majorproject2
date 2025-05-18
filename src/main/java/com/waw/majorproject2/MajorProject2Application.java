package com.waw.majorproject2;

import com.waw.majorproject2.models.NokthulaAI;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MajorProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(MajorProject2Application.class, args);
		}

	@Bean
	ChatClient chatClient(ChatClient.Builder builder){
		return builder.build();
	}
	@Bean
	NokthulaAI nokthulaAI(ChatClient chatClient){
		NokthulaAI nokthulaAI = new NokthulaAI();
		return new NokthulaAI();
	}









}
