package cn.mypandora.ldap;

import cn.mypandora.system.po.BaseUser;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created by kaibo on 2015/6/5.
 * desc
 */
public class PersonRepoImpl implements PersonRepo {
    private LdapTemplate ldapTemplate;

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    private class PersonAttributesMapper implements AttributesMapper<BaseUser> {
        public BaseUser mapFromAttributes(Attributes attrs) throws NamingException {
            BaseUser person = new BaseUser();
            person.setUsername((String) attrs.get("cn").get());
            person.setUsername((String) attrs.get("sn").get());
            person.setRealName((String) attrs.get("description").get());
            return person;
        }
    }

    /**
     * 获取所有的人。
     *
     * @return
     */
    @Override
    public List<BaseUser> getAllPerson() {
        return ldapTemplate.search(query().where("objectclass").is("inetOrgPerson"), new PersonAttributesMapper());
    }

    /**
     * 获取所有人的姓名
     *
     * @return
     */
    @Override
    public List<String> getAllPersonNames() {
        return ldapTemplate.search(query()
                        .attributes("cn")
                        .where("objectclass").is("person"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs) throws NamingException {
                        return attrs.get("cn").get().toString();
                    }
                }
        );

    }
}

