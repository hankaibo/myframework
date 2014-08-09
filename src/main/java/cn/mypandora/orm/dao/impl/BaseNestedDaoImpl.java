/**   
 * @ProjectName: MyFramework
 * @Package: cn.mypandora.orm.dao 
 * @ClassName: BaseNestedDaoImpl 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-11 上午12:40:23 
 *
 */
package cn.mypandora.orm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.mypandora.orm.dao.IBaseNestedDao;
import cn.mypandora.orm.model.BaseTree;

/**
 * @ClassName: BaseNestedDaoImpl
 * @Description: 嵌套DAO实现类。
 * @Author: kaibo
 * @date: 2014-3-11
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-11 上午12:40:23
 * @UpdateRemark: What is modified?
 */
@Repository
public abstract class BaseNestedDaoImpl<T extends BaseTree> extends BaseEntityDaoImpl<BaseTree> implements
        IBaseNestedDao<T> {
    private static final String LOAD_FULL_TREE = "loadFullTree";
    private static final String GET_DESCENDANTS = "getDescendants";
    private static final String GET_CHILDS = "getChilds";
    private static final String GET_PARENT = "getParent";
    private static final String GET_ANCESTRY = "getAncestry";

    private static final String LFT_PLUS2 = "lftPlus2";
    private static final String RGT_PLUS2 = "rgtPlus2";
    private static final String INSERT_NODE = "insertNode";
    private static final String PARENT_RGT_PLUS2 = "parentRgtPlus2";

    private static final String LFT_MINUS2 = "lftMinus2";
    private static final String RGT_MINUS2 = "rgtMinus2";

    private static final String MOVE_UP = "moveUp";
    private static final String MOVE_DOWN = "moveDown";

    private static final String FIRST_NODE = "firstNode";
    private static final String LAST_NODE = "lastNode";

    @Autowired
    private SqlSession sqlSession;

    //@formatter:off
    /* (非 Javadoc)
     * Title: loadFullTree
     * Description:
     * @param t
     * @return
     * @see cn.mypandora.orm.service.IBaseNestedOperation#loadFullTree(cn.mypandora.orm.model.BaseTree)
     */
    //@formatter:on
    @Override
    public List<T> loadFullTree() {
        return sqlSession.selectList(createSqlKeyName(LOAD_FULL_TREE));
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getDescendants
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.orm.dao.IBaseNestedDao#getDescendants(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<T> getDescendants(Long id) {
        return sqlSession.selectList(createSqlKeyName(GET_DESCENDANTS), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getChilds
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.orm.dao.IBaseNestedDao#getChilds(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<T> getChilds(Long id) {
        return sqlSession.selectList(createSqlKeyName(GET_CHILDS), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getParent
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.orm.dao.IBaseNestedDao#getParent(java.lang.Long)
     */
    //@formatter:on
    @Override
    public T getParent(Long id) {
        return sqlSession.selectOne(createSqlKeyName(GET_PARENT), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: getAncestry
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.orm.dao.IBaseNestedDao#getAncestry(java.lang.Long)
     */
    //@formatter:on
    @Override
    public List<T> getAncestry(Long id) {
        return sqlSession.selectList(createSqlKeyName(GET_ANCESTRY), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: lftPlus2
     * Description:
     * @param id
     * @see cn.mypandora.orm.dao.IBaseNestedDao#lftPlus2(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void lftPlus2(Long id) {
        sqlSession.update(createSqlKeyName(LFT_PLUS2), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: rgtPlus2
     * Description:
     * @param id
     * @see cn.mypandora.orm.dao.IBaseNestedDao#rgtPlus2(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void rgtPlus2(Long id) {
        sqlSession.update(createSqlKeyName(RGT_PLUS2), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: insertNode
     * Description:
     * @param params
     * @see cn.mypandora.orm.dao.IBaseNestedDao#insertNode(java.lang.Object)
     */
    //@formatter:on
    @Override
    public void insertNode(Object params) {
        sqlSession.insert(createSqlKeyName(INSERT_NODE), params);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: parentRgtPlus2
     * Description:
     * @param id
     * @see cn.mypandora.orm.dao.IBaseNestedDao#parentRgtPlus2(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void parentRgtPlus2(Long id) {
        sqlSession.update(createSqlKeyName(PARENT_RGT_PLUS2), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: lftMinus2
     * Description:
     * @param id
     * @see cn.mypandora.orm.dao.IBaseNestedDao#lftMinus2(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void lftMinus2(Long id) {
        sqlSession.update(createSqlKeyName(LFT_MINUS2), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: rgtMinus2
     * Description:
     * @param id
     * @see cn.mypandora.orm.dao.IBaseNestedDao#rgtMinus2(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void rgtMinus2(Long id) {
        sqlSession.update(createSqlKeyName(RGT_MINUS2), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: brotherPlus2
     * Description:
     * @param id
     * @see cn.mypandora.orm.dao.IBaseNestedDao#brotherPlus2(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void brotherPlus2(Long id) {
        sqlSession.update(createSqlKeyName(MOVE_DOWN), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: brotherMinus2
     * Description:
     * @param id
     * @see cn.mypandora.orm.dao.IBaseNestedDao#brotherMinus2(java.lang.Long)
     */
    //@formatter:on
    @Override
    public void brotherMinus2(Long id) {
        sqlSession.update(createSqlKeyName(MOVE_UP), id);
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: isFirstNode
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.orm.dao.IBaseNestedDao#isFirstNode(java.lang.Long)
     */
    //@formatter:on
    @Override
    public boolean isFirstNode(Long id) {
        return sqlSession.selectOne(createSqlKeyName(FIRST_NODE), id) == null ? false : true;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: isLastNode
     * Description:
     * @param id
     * @return
     * @see cn.mypandora.orm.dao.IBaseNestedDao#isLastNode(java.lang.Long)
     */
    //@formatter:on
    @Override
    public boolean isLastNode(Long id) {
        return sqlSession.selectOne(createSqlKeyName(LAST_NODE), id) == null ? false : true;

    }

}
