package com.group;

import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.MySQLContainer;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class FileTestConfiguration {

    @Bean
    @Primary
    public MySQLContainer embeddedMysqlDb(@Value("${spring.datasource.username}") String login,
                                          @Value("${spring.datasource.password}") String password,
                                          @Value("${db.name}") String dbName) {
        MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0")
                .withDatabaseName(dbName)
                .withUsername(login)
                .withPassword(password);

        mySQLContainer.start();
        return mySQLContainer;
    }

    @DependsOn("embeddedMysqlDb")
    @Bean
    @Primary
    public DataSource dataSource (MySQLContainer mySQLContainer){
        String jdbcUrl = mySQLContainer.getJdbcUrl();
        String username = mySQLContainer.getUsername();
        String password = mySQLContainer.getPassword();

        return new DriverDataSource(jdbcUrl, "com.mysql.cj.jdbc.Driver", new Properties(), username, password);
    }
}

