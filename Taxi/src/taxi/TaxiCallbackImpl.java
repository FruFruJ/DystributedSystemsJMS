/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Joks
 */
public class TaxiCallbackImpl extends UnicastRemoteObject implements TaxiCallback {

    String adresa;
    Taxi taxi;
    
    public TaxiCallbackImpl(String adresaP) throws RemoteException {
        adresa=adresaP;
        taxi=null;
    }
    public TaxiCallbackImpl(Taxi t) throws RemoteException
    {
            taxi=t;
            adresa=null;
    }

    @Override
    public void notifyTaxi(String address) throws RemoteException {
       if(taxi==null&&address==adresa)
           System.out.println("Vama je krenuo taxi");
       if(address==null&&address==taxi.address)
           System.out.println("Krenite ka "+adresa);
       
    }
    
}
