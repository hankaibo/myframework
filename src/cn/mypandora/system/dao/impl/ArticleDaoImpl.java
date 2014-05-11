/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.dao.impl 
 * @ClassName: ArticleDaoImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-9 上午10:10:19 
 *
 */ 
package cn.mypandora.system.dao.impl;

import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.impl.BaseEntityDaoImpl;
import cn.mypandora.system.dao.ArticleDao;
import cn.mypandora.system.po.Article;

/**
 * @ClassName: ArticleDaoImpl
 * @Description: 文章管理DAO实现类。
 * @Author: kaibo
 * @date: 2014-5-9
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-9 上午10:10:19
 * @UpdateRemark: What is modified?
 */
@Repository
public class ArticleDaoImpl extends BaseEntityDaoImpl<Article> implements ArticleDao {

    //@formatter:off
    /* (非 Javadoc)
     * Title: getNameSpace
     * Description:
     * @return
     * @see cn.mypandora.orm.dao.impl.BaseEntityDaoImpl#getNameSpace()
     */
    //@formatter:on
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.articleEntity";
    }


}
