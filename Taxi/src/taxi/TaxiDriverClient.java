/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Joks
 */
public class TaxiDriverClient {
    public TaxiDriverClient() throws NotBoundException, MalformedURLException, RemoteException, IOException{
    
        TaxiManager tm=(TaxiManager)Naming.lookup("rmi://localhost:1099/taxi");
        
        System.out.println("Unesite vasu id");
        
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String id=br.readLine();
        Taxi t=new Taxi(id,"lala");
        tm.addTaxi(t);
        TaxiCallback tc=new TaxiCallbackImpl(new Taxi(id,"lala"));
        tm.Register(tc);
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, IOException
    {
        new TaxiDriverClient();
    }
}
