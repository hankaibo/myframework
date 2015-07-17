/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseEntity;
import cn.mypandora.util.MyDateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 用户实体域对象。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class BaseUser extends BaseEntity {
    private static final long serialVersionUID = 1866721052326765131L;

    // 姓名
    private String username;
    // 密码
    private String password;
    // 盐
    private String salt;
    // 是否锁定
    private Boolean locked = Boolean.FALSE;
    // 真实姓名
    private String realName;
    // 邮箱
    private String email;
    // 电话
    private String phone;
    // 手机
    private String mobile;
    // 头像地址
    private String pictureUrl;
    // 性别
    private Integer sex;
    // 生日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /*
     * 字符串生日,该属性值不会存储到数据库中。
     * 因为前台采用的jqgrid主要是配合PHP的，PHP的Date类型时间戳是10位，而Java是13位，
     * 这样就导致了前台转换时出现错误，在不修改源码的情况下，故多加一个属性。
     */
    private String strBirthday;
    // 最后登录IP
    private String lastIp;
    // 最后登录日期
    private Timestamp lastVisit;
    // 同上。
    private String strLastVisit;
    // 积分
    private int credits;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Timestamp getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Timestamp lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getStrBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat(MyDateUtils.DATE_FORMAT);
        return sdf.format(this.getBirthday() != null ? this.getBirthday() : new Date(System.currentTimeMillis()));
    }

    public String getStrLastVisit() {
        SimpleDateFormat sdf = new SimpleDateFormat(MyDateUtils.TIME_FORMAT);
        return sdf.format(this.getLastVisit() != null ? this.getLastVisit() : new Timestamp(0L));
    }
}
