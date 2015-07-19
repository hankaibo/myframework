/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.log;

import java.lang.annotation.*;

/**
 * 自定义方法注解。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyMethodAnno {

    /**
     * 方法描述
     *
     * @return
     */
    String description() default "no description";

    /**
     * 是否进行性能测试
     *
     * @return
     */
    boolean value() default true;
}
