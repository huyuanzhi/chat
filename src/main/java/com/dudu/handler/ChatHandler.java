package com.dudu.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.dudu.util.CacheUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/14
 * @project: soundChat
 * @packageName: com.dudu.handler
 * @description: XXXXXX
 */
public class ChatHandler implements DataListener {
    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
        Map msg=(Map)data;
        Map mine = (Map) msg.get("mine");
        Map to = (Map) msg.get("to");
        String type=to.get("type").toString();
        switch (type){
            case "friend":
                handlerFriendMessage(mine,to,client);
                break;
            case "group":
        }
    }

    private void handlerFriendMessage(Map mine,Map to,SocketIOClient client) {
        Map returnMessage=new HashMap();
        returnMessage.put("username",mine.get("username"));
        returnMessage.put("avatar",mine.get("avatar"));
        returnMessage.put("id",mine.get("id"));
        returnMessage.put("content",mine.get("content"));
        returnMessage.put("mine",false);
        returnMessage.put("type",to.get("type"));
        returnMessage.put("timestamp",new Date().getTime());
        client= CacheUtil.getClient(to.get("id").toString());
        if(client != null){
            client.sendEvent("chatEvent",returnMessage);
        }
    }
}
