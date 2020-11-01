/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Joks
 */
public class CardGameManagerImpl extends UnicastRemoteObject implements CardGameManager {

    public ArrayList<Player> igraci;
    public ArrayList<Card> karte;
    public ArrayList<Callback> callbacks;
    public int krug;
    
    public CardGameManagerImpl() throws RemoteException{
        igraci=new ArrayList<Player>();
        karte=new ArrayList<Card>();
        callbacks=new ArrayList<Callback>();
        krug=1;
        
        for(int i=1;i<=14;i++)
        {
            karte.add(new Card("s",i));
            karte.add(new Card("k",i));
            karte.add(new Card("p",i));
            karte.add(new Card("t",i));
        }
    
}
    
    @Override
    public Card requestCard(Player player) throws RemoteException {
        if(karte.size()==0) 
        {
        obavestiPobednike();return null;
        }
        
        boolean svi=true;
          for(Player p : igraci)
       {
           if(p.reject==false)
           {
              svi=false;
           }
       }
          if(svi) {
            obavestiPobednike();
            return null;
          }
          int sum=0;
          for(Integer i:player.cards)
            {
                sum+=i;
            }
            if(sum>21)
            {
                return null;
            }
          int brojIzvucenihUKrugu=0;
        for(Player p : igraci)
       {
           if(p.cards.size()==krug||p.reject==true)
           {
              brojIzvucenihUKrugu++;
           }
       }
        if(brojIzvucenihUKrugu==igraci.size())
        {
            krug++;
        }
        if(player.cards.size()==krug)
            return null;
        int num=(int) (Math.random()*(this.karte.size()-1));
        Card toReturn=this.karte.get(num);
        this.karte.remove(num);
        return toReturn;
        
    }

    @Override
    public void pass(Player player) throws RemoteException {
       for(Player p : igraci)
       {
           if(p.id==player.id)
           {
               p.reject=true;
           }
       }
      
    } 

    @Override
    public void registerPlayer(Player player) throws RemoteException {
        igraci.add(player);
    }

    @Override
    public void register(Callback c) throws RemoteException {
       callbacks.add(c);
    }
    void obavestiPobednike() throws RemoteException
    {
        int max=0;
        for(Player p:igraci)
        {
            int sum=0;
            for(Integer i:p.cards)
            {
                sum+=i;
            }
            if(sum>max)
                max=sum;
        }
        
        for(Player p:igraci)
        {
        int sum=0;
            for(Integer i:p.cards)
            {
                sum+=i;
            }
            if(sum==max)
            {
                for(Callback c:callbacks)
                {
                    if(c.returnId()==p.id)
                        c.isWinner();
                }
                p.brojPoena+=10;
            }
        }
        
        restart();
    }
    
    void restart()
    {
        karte=new ArrayList<Card>();
        krug=1;
        
        for(int i=1;i<=14;i++)
        {
            karte.add(new Card("s",i));
            karte.add(new Card("k",i));
            karte.add(new Card("p",i));
            karte.add(new Card("t",i));
        }
    }
}
