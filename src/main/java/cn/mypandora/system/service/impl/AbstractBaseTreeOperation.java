/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.system.service.impl;

import cn.mypandora.orm.dao.IBaseTreeDao;
import cn.mypandora.system.dao.BaseResDao;
import cn.mypandora.system.po.BaseRes;
import cn.mypandora.system.service.BaseResService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kaibo on 2014/8/5.
 * desc
 */
/**
 * 登录页面PO。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
@Service
public class AbstractBaseTreeOperation extends AbstractBaseTreeOperation<BaseRes> implements BaseResService {
    @Resource
    private BaseResDao dao;

    @Override
    public IBaseTreeDao<BaseRes> getDao() {
        return dao;
    }

    @Override
    public List<BaseRes> loadFullRes() {
        return getDao().loadFullTree();
    }

    /**
     * 获取带深度的所有资源（一次性全部加载，适合数据量少的情况）。
     *
     * @return
     */
    @Override
    public List<BaseRes> loadFullResWithLevel(int level) {
        return getDao().loadTreeWithLevel(level);
    }

    @Override
    public List<BaseRes> getResDescendants(Long id) {
        return getDao().getDescendants(id);
    }

    @Override
    public List<BaseRes> getResChilds(Long id) {
        return getDao().getChilds(id);
    }

    @Override
    public BaseRes getResParent(Long id) {
        return getDao().getParent(id);
    }

    @Override
    public List<BaseRes> getResAncestry(Long id) {
        return getDao().getAncestry(id);
    }

    @Override
    @Transactional
    public void addRes(Long id, Object params) {
        getDao().lftPlus2(id);
        getDao().rgtPlus2(id);
        getDao().insertNode(params);
        getDao().parentRgtPlus2(id);
    }

    @Override
    public void delRes(Long id) {
        getDao().lftMinus2(id);
        getDao().rgtMinus2(id);
        getDao().deleteEntity(id);
    }

    @Override
    public void moveUpRes(Long id, Long upId) {
        // 当前节点不是首节点
        if (!getDao().isFirstNode(id)) {
            // 弟弟（自身）节点左右值减2
            getDao().brotherMinus2(id);
            // 哥哥节点左右值加去2
            getDao().brotherPlus2(upId);
        }
    }

    @Override
    public void moveDownRes(Long id, Long downId) {
        // 当前节点不是末节点
        if (!getDao().isLastNode(id)) {
            // 哥哥节点（自身）左右值加2
            getDao().brotherPlus2(id);
            // 弟弟节点左右值减2
            getDao().brotherMinus2(downId);
        }
    }

    @Override
    public BaseRes findResById(Long id) {
        return getDao().findById(id);
    }

    @Override
    public void updateRes(BaseRes res) {
        dao.updateEntity(res);
    }
}
