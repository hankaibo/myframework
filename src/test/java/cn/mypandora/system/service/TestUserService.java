package cn.mypandora.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

/**
 * @ClassName:TestUserService
 * @Description:用户JUnit测试类。
 * @Author:hankaibo
 * @date:2013-8-14
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-14 下午11:44:58
 * @UpdateRemark:What is modified?
 */
// 1基于JUnit4的Spring测试框架
@RunWith(SpringJUnit4ClassRunner.class)
// 2启动Spring容器
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestUserService {
    // 3注入Spring容器中的Bean
    @Resource
    private BaseUserService userService;

    // 4标注测试方法
    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(!b1);
        assertTrue(!b2);
    }

//    @Test
//    public void findUserByUserName() {
//        BaseUser user = userService.findUserByUsername("admin");
//        assertEquals(user.getUsername(), "admin");
//    }

//    @Test
//    public void loginSuccess() {
//        BaseUser user = new BaseUser();
//        user.setId(1L);
//        user.setUsername("admin");
//        user.setPassword("123456");
//
//        userService.loginSuccess(user);
//
//    }
//
//    @Test
//    public void delUser() {
//        userService.deleteUser(23L);
//    }

}
