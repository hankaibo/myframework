/**
 * Copyright © 2015. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 打包测试
 * <p>User: hankaibo
 * <p>Date: 2015/8/4
 * <p>Version: 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        cn.mypandora.service.TestDeptService.class,
        cn.mypandora.service.TestLogService.class,
        cn.mypandora.service.TestPermissionService.class,
        cn.mypandora.service.TestResService.class
})
public class TestAllService {
}
