/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Joks
 */
public class ChatMessageCallbackImpl extends UnicastRemoteObject implements ChatMessageCallback {

    public int id;
    
    public ChatMessageCallbackImpl(int id) throws RemoteException{
    this.id=id;
    }
    @Override
    public void onChatMessage(ChatMessage c) throws RemoteException {
        System.out.println(c.msg);
    }

    @Override
    public int returnId() throws RemoteException {
       return id;
    }
    
}
