/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Joks
 */
public class LotoManagesServer  {
    public LotoManagesServer() throws RemoteException, MalformedURLException, IOException
    {
        LotoManager lm=new LotoManagerImpl();
          LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost:1099/Loto", lm);
        
        System.out.println("Server radi");
        System.in.read();
    }
    public static void main(String[]args) throws RemoteException, MalformedURLException, IOException
    {
     new LotoManagesServer();
    }
}
