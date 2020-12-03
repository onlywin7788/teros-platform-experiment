package com.example.springexception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class SpringExceptionApplication {

	public static void main(String[] args) {
		try {
			Test test = new Test();
			test.test();
		}
		catch(SampleException e) {
			e.printStackTrace();
			e.getMessage();

			System.out.println(String.format("ReasonCode : [%s], ResonMessage : [%s]", e.getReasonCode(), e.getReasonMessage()));
		}
	}

}
