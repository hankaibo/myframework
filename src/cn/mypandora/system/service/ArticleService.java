/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.service 
 * @ClassName: ArticleService 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-9 上午10:05:01 
 *
 */
package cn.mypandora.system.service;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.Article;

/**
 * @ClassName: ArticleService
 * @Description: 文章管理Service。
 * @Author: kaibo
 * @date: 2014-5-9
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-9 上午10:05:01
 * @UpdateRemark: What is modified?
 */
public interface ArticleService {
    /**
     * @Title: saveArticle
     * @Description: 保存文章。
     * @param article
     * @return void
     */
    void saveArticle(Article article);

    /**
     * @Title: deleteArticle
     * @Description: 按主键删除文章(物理)。
     * @param id
     * @return void
     */
    void deleteArticle(Long id);

    /**
     * @Title: updateArticle
     * @Description: 修改文章。
     * @param article
     * @return void
     */
    void updateArticle(Article article);

    /**
     * @Title: findArticleById
     * @Description: 查找一篇文章。
     * @param id
     * @return
     * @return Article
     */
    Article findArticleById(Long id);

    /**
     * @Title: findPageArticleByCondition
     * @Description: 根据条件分页查询文章。
     * @param string
     * @param object
     * @param page
     * @return
     * @return Page<UploadFile>
     */
    Page<Article> findPageArticleByCondition(String string, Object object, Page<Article> page);

}
