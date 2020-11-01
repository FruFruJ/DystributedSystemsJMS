
import java.rmi.*;


public interface Student extends Remote{
    Prijava vratiPrijavu() throws RemoteException;
    void prijaviIspit(String ispit)throws RemoteException;
    String vratiIndex()throws RemoteException;
    void postaviPrijavu(Prijava a) throws RemoteException;
    String vratiIspite() throws RemoteException;
}
