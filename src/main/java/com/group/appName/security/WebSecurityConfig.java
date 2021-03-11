package com.group.appName.security;


import com.group.appName.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/file/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(HttpMethod.GET,  "/api/user/get/{userName}").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(HttpMethod.GET,  "/api/user/get/all").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST,  "/api/user/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT,  "/api/user/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE,  "/api/user/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated().and()
                .httpBasic().and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/api/file/get/all").and()
                .logout().permitAll()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles(Role.ADMIN.name())
                    .build(),
                User.builder()
                    .username("user")
                    .password(passwordEncoder().encode("user"))
                    .roles(Role.USER.name())
                    .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
