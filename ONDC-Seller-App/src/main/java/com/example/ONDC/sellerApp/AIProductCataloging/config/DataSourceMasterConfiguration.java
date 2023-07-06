package com.example.ONDC.sellerApp.AIProductCataloging.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.master"})
public class DataSourceMasterConfiguration {

  @Bean
  @Primary
  @ConfigurationProperties("db.master")
  public DataSourceProperties masterDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("db.master")
  public HikariDataSource masterDataSource() {
    return masterDataSourceProperties()
      .initializeDataSourceBuilder()
      .type(HikariDataSource.class)
      .build();
  }

  @Bean(name = "entityManagerFactory")
  @Primary
  public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(
    EntityManagerFactoryBuilder builder) {
    return builder.dataSource(masterDataSource()).packages("com.example.ONDC.sellerApp.ONDCSellerApp.db.entity").build();
  }

  @Bean(name = "transactionManager")
  @Primary
  public PlatformTransactionManager transactionManager(
    @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
