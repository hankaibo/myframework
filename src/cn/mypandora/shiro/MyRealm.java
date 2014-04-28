/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.shiro 
 * @ClassName: MyRealm 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-22 下午6:06:30 
 *
 */
package cn.mypandora.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.mypandora.system.po.BaseUser;
import cn.mypandora.system.service.BaseUserService;

/**
 * @ClassName: MyRealm
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-4-22
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-22 下午6:06:30
 * @UpdateRemark: What is modified?
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private BaseUserService baseUserService;

    //@formatter:off
    /* (非 Javadoc)
     * Title: doGetAuthorizationInfo
     * Description: 验证当前Subject所拥有的权限，且给其授权。
     * @param arg0
     * @return
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    //@formatter:on
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        return null;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: doGetAuthenticationInfo
     * Description:验证当前登录的Subject
     * @param authcToken
     * @return
     * @throws AuthenticationException
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    //@formatter:on
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 获取基于用户名和密码的令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        BaseUser user = baseUserService.findUserByUsername(token.getUsername());
        if (null != user) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getUsername());
        } else {
            return null;
        }
    }

//    /**
//     * 将一些数据放到ShiroSession中,以便于其它地方使用
//     * 
//     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
//     */
//    private void setSession(Object key, Object value) {
//        Subject currentUser = SecurityUtils.getSubject();
//        if (null != currentUser) {
//            Session session = currentUser.getSession();
//            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
//            if (null != session) {
//                session.setAttribute(key, value);
//            }
//        }
//    }

}
