package com.dudu.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.dudu.util.CacheUtil;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/14
 * @project: soundChat
 * @packageName: com.dudu.handler
 * @description: XXXXXX
 */
public class InitHandler implements DataListener {
    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
        int a = CacheUtil.userDecrement();
        if (CacheUtil.existClient(data.toString())) {
            CacheUtil.removeClient(data.toString());
        }
        client.set("uuid", data.toString());
        CacheUtil.saveClient(data.toString(), client);
    }
}
