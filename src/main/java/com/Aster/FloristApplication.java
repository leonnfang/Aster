package com.Aster;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;


@SpringBootApplication
public class FloristApplication {

	public static void main(String[] args) {

		SpringApplication.run(FloristApplication.class, args);
		System.out.println("Welcome to Aster");
	}
	/*
	@Bean
	public Jdbi jdbi(DataSource dataSource){
		TransactionAwareDataSourceProxy dataSourceProxy =
				new TransactionAwareDataSourceProxy(dataSource);
		Jdbi jdbi = Jdbi.create(dataSourceProxy);
		jdbi.installPlugins();
		jdbi.installPlugin(new H2DatabasePlugin());

		jdbi.registerRowMapper(Order.)
	}
	*/

}
