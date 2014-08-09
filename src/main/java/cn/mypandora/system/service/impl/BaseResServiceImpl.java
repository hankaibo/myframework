package cn.mypandora.system.service.impl;

import cn.mypandora.orm.dao.IBaseNestedDao;
import cn.mypandora.orm.service.AbstractBaseNestedOperation;
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
@Service
public class BaseResServiceImpl extends AbstractBaseNestedOperation<BaseRes> implements BaseResService {
    @Resource
    private BaseResDao dao;

    @Override
    public IBaseNestedDao<BaseRes> getDao() {
        return dao;
    }

    @Override
    public List<BaseRes> loadFullRes() {
        return getDao().loadFullTree();
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
        return (BaseRes)dao.findById(id);
    }

    @Override
    public void updateRes(BaseRes res) {
        dao.updateEntity(res);
    }
}
