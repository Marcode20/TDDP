package com.tddp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailsService adminDetailsService;


//    public AdminSecurityConfig(/*@Qualifier("userDetailsServiceImpl")*/ UserDetailsService adminDetailsService) {
//        this.adminDetailsService = adminDetailsService;
//    }



//    @Autowired
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    UserDetailsService userDetailsService ;


    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] staticResources  =  {
                "/assets/**",
                "/css/**",
                "/images/**",
                "/js/**",
                "/h2-console/**",
                "/registration"
                ,
                "/page/**",
                "/page/about",
                "/home",
                "page/**"
        };
//        http.antMatcher("/admin/**").authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/admin/login").defaultSuccessUrl("/admin/menu")
//                .failureForwardUrl("/admin/login?error=true")
//                .and()
//                .logout()
//                .logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login")
//                .and()
//                .csrf().disable();
//        ;
//
//        http.antMatcher("/admin/**").authorizeRequests()
//                .anyRequest().authenticated()
//                //.and()
//                //.csrf().ignoringAntMatchers("/h2-console/**")
//
//                .and()
//                .formLogin()
//                .loginPage("/admin/login")
//                .defaultSuccessUrl("/admin/menu")
//                .failureForwardUrl("/admin/login?error=true")
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

        http.authorizeRequests().antMatchers(staticResources).permitAll().and()
                .antMatcher("/admin/**")
                .authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/admin/login")
                .defaultSuccessUrl("/admin/menu", true)
                .permitAll()
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
        http.csrf().ignoringAntMatchers("/h2-console/**").disable();

    }





}
