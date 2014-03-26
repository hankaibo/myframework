package cn.littleprincess.system.service;

/**   
 * @ProjectName:MySpring
 * @Package:cn.littleprincess.service 
 * @ClassName:TestUserService 
 * @Description:TODO
 * Copyright © 2013东软集团股份有限公司. All rights reserved.
 * @Author:hankaibo
 * @CreateDate: 2013-8-14 下午11:44:58 
 * @Version:v1.0
 *
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.littleprincess.system.po.BaseUser;

/**
 * @ClassName:TestUserService
 * @Description:TODO
 * @Author:hankaibo
 * @date:2013-8-14
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-14 下午11:44:58
 * @UpdateRemark:What is modified?
 */
// 1基于JUnit4的Spring测试框架
@RunWith(SpringJUnit4ClassRunner.class)
// 2启动Spring容器
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestUserService {
    // 3注入Spring容器中的Bean
    @Resource
    private BaseUserService userService;

    // 4标注测试方法
    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void findUserByUserName() {
        BaseUser user = userService.findUserByUsername("admin");
        assertEquals(user.getUsername(), "admin");
    }

    @Test
    public void loginSuccess() {
        BaseUser user = new BaseUser();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("123456");

        userService.loginSuccess(user);

    }

}
