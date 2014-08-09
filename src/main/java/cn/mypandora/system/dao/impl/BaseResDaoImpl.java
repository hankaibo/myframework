package cn.mypandora.system.dao.impl;

import cn.mypandora.orm.dao.impl.BaseNestedDaoImpl;
import cn.mypandora.system.dao.BaseResDao;
import cn.mypandora.system.po.BaseRes;
import org.springframework.stereotype.Repository;

/**
 * Created by kaibo on 2014/8/5.
 * desc
 */
@Repository
public class BaseResDaoImpl extends BaseNestedDaoImpl<BaseRes> implements BaseResDao {
    @Override
    public String getNameSpace() {
        return "cn.mypandora.system.BaseRes";
    }
}
