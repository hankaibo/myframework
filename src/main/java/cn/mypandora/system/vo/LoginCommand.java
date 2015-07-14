package cn.mypandora.system.vo;

/**
 * @ClassName:LoginCommand
 * @Description:登录页面PO。
 * @Author:hankaibo
 * @date:2013-8-15
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-15 上午1:17:56
 * @UpdateRemark:What is modified?
 */
public class LoginCommand {
    private String username;
    private String password;
    private String kaptcha;
    private boolean rememberMe;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
