package com.hokim.Uploading_Files;

import com.hokim.Uploading_Files.storage.StorageProperties;
import com.hokim.Uploading_Files.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class UploadingFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadingFilesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			// 이전에 저장된 값 모두 삭제
			storageService.deleteAll();
			
			// 초기화
			storageService.init();
		};
	}
}
