/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;

import java.rmi.RemoteException;

/**
 *
 * @author Joks
 */
public interface ChatMessageCallback extends java.rmi.Remote {
   void  onChatMessage(ChatMessage c) throws RemoteException;
   
   int returnId()throws RemoteException;
}
