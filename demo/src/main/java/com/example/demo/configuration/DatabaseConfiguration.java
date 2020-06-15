package com.example.demo.configuration;


import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DatabaseConfiguration {
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		
		source.setDriverClassName("oracle.jdbc.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("kh");
		source.setPassword("kh");
		source.setMaxTotal(20);
		source.setMaxIdle(10);
		source.setMaxWaitMillis(3000);
		
		return source;
	}
	
}
