
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
interface Prijava extends Remote{
    String vratiIspite() throws RemoteException;
    void prijaviIspit(String imePredmeta) throws RemoteException;
}
