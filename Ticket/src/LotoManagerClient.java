
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;
import ticket.CallbackWinner;
import ticket.CallbackWinnerImpl;
import ticket.LotoManager;
import ticket.Ticket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class LotoManagerClient {
    
    public LotoManagerClient() throws NotBoundException, MalformedURLException, RemoteException, IOException
    {
        
        LotoManager lm=(LotoManager)Naming.lookup("rmi://localhost:1099/Loto");
        System.out.println("unesite vase brojeve");
        
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Vector<Integer> brojevi=new Vector<Integer>();
        
        for(int i=0;i<7;i++)
        {
         System.out.println("unesite vase broj");  
         brojevi.add(Integer.parseInt(br.readLine()));
        }
        Ticket pom=lm.playTicket(brojevi);
        CallbackWinner cw=new CallbackWinnerImpl(pom.id);
        lm.Register(cw);
        
        System.out.println("Hocete pobednike?");
        if(br.readLine().equals("da"))
        {
           lm.drawNumbers();
        }
        
         
            
    }
    public static void main(String[]args) throws NotBoundException, RemoteException, IOException
    {
     new LotoManagerClient();
    }
}
