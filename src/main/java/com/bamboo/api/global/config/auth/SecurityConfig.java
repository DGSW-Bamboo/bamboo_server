package com.bamboo.api.global.config.auth;

import com.bamboo.api.domain.auth.service.UserServiceFindUser;
import com.bamboo.api.global.config.auth.handler.OauthFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@EnableWebSecurity
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final UserServiceFindUser userServiceFindUser;
  private final OauthFailureHandler oauthFailureHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService)
            .and()
            .failureHandler(oauthFailureHandler);

    super.configure(http);
  }
}
