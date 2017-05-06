package com.homik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration  // klasa będzie brana pod uwage przez springa przy konfiguracji
@PropertySource(value = {"classpath:hibernate.properties"}) // pomocnicza adnotacja, classpath gdzies w projekcie
@EnableJpaRepositories(basePackages = "com.homik.dao") // ze spring.data , punkty do kontaktu z baza danych
public class HibernateConfig {  // nazwa tej klasy jest dowolna

    @Autowired
    private Environment environment;  // w tej zmiennej snajda sie wartosci, ktore przypisalismy do hibernate.properties

    // 1. utworzenie DataSource

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver.class.name"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.user.name"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));

        return dataSource;
    }

    // 2. EntityManagerFactory pochodzi ze specyfikacji jpa

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.put("hibernate.generate_statistics", environment.getProperty("hibernate.generate_statistics"));

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.homik.model");
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJpaProperties(properties);
        factoryBean.setDataSource(dataSource());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    // 3. PlatformTransactionManager
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }


}
