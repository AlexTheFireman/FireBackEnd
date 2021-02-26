package com.group.appName.config;

import com.group.appName.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.transaction.TransactionDefinition.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
//            .antMatchers(HttpMethod.POST,"/api/auth/**")
//            .hasAnyRole("ADMIN", "USER")
//            .antMatchers("/api/user/*").hasRole("ADMIN")
            .anyRequest()
                .authenticated()
                .and()
                .formLogin()
//            .and()
//            .formLogin()
//                .loginPage("/login");
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher( "/api/logout", "POST"))
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/");
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

