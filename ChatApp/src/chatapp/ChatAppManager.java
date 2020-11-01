/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;

import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Joks
 */
public interface ChatAppManager extends java.rmi.Remote{
    
    public void SendChatMessage(User fromUser,User toUser,ChatMessage cmsg) throws java.rmi.RemoteException;
    
    public Vector<ChatMessage> getChatMessages(User user,int hour,int min) throws RemoteException;
    
    public void register(ChatMessageCallback c) throws RemoteException;

  
}
