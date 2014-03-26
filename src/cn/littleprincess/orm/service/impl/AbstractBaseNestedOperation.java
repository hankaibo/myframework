/**   
 * @ProjectName: MyFramework
 * @Package: cn.littleprincess.orm.service.impl 
 * @ClassName: AbstractBaseNestedOperation 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-11 上午12:17:29 
 *
 */
package cn.littleprincess.orm.service.impl;

import java.util.List;

import cn.littleprincess.orm.dao.IBaseNestedDao;
import cn.littleprincess.orm.model.BaseTree;
import cn.littleprincess.orm.service.IBaseNestedService;

/**
 * @ClassName: AbstractBaseNestedOperation
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-3-11
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-11 上午12:17:29
 * @UpdateRemark: What is modified?
 */
public abstract class AbstractBaseNestedOperation<T extends BaseTree> extends AbstractBaseEntityOperation<BaseTree>
        implements IBaseNestedService<T> {

    public abstract IBaseNestedDao<T> getDao();

    //@formatter:off
    /* (非 Javadoc)
     * Title: loadFullTree
     * Description:
     * @return
     * @see cn.littleprincess.orm.service.IBaseNestedOperation#loadFullTree(cn.littleprincess.orm.model.BaseTree)
     */
    //@formatter:on
    @Override
    public List<T> loadFullTree() {
        return getDao().loadFullTree();
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDescendants
     * Description:
     * @param id
     * @return
     * @see cn.littleprincess.orm.service.IBaseNestedService#getDescendants(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<T> getDescendants(Long id) {
        return getDao().getDescendants(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getChilds
     * Description:
     * @param id
     * @return
     * @see cn.littleprincess.orm.service.IBaseNestedService#getChilds(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<T> getChilds(Long id) {
        return getDao().getChilds(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getParent
     * Description:
     * @param id
     * @return
     * @see cn.littleprincess.orm.service.IBaseNestedService#getParent(java.lang.Long)
     */
    //@formatter:on
    @Override
    public T getParent(Long id) {
        return getDao().getParent(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getAncestry
     * Description:
     * @param id
     * @return
     * @see cn.littleprincess.orm.service.IBaseNestedService#getAncestry(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<T> getAncestry(Long id) {
        return getDao().getAncestry(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: addChild
     * Description:
     * @param id
     * @param params
     * @see cn.littleprincess.orm.service.IBaseNestedService#addChild(java.lang.Long, java.lang.Object)
     */
    //@formatter:on
    @Override
    public void addChild(Long id, Object params) {
        getDao().lftPlus2(id);
        getDao().rgtPlus2(id);
        getDao().insertNode(params);
        getDao().parentRgtPlus2(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: delChild
     * Description:
     * @param id
     * @see cn.littleprincess.orm.service.IBaseNestedService#delChild(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void delChild(Long id) {
        getDao().lftMinus2(id);
        getDao().rgtMinus2(id);
        getDao().deleteEntity(id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: moveUp
     * Description:
     * @param id
     * @param upId
     * @see cn.littleprincess.orm.service.IBaseNestedService#moveUp(java.lang.Long, java.lang.Long)
     */
    //@formatter:on
    @Override
    public void moveUp(Long id,Long upId) {
        //当前节点不是首节点
        if (!getDao().isFirstNode(id)) {
            // 弟弟（自身）节点左右值减2
            getDao().brotherMinus2(id);
            // 哥哥节点左右值加去2
            getDao().brotherPlus2(upId);
        }
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: moveDown
     * Description:
     * @param id
     * @param downId
     * @see cn.littleprincess.orm.service.IBaseNestedService#moveDown(java.lang.Long, java.lang.Long)
     */
    //@formatter:on
    @Override
    public void moveDown(Long id,Long downId) {
        //当前节点不是末节点
        if (!getDao().isLastNode(id)) {
            // 哥哥节点（自身）左右值加2
            getDao().brotherPlus2(id);
            // 弟弟节点左右值减2
            getDao().brotherMinus2(downId);
        }
    }

}
