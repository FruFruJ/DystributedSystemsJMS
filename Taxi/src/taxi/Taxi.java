/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;


import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Joks
 */
public class Taxi extends UnicastRemoteObject implements TaxiInterface{

   
  
    public String id;
    public String address;
    public boolean isFree;
    
    public Taxi(String idTaxi,String addressTaxi) throws RemoteException
    {
        isFree=true;
        id=idTaxi;
        address="neka";
        
    }
    
    
}
