/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Joks
 */
public class CallbackWinnerImpl extends UnicastRemoteObject implements CallbackWinner {

    public int id;
    public CallbackWinnerImpl(int idP) throws RemoteException
    {
        id=idP;
    }
    @Override
    public void isWinner(int idP) throws RemoteException {
        
        if(this.id==idP)
       System.out.println("You are a winner");
    }
    
}
