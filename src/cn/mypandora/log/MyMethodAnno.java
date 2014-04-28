/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.log 
 * @ClassName: MyMethodAnno 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-27 下午2:48:09 
 *
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
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyMethodAnno {
//    /** 
//     * @Title: moduleName
//     * @Description: 模块名称
//     * @return
//     * @return String
//     */
//    String moduleName();
//    /** 
//     * @Title: logType
//     * @Description: 日志类别
//     * @return
//     * @return String
//     */
//    String logType();
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
