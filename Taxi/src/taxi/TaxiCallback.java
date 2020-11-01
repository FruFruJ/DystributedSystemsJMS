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
interface TaxiCallback  extends java.rmi.Remote{
    
    void notifyTaxi(String address) throws RemoteException;
}
