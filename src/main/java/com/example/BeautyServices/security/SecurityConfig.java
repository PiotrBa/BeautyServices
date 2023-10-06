package com.example.BeautyServices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/reservations").hasRole("USER")
                .antMatchers("/reservations/add").hasRole("USER")
                .antMatchers("/reservations/edit").hasRole("USER")
                .antMatchers("/reservations/delete").hasRole("USER")
                .antMatchers( "/services/*").hasRole("ADMIN")
                .antMatchers("/customers/*").hasRole("ADMIN")

                .antMatchers("/register/customer").permitAll()
                .antMatchers("/register/admin").permitAll()
                .antMatchers("favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/reservations")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .build();
    }
}
