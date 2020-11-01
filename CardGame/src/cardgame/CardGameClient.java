/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Joks
 */
public class CardGameClient {
    
    public CardGameClient() throws NotBoundException, MalformedURLException, RemoteException
    {
        
        CardGameManager cgm=(CardGameManager) Naming.lookup("rmi://localhost:1099/cardGame");
        Player p=new Player("Jovana");
        Callback c=new CallbackImpl(p.id);
        cgm.registerPlayer(p);
        cgm.register(c);
        
        Card card=cgm.requestCard(p);
        System.out.println(card.color+" "+card.value);
        
        card=cgm.requestCard(p);
        System.out.println(card.color+" "+card.value);
        
        cgm.pass(p);
    }
    
    public static void main(String []args) throws NotBoundException, MalformedURLException, RemoteException
    {
    new CardGameClient();
    }
}
