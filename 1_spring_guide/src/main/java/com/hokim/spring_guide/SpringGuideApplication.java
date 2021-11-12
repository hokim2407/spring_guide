package com.hokim.spring_guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication은 @Configuration, @EnableAutoConfiguration, @ComponentScan을 모두 가지고 있음
// @Configuration: 이 클래스에서 bean을 정의
// @EnableAutoConfiguration: classpath 세팅에 기반해 빈을 추가
// @ComponentScan: 홈 패키지 (com/hokim)에서 다른 컴포넌트, 설정을 탐색해 컨트롤러를 찾음
@SpringBootApplication
public class SpringGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGuideApplication.class, args);
	}

}

// gradlew bootRun 으로 실행
// gradlew build 후 java -jar build/libs/gs-rest-service-0.1.0.jar 으로 실행
