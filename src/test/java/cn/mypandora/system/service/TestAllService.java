/**
 * Copyright © 2015. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.system.service;

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
        TestDeptService.class,
        TestLogService.class,
        TestPermissionService.class,
        TestResService.class
})
public class TestAllService {
}
