package com.sparta.weeklytestspring.security;

import com.sparta.weeklytestspring.controller.JwtAuthenticationEntryPoint;
import com.sparta.weeklytestspring.controller.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/article").permitAll()
                .antMatchers("/articles").permitAll()
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/user/idcheck").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/").permitAll()
//                .antMatchers("/**.html").permitAll()

                //그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated()
                .and()
                //jwt 인증 실패 -> authenticationEntrypoint
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                //Stateleess
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/user/login")
//                .failureUrl("/index.html")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .permitAll();

        //filter 란? controller 에서 API 호출 시, 필터링 하는 기능임. 이 request 에서 필터링 한다..
        //ex. header에 authorization key 있는지 체크하려면? 접속한 url 이나 아이피 체크하고 싶을 떄 쓴다.
        //UsernamePasswordAuthenticationFilter: username 과 password 세션이 있는지 체크. JWT 토큰이 있는 사람은 이 필터에서 세션 만들어줌.
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }


}