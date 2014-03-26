/**   
 * @ProjectName: MyFramework
 * @Package: cn.littleprincess.system.po 
 * @ClassName: BaseLog 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-10 下午11:31:38 
 *
 */
package cn.littleprincess.system.po;

import java.util.Date;

import cn.littleprincess.orm.model.BaseEntity;

/**
 * @ClassName:BaseLog
 * @Description:TODO
 * @Author:hankaibo
 * @date:2013-8-13
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-13 下午11:32:27
 * @UpdateRemark:What is modified?
 */
public class BaseLog extends BaseEntity {
    private static final long serialVersionUID = 2269491304854735727L;

    private Long userId;
    private String ip;
    private Date loginDate;

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the loginDate
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * @param loginDate
     *            the loginDate to set
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

}
