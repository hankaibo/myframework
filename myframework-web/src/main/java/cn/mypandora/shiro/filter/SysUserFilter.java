package cn.mypandora.shiro.filter;

import cn.mypandora.service.BaseUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by hanka on 11/13/2016.
 */
public class SysUserFilter extends PathMatchingFilter {
    @Resource
    private BaseUserService baseUserService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username= (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("user", baseUserService.findUserByUsername(username));
        return true;
    }

}
