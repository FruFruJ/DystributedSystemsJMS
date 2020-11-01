/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.util.Vector;

/**
 *
 * @author Joks
 */
public interface LotoManager extends java.rmi.Remote {
    
   Ticket playTicket(Vector<Integer> numbers) throws java.rmi.RemoteException;
    
   
    
    void drawNumbers() throws java.rmi.RemoteException;
    
    void Register(CallbackWinner cW) throws java.rmi.RemoteException;
    void Deregister(CallbackWinner cw) throws java.rmi.RemoteException;
    
}
