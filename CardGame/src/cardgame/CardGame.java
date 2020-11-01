/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Joks
 */
public class CardGame {

   public CardGame() throws RemoteException, MalformedURLException{
   
       LocateRegistry.createRegistry(1099);
       CardGameManager cgm=new CardGameManagerImpl();
       Naming.rebind("rmi://localhost:1099/cardGame", cgm);
       
       System.out.println("Server on");
   }
    public static void main(String[] args) throws RemoteException, MalformedURLException {
       new CardGame();
    }
    
}
