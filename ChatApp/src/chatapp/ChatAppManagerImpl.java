/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author Joks
 */
public class ChatAppManagerImpl extends UnicastRemoteObject implements ChatAppManager {

    public Vector<ChatMessage> sentMessages;
    public Vector<ChatMessageCallback> callbacks;
    public ChatAppManagerImpl() throws RemoteException{
    
        sentMessages=new Vector<ChatMessage>();
        callbacks=new Vector<ChatMessageCallback>();
    }
    @Override
    public void SendChatMessage(User fromUser, User toUser, ChatMessage cmsg) throws RemoteException {
       sentMessages.add(cmsg);
       for(ChatMessageCallback calback:callbacks)
       {
           if(calback.returnId()==toUser.id)
               calback.onChatMessage(cmsg);
       }
    }

    @Override
    public Vector<ChatMessage> getChatMessages(User user, int hour, int min) throws RemoteException {
        Vector<ChatMessage> msgs=new Vector<ChatMessage>();
        for(ChatMessage c:sentMessages)
        {
            if(c.fromUser.id==user.id||c.toUser.id==user.id)
            {
                if(hour<c.hour||(hour==c.hour&&c.minute>min))
                    msgs.add(c);
            }
        }
        return msgs;
    }

    @Override
    public void register(ChatMessageCallback c) throws RemoteException {
        callbacks.add(c);
    }
    
}
