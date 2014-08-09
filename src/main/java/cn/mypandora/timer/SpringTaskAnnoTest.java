/**   
 * @ProjectName: MyDemo
 * @Package: cn.mypandora.timer.spring 
 * @ClassName: SpringTaskAnnoTest 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-22 下午3:52:29 
 *
 */ 
package cn.mypandora.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SpringTaskAnnoTest
 * @Description: 基于Spring-task注解方式的用例。
 * @Author: kaibo
 * @date: 2014-4-22
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-22 下午3:52:29
 * @UpdateRemark: What is modified?
 */
@Service
public class SpringTaskAnnoTest {
    @Scheduled(cron="0 24 16 * * ?")
    public void job1() {
        System.out.println("Spring-task任务进行中...我是注解我是注解...");
    }
}
