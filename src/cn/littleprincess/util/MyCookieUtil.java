/**   
 * @ProjectName: MyFramework
 * @Package: cn.littleprincess.util 
 * @ClassName: MyCookieUtil 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-3-23 上午12:25:57 
 *
 */
package cn.littleprincess.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MyCookieUtil
 * @Description: TODO
 * @Author: kaibo
 * @date: 2014-3-23
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-3-23 上午12:25:57
 * @UpdateRemark: What is modified?
 */
public class MyCookieUtil {
    public static final String COOKIE_KEY = "hankaibo";

    /**
     * @Title: isCookieExist
     * @Description: 判断cookie值是否存在，如果不存在返回null
     * @param name
     * @param request
     * @return
     * @return Cookie
     */
    public static Cookie isCookieExist(String name, HttpServletRequest request) {
        Cookie[] cookieList = request.getCookies();
        // firefox浏览时如果cookie不存在返回null而不是空数组
        if (cookieList != null) {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * @Title: removeCookie
     * @Description: 移除Cookie
     * @param name
     * @param request
     * @param response
     * @return void
     */
    public static void removeCookie(String name, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookieList = request.getCookies();
        // firefox浏览时如果cookie不存在返回null而不是空数组
        if (cookieList != null) {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(name)) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
        }
    }

}
