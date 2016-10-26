package com.dudu.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.dudu.model.ChessRequest;
import com.dudu.model.User;
import com.dudu.service.user.UserService;
import com.dudu.util.BeanUtils;
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

    UserService userService= BeanUtils.getBean(UserService.class);

    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
        ChessRequest request= (ChessRequest) data;
        String to = request.getTo();
        String mine = request.getMine();
        SocketIOClient toClient = CacheUtil.getClient(to);
        Map msg = new HashMap();
        msg.put("from",mine);
        msg.put("type",1);
        msg.put("name",request.getMineName());

        SocketIOClient myClient = CacheUtil.getClient(mine);
        Map myMsg = new HashMap();
        myMsg.put("from",to);
        myMsg.put("type",2);
        User u=userService.getUserById(to);
        myMsg.put("name",u.getUsername());
        toClient.sendEvent("acceptChess",msg);
        myClient.sendEvent("acceptChess",myMsg);
    }
}
