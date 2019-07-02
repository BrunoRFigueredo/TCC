package com.bruno.pictatture.configuration.oauth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class OAuthResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable(); 
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/imagens/uploadFile").permitAll()
                .antMatchers("/dados/cliente").permitAll()
                .antMatchers("/coments/tatuador").permitAll()
                .antMatchers("/orcamentos/insert").permitAll()
                .antMatchers("/coments/insert").permitAll()
                  .antMatchers("/orcamentos/cliente").permitAll()
                  .antMatchers("/orcamentos/tatuador").permitAll()
                 .antMatchers("/dados/tatuador").permitAll()
                .antMatchers("/dados/uploadFile").permitAll()
                 .antMatchers("/tatuador/uploadFile").permitAll()
                .antMatchers("/users/uploadFile").permitAll()
                .antMatchers("/imagens/tatuador").permitAll()
                  .antMatchers("/users/foto").permitAll()
                .antMatchers("/api/users/tatuadores").permitAll()
                .antMatchers("/imagens/downloadFile/{id}").permitAll()
                    .antMatchers("/downloadFile").permitAll()
                .antMatchers("/imagens/uploadFile/downloadFile/").permitAll()
                .antMatchers("/imagens/uploadMutipleFiles").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/tatuador/{id}").permitAll()
                .antMatchers("/dados/{id}").permitAll()
                .antMatchers("/users/current").permitAll()
                .antMatchers("/users/{id}").permitAll()
                .antMatchers("/clientes/{id}").permitAll()
                .antMatchers("/enderecos").permitAll()
                .antMatchers(HttpMethod.POST,"/tatuador").permitAll()
                .antMatchers("/token/revoke").permitAll()
                .antMatchers("/**").authenticated();
                
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        UrlBasedCorsConfigurationSource urlBasedConfig = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin,Access-Control-Request-Method,Origin,Authorization,Content-Type".split(",")));
        config.setAllowedMethods(Arrays.asList("GET,PUT,POST,DELETE,OPTIONS".split(",")));

        urlBasedConfig.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(urlBasedConfig));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
