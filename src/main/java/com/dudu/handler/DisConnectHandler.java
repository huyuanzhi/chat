package com.dudu.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.dudu.util.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/25
 * @project: soundChat
 * @packageName: com.dudu.handler
 * @description: XXXXXX
 */
public class DisConnectHandler implements DisconnectListener {

    private static final Logger log= LoggerFactory.getLogger(DisConnectHandler.class);

    @Override
    public void onDisconnect(SocketIOClient client) {
        CacheUtil.userDecrement();
    }
}
