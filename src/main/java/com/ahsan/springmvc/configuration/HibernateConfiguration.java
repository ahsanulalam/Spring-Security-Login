package com.ahsan.springmvc.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.hibernate.HikariConnectionProvider;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.*;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.ahsan.springmvc.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.ahsan.springmvc.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource dataSource() {
        //DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //HikariDataSource hikariDataSource = new HikariDataSource();
       // dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
       // dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
       // dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
       // dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
 //       return dataSource;


        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getRequiredProperty("hibernate.hikari.dataSourceClassName"));
        hikariConfig.setJdbcUrl(environment.getRequiredProperty("hibernate.hikari.dataSource.url"));
        hikariConfig.setUsername(environment.getRequiredProperty("hibernate.hikari.dataSource.user"));
        hikariConfig.setPassword(environment.getRequiredProperty("hibernate.hikari.dataSource.password"));

        hikariConfig.setMaximumPoolSize(Integer.valueOf(environment.getRequiredProperty("hibernate.hikari.maximumPoolSize")));
        //hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");

        //hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        //hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        //hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        //hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;

    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

