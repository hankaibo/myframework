package cn.mypandora.ldap;

import cn.mypandora.system.po.BaseUser;

import java.util.List;

/**
 * Created by kaibo on 2015/6/5.
 * desc
 */
public interface PersonRepo {
    /**
     * 获取所有的人。
     *
     * @return
     */
    List<BaseUser> getAllPerson();

    /**
     * 获取所有人的姓名
     *
     * @return
     */
    List<String> getAllPersonNames();
}
