
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
public interface Eksponat extends Remote {
     public void prijaviLicitaciju(KlijentAukcije ka) throws RemoteException;
     public KlijentAukcije vratiKlijentaAukcije(String id) throws RemoteException;;
     public void odustaniOdLicitacije(String klijentAukcijeId) throws RemoteException;;
     public String vratiNaziv() throws RemoteException;;
     public int vratiCenu() throws RemoteException;
     public void povecajCenu(int iznos) throws RemoteException;;
     public String vratiId() throws RemoteException;;
}
