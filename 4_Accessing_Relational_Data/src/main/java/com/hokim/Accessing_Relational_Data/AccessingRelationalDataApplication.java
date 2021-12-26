package com.hokim.Accessing_Relational_Data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class AccessingRelationalDataApplication
		//이 Application클래스는 CommandLineRunner를 상속.
		// 즉, 애플리케이션 컨텍스트가 로드된 후 run()을 실행.
		implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(AccessingRelationalDataApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingRelationalDataApplication.class, args);
	}

	// Spring Boot는 H2(메모리 내 관계형 데이터베이스 엔진)를 지원하고 자동으로 연결을 생성.
	// spring-jdbc를 사용하기 때문에 Spring Boot는 자동으로 JdbcTemplate를 생성함.
	// @Autowired JdbcTemplate필드는 자동으로 JdbcTemplate를 로드하고 사용.
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		//DB가 존재하면 삭제
		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");

		//DB가 없으면 생성
		jdbcTemplate.execute("CREATE TABLE customers(" +
				"id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		// 이름과 성을 분리
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" "))
				.collect(Collectors.toList());

		// insert 문을 일괄 실행
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		// 이름이 Josh인 사람 검색해서 출력
		jdbcTemplate.query(
				//sql문
				"SELECT id, first_name, last_name FROM customers WHERE first_name = ?", 
				// sql을 실행한 결과를 맵핑
				new RowMapper<Customer>() {
					@Override
					public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
						Customer member = new Customer(rs.getLong("id"),
								rs.getString("first_name"),
								rs.getString("last_name"));
						return member;
					}
				},
				// ? 에 들어갈 값
				new Object[] { "Josh" }
		)
		// 결과를 로그로 찍기
		.forEach(customer -> log.info(customer.toString()));
	}
}
