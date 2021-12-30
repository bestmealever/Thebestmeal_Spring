package com.example.thebestmeal_test.security;

import com.example.thebestmeal_test.controller.JwtAuthenticationEntryPoint;
import com.example.thebestmeal_test.controller.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/articles/**").permitAll()
                .antMatchers("/article/comment").permitAll()
                .antMatchers("/posting").permitAll()
                .antMatchers("/post").permitAll()
                .antMatchers("/recommend").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/**.html").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/login/kakao").permitAll()
                .antMatchers("/signup/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/post/**").permitAll()
                .antMatchers("/static/images/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/liked/**").permitAll()
                .antMatchers("/liked/count/**").permitAll()
                .antMatchers("thebestmeal.shop/**").permitAll()
                .antMatchers("www.thebestmeal.shop/**").permitAll()
                .antMatchers("backend.thebestmeal.shop/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //jwt 인증 실패 -> authenticationEntryPoint
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .loginPage("/index.html")
                .failureUrl("/index.html")
                .defaultSuccessUrl("/")
                .permitAll()	//허용
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/user/forbidden");	//로그아웃도 허용

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors();

    }
}