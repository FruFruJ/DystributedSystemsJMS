
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
public interface EAukcijaManager extends Remote {
    public Eksponat vratiEksponat(String idEksponata) throws RemoteException;
    public void dodajEksponat(Eksponat eksponat) throws RemoteException;
    public void registruj(EAukcijaCallback c) throws RemoteException;
    public void unregistruj(EAukcijaCallback c) throws RemoteException;
    public void obavestiSve(String naziv,int cena) throws RemoteException;
}
