package com.dudu.util;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/14
 * @project: soundChat
 * @packageName: com.dudu.util
 * @description: XXXXXX
 */
public class CacheUtil {

    private static final ConcurrentHashMap clientCache=new ConcurrentHashMap();
    private static final AtomicInteger totalUser=new AtomicInteger(0);


    public static SocketIOClient getClient(String openId){
        return (SocketIOClient) clientCache.get(openId);
    }

    public static void saveClient(String openId, SocketIOClient client){
        clientCache.put(openId,client);
    }

    public static boolean existClient(String uuid){
        return clientCache.containsKey(uuid);
    }

    public static void removeClient(String uuid){
        clientCache.remove(uuid);
    }
    public static int userIncrement(){
        return totalUser.incrementAndGet();
    }

    public static int userDecrement(){
        return totalUser.decrementAndGet();
    }

}
