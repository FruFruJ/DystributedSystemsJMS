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
public class TaxiClient {
    
    public TaxiClient() throws NotBoundException, MalformedURLException, RemoteException, IOException{
    
        TaxiManager tm=(TaxiManager)Naming.lookup("rmi://localhost:1099/taxi");
        
        System.out.println("Unesite vasu adresu");
        
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String adresa=br.readLine();
        TaxiCallback tc=new TaxiCallbackImpl(adresa);
        tm.Register(tc);
        tm.requestTaxi(adresa);
    }
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, IOException
    {
        new TaxiClient();
    }
}
