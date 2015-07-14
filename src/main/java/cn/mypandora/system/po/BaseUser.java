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

import cn.mypandora.orm.model.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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

    /**
     * @Fields username :姓名
     */
    private String username;
    /**
     * @Fields password :密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;

    private Boolean locked = Boolean.FALSE;
    /**
     * @Fields realName :真实姓名
     */
    private String realName;
    /**
     * @Fields email :邮箱
     */
    private String email;
    /**
     * @Fields phone :电话
     */
    private String phone;
    /**
     * @Fields mobile :手机
     */
    private String mobile;
    /**
     * @Fields pictureUrl :头像地址
     */
    private String pictureUrl;
    /**
     * @Fields sex :性别
     */
    private Integer sex;
    /**
     * @Fields birthday :生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 字符串生日,该属性值不会存储到数据库中。
     * 因为前台采用的jqgrid主要是配合PHP的，PHP的Date类型时间戳是10位，而Java是13位，这样就导致了前台转换时出现错误，在不修改源码的情况下，故多加一个属性。
     */
    private String strBirthday;
    /**
     * @Fields lastIp :最后登录IP
     */
    private String lastIp;
    /**
     * @Fields lastVisit :最后登录日期
     */
    private Timestamp lastVisit;
    /**
     * 同上。
     */
    private String strLastVisit;
    /**
     * @Fields credits :积分
     */
    private int credits;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
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
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }
    /**
     * @return the realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName the realName to set
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the pictureUrl
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * @param pictureUrl the pictureUrl to set
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
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
     * @param birthday the birthday to set
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
     * @param lastIp the lastIp to set
     */
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    /**
     * @return the lastVisit
     */
    public Timestamp getLastVisit() {
        return lastVisit;
    }

    /**
     * @param lastVisit the lastVisit to set
     */
    public void setLastVisit(Timestamp lastVisit) {
        this.lastVisit = lastVisit;
    }

    /**
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits the credits to set
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getStrBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(this.getBirthday() != null ? this.getBirthday() : new Date(System.currentTimeMillis()));
    }

    public String getStrLastVisit() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(this.getLastVisit() != null ? this.getLastVisit() : new Timestamp(0L));
    }
}
