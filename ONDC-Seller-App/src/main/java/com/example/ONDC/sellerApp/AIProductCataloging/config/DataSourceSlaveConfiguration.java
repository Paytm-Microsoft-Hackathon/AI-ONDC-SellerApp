package com.example.ONDC.sellerApp.AIProductCataloging.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "entityManagerFactorySlave",
  transactionManagerRef = "transactionManagerSlave",
  basePackages = {"com.example.ONDC.sellerApp.ONDCSellerApp.db.repo.slave"})
public class DataSourceSlaveConfiguration {

  @Bean
  @ConfigurationProperties("db.slave")
  public DataSourceProperties slaveDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("db.slave")
  public HikariDataSource slaveDataSource() {
    return slaveDataSourceProperties()
      .initializeDataSourceBuilder()
      .type(HikariDataSource.class)
      .build();
  }

  @Bean(name = "entityManagerFactorySlave")
  public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(
    EntityManagerFactoryBuilder builder) {
    return builder.dataSource(slaveDataSource()).packages("com.example.ONDC.sellerApp.ONDCSellerApp.db.entity").build();
  }

  @Bean(name = "transactionManagerSlave")
  public PlatformTransactionManager transactionManager(
    @Qualifier("entityManagerFactorySlave") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
