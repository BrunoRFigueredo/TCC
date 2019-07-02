package com.bruno.pictatture.configuration.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class OAuthTokenStoreConfiguration {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource tokenDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setSchema("auth_owner");

        return dataSource;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(tokenDataSource());
    }
}
