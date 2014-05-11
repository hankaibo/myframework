/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.service.impl 
 * @ClassName: ArticleServiceImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-9 上午10:08:20 
 *
 */
package cn.mypandora.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mypandora.orm.Page;
import cn.mypandora.orm.dao.IBaseEntityDao;
import cn.mypandora.orm.service.AbstractBaseEntityOperation;
import cn.mypandora.system.dao.ArticleDao;
import cn.mypandora.system.po.Article;
import cn.mypandora.system.service.ArticleService;

/**
 * @ClassName: ArticleServiceImpl
 * @Description: 文章管理Service实现类。
 * @Author: kaibo
 * @date: 2014-5-9
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-9 上午10:08:20
 * @UpdateRemark: What is modified?
 */
@Service
public class ArticleServiceImpl extends AbstractBaseEntityOperation<Article> implements ArticleService {

    @Resource
    private ArticleDao dao;
    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.mypandora.orm.service.impl.AbstractBaseEntityOperation#getDao()
     */
    //@formatter:on
    @Override
    public IBaseEntityDao<Article> getDao() {
        return dao;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: saveArticle
     * Description:
     * @param article
     * @see cn.mypandora.system.service.ArticleService#saveArticle(cn.mypandora.system.po.Article)
     */
    //@formatter:on
    @Override
    public void saveArticle(Article article) {
        dao.addEntity(article);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: deleteArticle
     * Description:
     * @param id
     * @see cn.mypandora.system.service.ArticleService#deleteArticle(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void deleteArticle(Long id) {
        dao.deleteEntity(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: findPageArticleByCondition
     * Description:
     * @param string
     * @param object
     * @param page
     * @return
     * @see cn.mypandora.system.service.ArticleService#findPageArticleByCondition(java.lang.String, java.lang.Object, cn.mypandora.orm.Page)
     */
    //@formatter:on
    @Override
    public Page<Article> findPageArticleByCondition(String string, Object object, Page<Article> page) {
        return dao.findByCondition(string, object, page);
    }

}
