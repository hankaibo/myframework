/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.system.po 
 * @ClassName: BaseUser 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014年3月7日 下午4:18:43 
 *
 */
package cn.mypandora.system.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.mypandora.orm.model.BaseEntity;

/**
 * @ClassName: BaseUser
 * @Description: 用户实体域对象。
 * @Author: kaibo
 * @date: 2014年3月7日
 * @UpdateUser: kaibo
 * @UpdateDate: 2014年3月7日 下午4:18:43
 * @UpdateRemark: What is modified?
 */
public class BaseUser extends BaseEntity {

    private static final long serialVersionUID = 1866721052326765131L;

    /** @Fields username :姓名 */
    private String username;
    /** @Fields password :密码 */
    private String password;
    /** @Fields realName :真实姓名 */
    private String realName;
    /** @Fields sex :性别 */
    private Integer sex;
    /** @Fields birthday :生日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /** @Fields lastIp :最后登录IP */
    private String lastIp;
    /** @Fields lastVisit :最后登录日期 */
    private Date lastVisit;
    /** @Fields credits :积分 */
    private int credits;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     *            the realName to set
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the lastIp
     */
    public String getLastIp() {
        return lastIp;
    }

    /**
     * @param lastIp
     *            the lastIp to set
     */
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    /**
     * @return the lastVisit
     */
    public Date getLastVisit() {
        return lastVisit;
    }

    /**
     * @param lastVisit
     *            the lastVisit to set
     */
    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    /**
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits
     *            the credits to set
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

}
