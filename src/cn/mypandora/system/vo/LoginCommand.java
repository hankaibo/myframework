/**   
 * @ProjectName:MySpring
 * @Package:cn.mypandora.web 
 * @ClassName:LoginCommand 
 * @Description:TODO
 * Copyright © 2013东软集团股份有限公司. All rights reserved.
 * @Author:hankaibo
 * @CreateDate: 2013-8-15 上午1:17:56 
 * @Version:v1.0
 *
 */
package cn.mypandora.system.vo;

/**
 * @ClassName:LoginCommand
 * @Description:TODO
 * @Author:hankaibo
 * @date:2013-8-15
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-15 上午1:17:56
 * @UpdateRemark:What is modified?
 */
public class LoginCommand {
    private String userName;
    private String password;
    private String kaptcha;
    private boolean rememberMe;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

}
