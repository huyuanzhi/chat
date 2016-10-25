package com.dudu.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.dudu.model.ChessRequest;
import com.dudu.util.CacheUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/25
 * @project: soundChat
 * @packageName: com.dudu.handler
 * @description: XXXXXX
 */
public class ChessHandler implements DataListener {
    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
        ChessRequest request= (ChessRequest) data;
        String to = request.getTo();
        SocketIOClient playClient = CacheUtil.getClient(to);
        Map message=new HashMap();
        message.put("from",request.getMine());
        message.put("msg",request.getMineName()+"邀请你一起玩五子棋，站个痛快，赶快应战吧！");
        playClient.sendEvent("playChess",message);
    }
}
