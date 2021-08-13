package com.bamboo.api.global.config.auth.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OauthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    System.out.println(request.getParameter("code"));
    System.out.println(response);
    System.out.println(response.getStatus());
    System.out.println(response.getTrailerFields());
    System.out.println(response.getHeaderNames());

//    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
