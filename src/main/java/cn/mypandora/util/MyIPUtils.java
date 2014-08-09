/**   
 * @ProjectName: MyDemo
 * @Package: cn.mypandora 
 * @ClassName: MyIPUtils 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-4 下午2:01:05 
 *
 */
package cn.mypandora.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: MyIPUtils
 * @Description: IP获取工具类。
 * @Author: kaibo
 * @date: 2014-5-4
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-4 下午2:01:05
 * @UpdateRemark: What is modified?
 */
public class MyIPUtils {

    /**
     * @Title: getIpAddr
     * @Description: 通过HttpServletRequest返回IP地址
     * @param request
     * @return
     * @throws Exception
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
