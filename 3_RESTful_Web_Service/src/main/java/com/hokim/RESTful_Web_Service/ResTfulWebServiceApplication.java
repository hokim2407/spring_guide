package com.hokim.RESTful_Web_Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ResTfulWebServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(ResTfulWebServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ResTfulWebServiceApplication.class, args);
	}

	// RestTemplate : Rest Api 호출, 응답을 받을 때까지 기다림
	// 		getForObject: HTTP GET으로 객체를 반환받는다
	// RestTemplateBuilder : RestTemplate를 안전하게 생성
	// 빈 이름은 소문자로 시작하고 이후로 카멜 케이스로 사용함
	// 따라서 이후에 RestTemplate을 쓰면 아래에서 선언한 restTemplate을 사용하게 되는 것
	//https://docs.spring.io/spring-framework/docs/5.2.3.RELEASE/spring-framework-reference/core.html#beans-beanname
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
	{
		return builder.build();
	}

	// CommandLineRunner: 앱이 구동되자마자 내부 코드를 실행
	@Bean
	public CommandLineRunner run (RestTemplate restTemplate) throws Exception{
		return args -> {
			Quote quote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}

}
