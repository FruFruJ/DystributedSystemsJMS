/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Joks
 */
public class ChatApp {

   public ChatApp() throws RemoteException, MalformedURLException
   {
       ChatAppManager cam=new ChatAppManagerImpl();
       LocateRegistry.createRegistry(1099);
       Naming.rebind("rmi://localhost:1099/chatApp", cam);
       
       System.out.println("Server up");
       
   }
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        new ChatApp();
    }
    
}
