/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

import java.rmi.RemoteException;

/**
 *
 * @author Joks
 */
public interface TaxiManager extends java.rmi.Remote {
    
    boolean requestTaxi(String address) throws RemoteException;
    
   void setTaxiStatus(String id,boolean isFree) throws RemoteException;
   
   void addTaxi(Taxi x) throws RemoteException;
   
   void Register(TaxiCallback tc) throws RemoteException;
   
   void Deregister(TaxiCallback tc) throws RemoteException;
}
