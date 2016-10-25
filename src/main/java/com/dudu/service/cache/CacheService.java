package com.dudu.service.cache;

import com.dudu.dao.DaoSupport;
import com.dudu.service.user.UserService;

/**
 * @description 操作redis方法接口
 * @author huyuanzhi
 *
 */

public interface CacheService  {
	/**
     * 通过key删除
     * 
     * @param key
     */
    public  long del(String key);


    /**
     * 添加key value 并且设置存活时间
     * 
     * @param key
     * @param value
     * @param liveTime
     *            单位秒
     */
    public  String setx(String key, String value, int liveTime);

    /**
     * 添加key value
     * 
     * @param key
     * @param value
     */
    public  String set(String key, String value);

  

    /**
     * 获取redis value (String)
     * 
     * @param key
     * @return
     */
    public  String get(String key);

    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    public  boolean exists(String key);

    /**
     * 缓存一个object
     * @param <T>
     * @param key
     * @param value
     */
    public <T>  void setObject(String key, T value);


    /**
     * 缓存一个object，并设定时间
     * @param key
     * @param value
     * @param second
     * @param <T>
     */
    public <T>  void setexObject(String key, T value, int second);

    /**
     * 获取一个对象，并转化成相应对象
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getObject(String key);
}
