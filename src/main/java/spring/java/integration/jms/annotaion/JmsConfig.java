package spring.java.integration.jms.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author rafael.guterres
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JmsConfig {
    String connectionFactoryJndi();
    String destinationJndi();
    String messageSelector() default "";
    boolean enableTransaction() default true;
    boolean enableListener() default true;
}
