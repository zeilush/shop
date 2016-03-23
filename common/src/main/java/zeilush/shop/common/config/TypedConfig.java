package zeilush.shop.common.config;

import org.apache.deltaspike.partialbean.api.PartialBeanBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@PartialBeanBinding
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TypedConfig {
}
