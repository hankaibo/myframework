package cn.mypandora.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mypandora.orm.dao.IBaseNestedDao;
import cn.mypandora.orm.service.AbstractBaseNestedOperation;
import cn.mypandora.system.dao.BaseDeptDao;
import cn.mypandora.system.po.BaseDept;
import cn.mypandora.system.service.BaseDeptService;

/**
 * @ClassName:BaseDeptServiceImpl
 * @Description:部门管理Service实现类。
 * @Author:hankaibo
 * @date:2013-8-14
 * @UpdateUser:hankaibo
 * @UpdateDate:2013-8-14 下午11:12:00
 * @UpdateRemark:What is modified?
 */
@Service
public class BaseDeptServiceImpl extends AbstractBaseNestedOperation<BaseDept> implements BaseDeptService {
    @Resource
    private BaseDeptDao dao;

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDao
     * Description:
     * @return
     * @see cn.mypandora.orm.service.AbstractBaseNestedOperation#getDao()
     */
    //@formatter:on
    @Override
    public IBaseNestedDao<BaseDept> getDao() {
        return dao;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: loadFullDept
     * Description:
     * @return
     * @see cn.mypandora.system.service.BaseDeptService#loadFullDept()
     */
    //@formatter:on
    @Override
    public List<BaseDept> loadFullDept() {
        return getDao().loadFullTree();
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDeptDescendants
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.system.service.BaseDeptService#getDeptDescendants(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<BaseDept> getDeptDescendants(Long id) {
        return getDao().getDescendants(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDeptChilds
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.system.service.BaseDeptService#getDeptChilds(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<BaseDept> getDeptChilds(Long id) {
        return getDao().getChilds(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDeptParent
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.system.service.BaseDeptService#getDeptParent(java.lang.Long)
     */
    //@formatter:on
    @Override
    public BaseDept getDeptParent(Long id) {
        return getDao().getParent(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDeptAncestry
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.system.service.BaseDeptService#getDeptAncestry(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<BaseDept> getDeptAncestry(Long id) {
        return getDao().getAncestry(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: addDept
     * Description:
     * @param id
     * @param params
     * @see cn.mypandora.system.service.BaseDeptService#addDept(java.lang.Long, java.lang.Object)
     */
    //@formatter:on
    @Override
    @Transactional
    public void addDept(Long id, Object params) {
        getDao().lftPlus2(id);
        getDao().rgtPlus2(id);
        getDao().insertNode(params);
        getDao().parentRgtPlus2(id);

    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: delDept
     * Description:
     * @param id
     * @see cn.mypandora.system.service.BaseDeptService#delDept(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void delDept(Long id) {
        getDao().lftMinus2(id);
        getDao().rgtMinus2(id);
        getDao().deleteEntity(id);

    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: moveUpDept
     * Description:
     * @param id
     * @param upId
     * @see cn.mypandora.system.service.BaseDeptService#moveUpDept(java.lang.Long, java.lang.Long)
     */
    //@formatter:on
    @Override
    public void moveUpDept(Long id, Long upId) {
        // 当前节点不是首节点
        if (!getDao().isFirstNode(id)) {
            // 弟弟（自身）节点左右值减2
            getDao().brotherMinus2(id);
            // 哥哥节点左右值加去2
            getDao().brotherPlus2(upId);
        }

    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: moveDownDept
     * Description:
     * @param id
     * @param downId
     * @see cn.mypandora.system.service.BaseDeptService#moveDownDept(java.lang.Long, java.lang.Long)
     */
    //@formatter:on
    @Override
    public void moveDownDept(Long id, Long downId) {
        // 当前节点不是末节点
        if (!getDao().isLastNode(id)) {
            // 哥哥节点（自身）左右值加2
            getDao().brotherPlus2(id);
            // 弟弟节点左右值减2
            getDao().brotherMinus2(downId);
        }

    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: findDeptById
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.system.service.BaseDeptService#findDeptById(java.lang.Long)
     */
    //@formatter:on
    @Override
    public BaseDept findDeptById(Long id) {
        return (BaseDept) dao.findById(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: updateDept
     * Description:
     * @param dept
     * @see cn.mypandora.system.service.BaseDeptService#updateDept(cn.mypandora.system.po.BaseDept)
     */
    //@formatter:on
    @Override
    public void updateDept(BaseDept dept) {
        dao.updateEntity(dept);
    }

}
