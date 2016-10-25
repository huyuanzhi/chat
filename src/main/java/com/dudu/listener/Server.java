package com.dudu.listener;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.dudu.handler.*;
import com.dudu.model.Chess;
import com.dudu.model.ChessRequest;

import java.util.Map;

public class Server {

    protected void main(){
        final Configuration chatConfig = new Configuration();
        chatConfig.setHostname("localhost");
        chatConfig.setPort(9002);

        final SocketIOServer server = new SocketIOServer(chatConfig);
        server.addEventListener("init", Object.class, new InitHandler());
        server.addDisconnectListener(new DisConnectHandler());
        server.addEventListener("chatEvent", Map.class, new ChatHandler());
        server.addEventListener("playChess", ChessRequest.class, new ChessHandler());
        server.addEventListener("acceptChess",ChessRequest.class, new AcceptChessHandler());
        server.addEventListener("chessClick", Chess.class, new ChessClickHandler());
        server.start();
    }

}
