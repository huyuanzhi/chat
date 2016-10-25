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
public class AcceptChessHandler implements DataListener {
    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
        ChessRequest request= (ChessRequest) data;
        String to = request.getTo();
        String mine = request.getMine();
        SocketIOClient toClient = CacheUtil.getClient(to);
        Map msg = new HashMap();
        msg.put("from",mine);
        msg.put("type",2);

        SocketIOClient myClient = CacheUtil.getClient(mine);
        Map myMsg = new HashMap();
        myMsg.put("from",to);
        myMsg.put("type",1);

        toClient.sendEvent("acceptChess",msg);
        myClient.sendEvent("acceptChess",myMsg);
    }
}
