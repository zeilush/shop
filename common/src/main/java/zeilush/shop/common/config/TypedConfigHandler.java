package zeilush.shop.common.config;

import org.apache.deltaspike.core.api.config.ConfigResolver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by AAA on 23.03.2016.
 */
@TypedConfig
public class TypedConfigHandler implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        String key = method.getName();
        Class<?> configType = method.getReturnType();

        String loadedValue = ConfigResolver.getPropertyValue(key);
        return parseValue(loadedValue, configType);
    }

    private Object parseValue(String loadedValue, Class<?> configType) {
        if (loadedValue != null) {
            if (configType.equals(Integer.class)) {
                return Integer.parseInt(loadedValue);
            } else if (configType.equals(String.class)) {
                return loadedValue;
            } else {
                throw new IllegalStateException(configType.getName() + " isn't supported");
            }
        }
        return null;
    }
}
