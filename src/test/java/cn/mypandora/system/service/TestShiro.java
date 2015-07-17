package cn.mypandora.system.service;

import cn.mypandora.shiro.realm.UserRealm;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kaibo on 2015/7/15.
 * desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml","/applicationContext-shiro.xml"})
public class TestShiro {
    @Autowired
    private UserRealm userRealm;

    @Test
    public void test() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("8", "8");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());
    }
}
