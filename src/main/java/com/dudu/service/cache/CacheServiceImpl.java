package com.dudu.service.cache;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.lang.reflect.Type;

/**
 * @author:huyuanzhi
 * @function:redis操作方法类
 */

@Service("cacheService")
public class CacheServiceImpl  implements CacheService {

	@Autowired
	private  JedisCluster jedisCluster;


	private static final Logger log= LoggerFactory.getLogger(CacheServiceImpl.class);

	@Override
	public long del(String key) {
		try {
			return jedisCluster.del(key);
		} catch (Exception e) {
			log.debug("del cache失败 key={}",key);
			return 0;
		}
	}

	@Override
	public String setx(String key, String value, int liveTime) {
		try {
			if(StringUtils.isEmpty(value)){
				return null;
			}
			return jedisCluster.setex(key, liveTime, value);
		} catch (Exception e) {
			log.debug("setx cahce失败 key={},value={}",key,value);
			return null;
		}
	}

	@Override
	public String set(String key, String value) {
		try {
			if(StringUtils.isEmpty(value)){
				return null;
			}
			return jedisCluster.set(key,value);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("set cache失败 key={},value={}",key,value);
			return null;
		}
	}

	@Override
	public String get(String key) {
		try {
			return jedisCluster.get(key);
		} catch (Exception e) {
			log.debug("get cache失败 key={}",key);
			return null;
		}
	}

	@Override
	public boolean exists(String key) {
		try {
			return jedisCluster.exists(key);
		} catch (Exception e) {
			log.debug("exists cache失败 key={}",key);
			return false;
		}
	}

	@Override
	public <T> void setObject(String key, T value) {
		try{
			jedisCluster.set(key,JSON.toJSONString(value));
		}catch (Exception e){

		}
	}

	@Override
	public <T> void setexObject(String key, T value, int second) {
		try{
			jedisCluster.setex(key,second, JSON.toJSONString(value));
		}catch (Exception e){

		}
	}

	@Override
	public <T> T getObject(String key) {
		if(jedisCluster == null || !jedisCluster.exists(key)){
			return null;
		}
		String json=jedisCluster.get(key);
		try {
			return null != json && json.trim().length() != 0?JSON.parseObject(json, new TypeReference<T>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            }):null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
