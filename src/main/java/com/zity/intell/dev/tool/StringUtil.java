package com.zity.intell.dev.tool;

import org.springframework.util.StringUtils;

/**
 * Copyright (C) 2014-2016 天津紫藤科技有限公司. Co. Ltd. All Rights Reserved.
 *
 * @author hanlijie
 * @version v1
 * @description 验证字符串数组中是否有空对象
 * @serve
 * @module
 * @date 2017/2/23
 * @code
 */


public class StringUtil {

    public static boolean check(String[] arr) {
        for (String s : arr) {
            if(s == null || StringUtils.isEmpty(s)) {
                return false;
            }
        }

        return true;
    }
}
