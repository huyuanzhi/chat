package com.dudu.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.dudu.model.Chess;
import com.dudu.util.CacheUtil;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/25
 * @project: soundChat
 * @packageName: com.dudu.handler
 * @description: XXXXXX
 */
public class ChessClickHandler implements DataListener {
    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
        Chess chess= (Chess) data;
        String to = chess.getTo();
        SocketIOClient toClient = CacheUtil.getClient(to);
        toClient.sendEvent("chessClick",chess);
    }
}
