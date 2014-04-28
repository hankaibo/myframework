/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.orm.model 
 * @ClassName: BaseLog 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-4-27 下午2:56:32 
 *
 */
package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseEntity;

/**
 * @ClassName: BaseLog
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-4-27
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-4-27 下午2:56:32
 * @UpdateRemark: What is modified?
 */
public class BaseLog extends BaseEntity {

    private static final long serialVersionUID = 4687184269056883778L;
    private String username;
    private String description;
    private String ip;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

}
