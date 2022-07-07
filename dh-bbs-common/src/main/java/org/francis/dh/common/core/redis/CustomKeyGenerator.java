package org.francis.dh.common.core.redis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Franc1s
 * @date 2022/7/5
 * @apiNote
 */
@Component
public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return target.toString() + ":" + method.getName() + ":" + Arrays.toString(params);
    }
}
