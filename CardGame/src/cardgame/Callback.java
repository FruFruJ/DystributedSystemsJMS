/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Joks
 */
public interface Callback extends java.rmi.Remote {
    
    public void isWinner() throws java.rmi.RemoteException;
    
    public int returnId() throws java.rmi.RemoteException;
}
