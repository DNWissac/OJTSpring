package com.ojt.movie.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  // 일단 메모리에 떠야 하니까 어노테이션 추가해줌
@EnableWebSecurity
//스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨, 스프링 시큐리티 필터가 SecurityConfig 이것을 말한다.
//지금부터 등록할 필터가 기본 필터에 등록이 된다.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //csrf 토큰 비활성화 (테스트 시 걸어두는 게 좋음)
                .authorizeRequests()

                .antMatchers("/member/**").authenticated() // 해당 주소로 들어오면 인증이 필요함
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")

                .anyRequest().permitAll() //이 3개 주소가 아니라면 권한이 허용된다.
                .and()
                    .formLogin()
                    .loginPage("/signinform")
                    .defaultSuccessUrl("/");
    }
}
