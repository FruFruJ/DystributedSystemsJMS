
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.util.ArrayList;

public class StudentImpl extends UnicastRemoteObject implements Student{

    public String brIndexa;
    public ArrayList<String> nizIspita=new ArrayList<String>();
    public Prijava prijava;
    public StudentImpl(String brIndexa)throws RemoteException{
    this.brIndexa=brIndexa;
    prijava=null;
    }
    @Override
    public Prijava vratiPrijavu() throws RemoteException {
        return prijava;
    }

    @Override
    public void prijaviIspit(String ispit) throws RemoteException {
       this.prijava.prijaviIspit(ispit);
    }

    @Override
    public String vratiIndex() throws RemoteException {
       return this.brIndexa;
    }

    @Override
    public void postaviPrijavu(Prijava a) throws RemoteException {
        this.prijava=a;
    }

    @Override
    public String vratiIspite() throws RemoteException {
    return this.prijava.vratiIspite();
    }
    
}
