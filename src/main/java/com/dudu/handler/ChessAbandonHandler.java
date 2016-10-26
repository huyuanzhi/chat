package com.dudu.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.dudu.util.CacheUtil;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/26
 * @project: chat
 * @packageName: com.dudu.handler
 * @description: XXXXXX
 */
public class ChessAbandonHandler implements DataListener {
    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
        SocketIOClient toClient = CacheUtil.getClient(data.toString());
        toClient.sendEvent("chessAbandon");
    }
}
