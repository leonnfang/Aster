package com.Aster;

import com.Aster.Model.Customer;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@SpringBootApplication
public class FloristApplication {

	public static void main(String[] args) {
		SpringApplication.run(FloristApplication.class, args);
		System.out.println("Welcome to Aster");
	}

	//TODO money calculation
	//TODO react JS (redux)
	//TODO logging (cookies, jwt tokens)
	//TODO docker, middleware
	//TODO add tags to products
	//TODO S3 photo saving
}
