package cn.ycc.api.admin.commons.annotation;

import java.lang.annotation.*;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.26 21:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogInfo {
    String value() default "";
    boolean ignore() default false;
}
