
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
public interface EStudentManager extends Remote {
    Student vratiStudenta(String brindexa)throws RemoteException;
    void registruj(EStudSluzbaCallback c) throws RemoteException;
    void unregistruj(EStudSluzbaCallback c) throws RemoteException;
    void obavestiSve(String ime,String brindexa) throws RemoteException;
    void dodajStudenta(Student stud)throws RemoteException;
}
