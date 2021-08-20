package com.bamboo.api.global.config.environment;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextService implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ApplicationContextService.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext () {
    return applicationContext;
  }
}
