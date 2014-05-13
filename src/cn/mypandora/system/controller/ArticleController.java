/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.controller 
 * @ClassName: ArticleController 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-9 上午10:47:49 
 *
 */
package cn.mypandora.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mypandora.orm.Page;
import cn.mypandora.system.po.Article;
import cn.mypandora.system.service.ArticleService;

/**
 * @ClassName: ArticleController
 * @Description: 文章管理Controller.
 * @Author: kaibo
 * @date: 2014-5-9
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-9 上午10:47:49
 * @UpdateRemark: What is modified?
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /** 
     * @Title: list
     * @Description: 显示文章列表。
     * @param model
     * @param currentPage
     * @return
     * @return String
     */
    @RequestMapping(value = "/articles",method=RequestMethod.GET)
    public String list(ModelMap model,
            @RequestParam(value = "currentPage", required = true, defaultValue = "1") int currentPage) {
        Page<Article> page = new Page<>();
        page.setCurrentPage(currentPage);
        page = articleService.findPageArticleByCondition("pageArticles", null, page);
        model.put("articles", page.getResultList());
        model.put("page", page);
        return "article/list";
    }

    /**
     * @Title: add
     * @Description: 跳转到添加新文章页面。
     * @return
     * @return String
     */
    @RequestMapping(method=RequestMethod.GET)
    public String add() {
        return "article/add";
    }
    
    /** 
     * @Title: add
     * @Description: 添加文章。
     * @return
     * @return String
     */
    @RequestMapping(method=RequestMethod.POST)
    public String add(Article article){
        articleService.saveArticle(article);
        return "redirect:/article/articles";
    }
    
    /**
     * @Title: delete
     * @Description: 删除文章。
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/article/articles";
    }
    
    /**
     * @Title: update
     * @Description: 跳转到文章修改页面。
     * @param id
     * @return
     * @return String
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, ModelMap model) {
        Article article = articleService.findArticleById(id);
        model.put("article", article);
        return "article/edit";
    }

    /**
     * @Title: update
     * @Description: 文章修改。
     * @param baseUser
     * @return
     * @return String
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(Article article) {
        articleService.updateArticle(article);
        return "redirect:/article/articles";
    }
    
}
