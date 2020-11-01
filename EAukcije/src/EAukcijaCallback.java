
import java.rmi.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public interface EAukcijaCallback extends Remote  {
    void obavesti(String klijent,int cena) throws RemoteException;
}
