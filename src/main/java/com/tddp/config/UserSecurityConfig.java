//package com.tddp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import com.tddp.service.UserDetailsServiceImpl;
//
//@Configuration
////@EnableWebSecurity
//@Order(2)
//public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    private final UserDetailsService userDetailsService;
////
////    public UserSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
////        this.userDetailsService = userDetailsService;
////    }
//
//
////
////    @Autowired
////    public BCryptPasswordEncoder bCryptPasswordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
//
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//    @Autowired
//    UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
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
//                "/**"
//        };
//
//        http.authorizeRequests()
//                .antMatchers(staticResources).permitAll()
////                .and()
////        .antMatcher("/**").authorizeRequests()
//                .anyRequest().authenticated() //hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/login").defaultSuccessUrl("/home", true)
//                //.failureForwardUrl("/login?error=true")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/page/userLogin.html?logout").logoutSuccessUrl("/home");
//        http.csrf().ignoringAntMatchers("/h2-console/**").disable();
//
//
//    }
//
//}
