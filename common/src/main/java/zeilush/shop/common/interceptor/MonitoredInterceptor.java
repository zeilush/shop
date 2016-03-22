package zeilush.shop.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * Created by AAA on 17.03.2016.
 */
@Monitored
@Interceptor
public class MonitoredInterceptor implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(MonitoredInterceptor.class);

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        long start = System.currentTimeMillis();

        logger.info("Method Invocation: {}", ic.getMethod().getName());

        try {
            return ic.proceed();
        } finally {
            logger.info("Method Invocation: {}", ic.getMethod().getName());
        }
    }
}
