package com.homik.config;

import com.homik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Pawel on 2017-03-06.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final int PASSWORD_STRENGHT = 10;


    @Autowired
    private UserService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("1234").roles("USER");

        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder(PASSWORD_STRENGHT));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()    //konfiguracja które requesty mają być autoryzowane a które nie
                    .antMatchers("/login").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/resources/**").permitAll()
//                    .antMatchers("/users", "/create-user", "/book-create", "/books/edit/**", "/users/edit/**", "/books/delete/*", "/users/delete/*").hasRole("ADMIN")
//                    .antMatchers("/users/", "/create-user/**", "/book-create", "/books/edit/**", "/books/delete/*").hasRole("ADMIN")
                    .mvcMatchers("/users", "/create-user", "/book-create", "/books/edit/**", "/books/delete/*").hasRole("ADMIN")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").authenticated()
                    .and()
                .formLogin()            //konfiguracja formularza logowania
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginPage("/login")
                    .loginProcessingUrl("/login")  //adres na ktory wysylamy POST z formularza logowania
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .and()
                .csrf() //cross site request forgery
                    .disable();
    }
}
