package com.gotravel.flightshoppingservice.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Component("scheduleCacheKeyGenerator")
public class ScheduleCacheKeyGenerator implements KeyGenerator {

       public Object generate(final Object target, final Method method, final Object... params) {
           String key =  StringUtils.arrayToDelimitedString(params, "_");
           System.out.println("key is " + key);
           return key;
    }
}
