/**   
 * @ProjectName: MyFramework
 * @Package: cn.littleprincess.system.po 
 * @ClassName: BaseUser 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014年3月7日 下午4:18:43 
 *
 */
package cn.littleprincess.system.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.littleprincess.orm.model.BaseEntity;

/**
 * @ClassName: BaseUser
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014年3月7日
 * @UpdateUser: kaibo
 * @UpdateDate: 2014年3月7日 下午4:18:43
 * @UpdateRemark: What is modified?
 */
public class BaseUser extends BaseEntity {

    private static final long serialVersionUID = 1866721052326765131L;

    private String username;
    private String password;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String lastIp;
    private Date lastVisit;
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
