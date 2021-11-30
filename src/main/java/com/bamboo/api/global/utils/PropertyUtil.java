package com.bamboo.api.global.utils;

import com.bamboo.api.global.config.environment.ApplicationContextService;
import com.bamboo.api.global.exception.errors.codes.PropertyIsUndefinedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;
import org.springframework.context.ApplicationContext;

@Slf4j
public class PropertyUtil {

    public static String getProperty(String propertyName) throws PropertyIsUndefinedException {

        ApplicationContext applicationContext = ApplicationContextService.getApplicationContext();

        String value = applicationContext.getEnvironment().getProperty(propertyName);

        if (TextUtils.isEmpty(value)) {

            String errorMessage = propertyName + "가 정의되지 않았습니다";
            log.error(errorMessage);
            throw new PropertyIsUndefinedException(errorMessage);
        }

        return value;
    }
}
