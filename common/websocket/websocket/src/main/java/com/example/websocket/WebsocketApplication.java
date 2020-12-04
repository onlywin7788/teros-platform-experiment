package com.example.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WebsocketApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WebsocketApplication.class, args);


		// markdown file to html
		MdToHtml mdToHtml = new MdToHtml();
		mdToHtml.test("C:\\Users\\jiyoung\\Downloads\\spring-boot-websocket-chat-demo-master\\spring-boot-websocket-chat-demo-master\\11111.md");


	}

}
