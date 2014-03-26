///**   
// * @ProjectName:MySpring
// * @Package:cn.littleprincess.dao 
// * @ClassName:LoginLogDao 
// * @Description:TODO
// * Copyright © 2013东软集团股份有限公司. All rights reserved.
// * @Author:hankaibo
// * @CreateDate: 2013-8-14 下午10:39:40 
// * @Version:v1.0
// *
// */
//package cn.littleprincess.system.dao.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import cn.littleprincess.domain.LoginLog;
//
///**
// * @ClassName:LoginLogDao
// * @Description:TODO
// * @Author:hankaibo
// * @date:2013-8-14
// * @UpdateUser:hankaibo
// * @UpdateDate:2013-8-14 下午10:39:40
// * @UpdateRemark:What is modified?
// */
//@Repository
//public class LoginLogDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public void insertLoginLog(LoginLog loginLog) {
//        String sqlStr = "insert into t_login_log(user_id,ip,login_datetime)" + " values(?,?,?)";
//        Object[] args = { loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate() };
//        jdbcTemplate.update(sqlStr, args);
//    }
//
//}
