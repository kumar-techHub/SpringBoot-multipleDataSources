package com.techbee.multipledb.example.contractor;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "contractorEntityManagerFactory", transactionManagerRef = "contractorTransactionManager", basePackages = {
		"com.techbee.multipledb.example.contractor" })
public class ContractorDbConfig {

	@Bean(name = "contractorDatasource")
	@ConfigurationProperties(prefix = "spring.contractor.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	
	@Bean(name = "contractorEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean contractorEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("contractorDatasource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.techbee.multipledb.example.contractor").persistenceUnit("Contractor").build();
	}

	@Bean(name = "contractorTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("contractorEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
