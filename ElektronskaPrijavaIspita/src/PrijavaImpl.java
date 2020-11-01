
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class PrijavaImpl extends UnicastRemoteObject implements Prijava {
     
    String brIndexa;
    ArrayList<String> prijavljeniIspiti = new ArrayList<String>();
    ArrayList<String> ispiti=new ArrayList<String>();
  
    
    public PrijavaImpl(String index) throws RemoteException{
        this.brIndexa=index;
        ispiti.add("RM");
        ispiti.add("DS");
        ispiti.add("OS");
        ispiti.add("MIKS");
        ispiti.add("SWE");
        ispiti.add("IS");
        
     }
    @Override
    public String vratiIspite() throws RemoteException {
         String sredjeno="";
        int indeks=0;
        for(String k:ispiti)
        {
            indeks++;
            sredjeno+=indeks+" "+k+"\n";
        }
        return sredjeno;
    }

    @Override
    public void prijaviIspit( String imePredmeta) throws RemoteException {
      this.prijavljeniIspiti.add(imePredmeta);
    }
    
}
