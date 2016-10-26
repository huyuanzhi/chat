package com.dudu.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

import java.util.Map;

/**
 * @author: huyuanzhi
 * @version: 1.0
 * @date: 2016/10/26
 * @project: chat
 * @packageName: com.dudu.handler
 * @description: XXXXXX
 */
public class ChessSuccessHandler implements DataListener {

    private SocketIOServer server;

    public SocketIOServer getServer() {
        return server;
    }

    public void setServer(SocketIOServer server) {
        this.server = server;
    }

    public ChessSuccessHandler(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
        server.getBroadcastOperations().sendEvent("isWell",data);
    }
}
