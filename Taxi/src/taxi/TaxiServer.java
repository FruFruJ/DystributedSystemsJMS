/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Joks
 */
public class TaxiServer {
    
    public TaxiServer() throws RemoteException, MalformedURLException{
        LocateRegistry.createRegistry(1099);
        TaxiManager tm=new TaxiManagerImpl();
        Naming.rebind("rmi://localhost:1099/taxi", tm);
        
        System.out.println("server radi");
    }
    
    public static void main(String[]args) throws RemoteException, MalformedURLException
    {
        new TaxiServer();
    }
}
