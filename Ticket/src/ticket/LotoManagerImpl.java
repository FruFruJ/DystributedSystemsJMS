/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author Joks
 */
public class LotoManagerImpl extends UnicastRemoteObject implements LotoManager {

    public Vector<Integer> numbersDrawn;
    public Vector<Ticket> ticketsPlayed;
    public Vector<Integer> winners;
    private Vector<Integer> numbers;
    private boolean winnersDrawn;
    private Vector<CallbackWinner> callbacks;
    public LotoManagerImpl() throws java.rmi.RemoteException{
        numbersDrawn=new Vector<Integer>();
        ticketsPlayed=new Vector<Ticket>();
        winners=new Vector<Integer>();
        numbers=new Vector<Integer>();
        callbacks=new Vector<CallbackWinner>();
        winnersDrawn=false;
    }
    @Override
    public Ticket playTicket(Vector<Integer> numbers) throws RemoteException {
        Ticket ticket=new Ticket(numbers);
        ticketsPlayed.add(ticket);
      
        return ticket;
    }

   

    @Override
    public void drawNumbers() throws RemoteException {
       numbers.add(1);
       numbers.add(2);
       numbers.add(3);
       numbers.add(4);
       numbers.add(5);
       numbers.add(6);
       numbers.add(7);
       int []niz=new int[ticketsPlayed.size()];
       System.out.println(ticketsPlayed.size());
       System.out.println(ticketsPlayed.get(0).id+" "+ticketsPlayed.get(0).tiket.toString());
       
       for(int i=1;i<8;i++)
       {
           for(int j=0;j<ticketsPlayed.size();j++)
           {
               
               if(i==0)
                   niz[j]=0;
               if(ticketsPlayed.get(j).tiket.indexOf(i)>-1)
                   niz[j]++;
           }
               
       }
       for(int i=0;i<niz.length;i++)
       {
           if(niz[i]==7)
           {
               for(int j=0;j<callbacks.size();j++)
               {
                   callbacks.get(j).isWinner(ticketsPlayed.get(i).id);
               }
           }
       }

       
    }

    @Override
    public void Register(CallbackWinner cW) throws java.rmi.RemoteException{
        callbacks.add(cW);
       }

    @Override
    public void Deregister(CallbackWinner cw) throws java.rmi.RemoteException {
        callbacks.remove(cw);
    }
    
}
