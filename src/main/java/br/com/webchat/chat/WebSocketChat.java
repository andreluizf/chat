package br.com.webchat.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{user}")
public class WebSocketChat {

    private static final Map<String, Session> peers
            = Collections.synchronizedMap(new HashMap<String, Session>());

    @OnOpen
    public void onOpen(Session peer, @PathParam("user") String userName) {
        peers.put(userName, peer);
    }

    @OnClose
    public void onClose(Session peer, @PathParam("user") String userName) {
        peers.remove(userName);
    }

    @OnMessage
    public void message(String message, Session client, @PathParam("user") String userName)
            throws IOException, EncodeException {
        int i = 0;
        if (message.contains(" ::user")) {
            for (Session peer : client.getOpenSessions()) {
                List users = new ArrayList();
                for (Map.Entry<String, Session> entry : peers.entrySet()) {
                    String user = entry.getKey();
                    users.add(user);
                }
                peer.getBasicRemote().sendObject(users);

            }
        } else {
            String[] to =message.split("to::");
            for (Session peer : client.getOpenSessions()) {
                peer.getBasicRemote().sendText(message);
            }

        }

    }

}
