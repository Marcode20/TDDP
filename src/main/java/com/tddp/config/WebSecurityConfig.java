package com.tddp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//
//@Configuration
//@EnableWebSecurity
//@Order(2)
public class WebSecurityConfig    {

//    private final UserDetailsService userDetailsService;
//
//    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
//
//    @Autowired
//    public void configurationGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        String[] staticResources  =  {
//                "/assets/**",
//                "/css/**",
//                "/images/**",
//                "/js/**",
//                "/h2-console/**",
//                "/registration"
//                ,
//                "/page/**",
//                "/page/about",
//                "/home",
//                "page/**"
//        };

//        http.authorizeRequests()
//                .antMatchers(staticResources).permitAll()
//                .anyRequest().authenticated()
//                //.and()
//                //.csrf().ignoringAntMatchers("/h2-console/**")
//
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/home")
//                .failureForwardUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                .and()
//                .csrf().ignoringAntMatchers("/h2-console/**")
//                .and()
//                .headers().frameOptions().sameOrigin()
//        ;
//        http.authorizeRequests()
//                .antMatchers(staticResources).permitAll()
//                .anyRequest().authenticated()
//                //.and()
//                //.csrf().ignoringAntMatchers("/h2-console/**")
//
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/home")
//                .failureForwardUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                .and()
//                .csrf().ignoringAntMatchers("/h2-console/**")
//                .and()
//                .headers().frameOptions().sameOrigin()
//        ;
//
//    }
}
