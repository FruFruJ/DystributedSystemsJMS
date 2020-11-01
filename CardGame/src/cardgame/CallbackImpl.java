/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Joks
 */
public class CallbackImpl extends UnicastRemoteObject implements Callback {

    public int id;
    public CallbackImpl(int id) throws RemoteException
    {
        this.id=id;
    }
            
    @Override
    public void isWinner() throws RemoteException {
       System.out.println("U're a winner");
    }

    @Override
    public int returnId() throws RemoteException {
        return id;
          }


    
}
