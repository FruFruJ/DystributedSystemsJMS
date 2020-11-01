/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Joks
 */
public interface CallbackWinner extends Remote{
    public void isWinner(int idP) throws RemoteException;
 
}
