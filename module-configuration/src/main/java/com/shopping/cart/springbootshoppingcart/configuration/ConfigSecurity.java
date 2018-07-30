package com.shopping.cart.springbootshoppingcart.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable();
        httpSecurity
                .authorizeRequests()
                .antMatchers(
                        "/admin/orderList",
                        "/admin/order",
                        "/admin/accountInfo",
                        "/product",
                        "/productImage",
                        "/show_product",
                        "/add_product",
                        "/update_product")
                    .access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");
        httpSecurity
                .authorizeRequests()
                    .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        httpSecurity
                .authorizeRequests()
                    .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/accountInfo")
                .failureUrl("/admin/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                    .and()
                .logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/");
    }
}
