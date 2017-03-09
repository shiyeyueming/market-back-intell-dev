package com.zity.intell.dev.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C) 2014-2016 天津紫藤科技有限公司. Co. Ltd. All Rights Reserved.
 *
 * @author Andy
 * @version v1
 * @description redis的工具类
 * @serve
 * @module
 * @date 2016年11月20日
 * @code
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 保存数据
     *
     * @param key
     * @param value
     */
    public void save(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error("redis保存数据时报错", e);
        }
    }

    /**
     * 存数据有效时间单位为秒
     *
     * @param key
     * @param value
     * @param time
     */
    public void saveInSeconds(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("存数据时报错", e);
        }
    }

    /**
     * 存数据有效时间单位小时
     *
     * @param key
     * @param value
     * @param time
     */
    public void saveInHours(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.HOURS);
        } catch (Exception e) {
            logger.error("存数据时报错", e);
        }
    }

    /**
     * 存数据有效时间单位为天
     *
     * @param key
     * @param value
     * @param time
     */
    public void saveInDays(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.DAYS);
        } catch (Exception e) {
            logger.error("存数据时报错", e);
        }
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public Object findKey(Object key) {
        Object result = null;
        try {
            result = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error("redis获取数据时报错", e);
        }
        return result;
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void delKey(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("redis删除数据时报错", e);
        }
    }

    /**
     * 以map状态保存数据(无时间限制)
     *
     * @param name
     * @param key
     * @param value
     */
    public void saveMap(String name, String key, Object value) {
        try {
            redisTemplate.opsForHash().put(name, key, value);
        } catch (Exception e) {
            logger.error("redis保存map时报错", e);
        }
    }

    /**
     * 以map状态保存数据(秒级别)
     *
     * @param name
     * @param key
     * @param value
     * @param time
     */
    public void saveMapSecond(String name, String key, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(name, key, value);
            redisTemplate.expire(name, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("redis保存map时报错", e);
        }
    }

    /**
     * 获取map
     *
     * @param name
     * @return
     */
    public Map<String, Object> findMapKey(String name) {
        Map<String, Object> map = null;
        try {
            map = redisTemplate.opsForHash().entries(name);
        } catch (Exception e) {
            logger.error("redis获取map时报错", e);
        }
        return map;
    }

    /**
     * 判断是否存在keyvalue键值对
     *
     * @param mapKey
     * @param key
     * @return
     */
    public boolean isMapKey(String mapKey, Object key) {
        boolean flag = false;
        try {
            flag = redisTemplate.opsForHash().hasKey(mapKey, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除map中的键值对
     *
     * @param mapKey
     * @param key
     * @param value
     * @return
     */
    public void delMapKey(String mapKey, Object key, Object value) {
        try {
            redisTemplate.opsForHash().delete(mapKey, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
