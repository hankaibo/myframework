package cn.mypandora.orm.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName:BaseEntity
 * @Description:基础类。
 * @Author:hankaibo
 * @date:2014-1-1
 * @UpdateUser:hankaibo
 * @UpdateDate:2014-1-1 上午11:53:48
 * @UpdateRemark:创建时间与修改时间和微信中相关实体类冲突，故注掉。
 */
public class BaseEntity implements Serializable {

    /** 
     * @Fields serialVersionUID :序列化值。
     */
    private static final long serialVersionUID = -2913220082793071417L;
    /**
     * @Fields id :主键
     */
    private Long id;
    /**
     * @Fields createTime :创建时间
     */
    private Timestamp createTime;
    /**
     * @Fields updateTime :修改时间
     */
    private Timestamp updateTime;

    // ----------setter getter------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}