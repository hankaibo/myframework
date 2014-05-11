/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.po 
 * @ClassName: Article 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-9 上午9:31:19 
 *
 */
package cn.mypandora.system.po;

import java.sql.Date;
import java.sql.Timestamp;

import cn.mypandora.orm.model.BaseEntity;

/**
 * @ClassName: Article
 * @Description: 文章内容实体域对象。
 * @Author: kaibo
 * @date: 2014-5-9
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-9 上午9:31:19
 * @UpdateRemark: What is modified?
 */
public class Article extends BaseEntity {

    /** @Fields serialVersionUID :序列化 */
    private static final long serialVersionUID = -6539419682750457372L;

    /** @Fields title :文章标题 */
    private String title;
    /** @Fields author :文章作者 */
    private String author;
    /** @Fields time :文章写作时间 */
    private Timestamp time;
    /** @Fields content :文章内容 */
    private String content;
    /** @Fields picturePath :文章中图片路径 */
    private String picturePath;
    /** @Fields isTop :文章是否置顶 */
    private Integer isTop;
    /** @Fields topDays :文章置顶天数 */
    private Integer topDays;
    /** @Fields topEndDate :文章置顶结束日期 */
    private Date topEndDate;
    /** @Fields isRed :文章是否标红 */
    private Integer isRed;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the picturePath
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * @param picturePath
     *            the picturePath to set
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * @return the isTop
     */
    public Integer getIsTop() {
        return isTop;
    }

    /**
     * @param isTop
     *            the isTop to set
     */
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    /**
     * @return the topDays
     */
    public Integer getTopDays() {
        return topDays;
    }

    /**
     * @param topDays
     *            the topDays to set
     */
    public void setTopDays(Integer topDays) {
        this.topDays = topDays;
    }

    /**
     * @return the topEndDate
     */
    public Date getTopEndDate() {
        return topEndDate;
    }

    /**
     * @param topEndDate
     *            the topEndDate to set
     */
    public void setTopEndDate(Date topEndDate) {
        this.topEndDate = topEndDate;
    }

    /**
     * @return the isRed
     */
    public Integer getIsRed() {
        return isRed;
    }

    /**
     * @param isRed
     *            the isRed to set
     */
    public void setIsRed(Integer isRed) {
        this.isRed = isRed;
    }

}
