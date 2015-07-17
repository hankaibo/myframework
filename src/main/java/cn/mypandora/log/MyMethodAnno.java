/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: MyMethodAnno
 * @Description: 自定义方法注解。
 * @Author: kaibo
 * @date: 2014-4-27
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-27 下午2:48:09
 * @UpdateRemark: What is modified?
 *//**
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
     * @Title: description
     * @Description: 方法描述
     * @return
     * @return String
     */
    String description() default "no description";

    /**
     * @Title: value
     * @Description: 是否进行性能测试
     * @return
     * @return boolean
     */
    boolean value() default true;
}
