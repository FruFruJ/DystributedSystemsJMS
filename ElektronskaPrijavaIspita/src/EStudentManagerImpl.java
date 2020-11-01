
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class EStudentManagerImpl extends UnicastRemoteObject implements EStudentManager{

    ArrayList<Student> student;
    ArrayList<EStudSluzbaCallback> callbackovi;
    
    public EStudentManagerImpl() throws RemoteException
    {
        student=new ArrayList<Student>();
        callbackovi=new ArrayList<EStudSluzbaCallback>();
        
    }
    @Override
    public Student vratiStudenta(String brindexa) throws RemoteException {
        for(Student s:student)
        {
            if(s.vratiIndex().equals(brindexa))
                return s;
        }
        return null;
    }

    @Override
    public void registruj(EStudSluzbaCallback c) {
       this.callbackovi.add(c);
    }

    @Override
    public void unregistruj(EStudSluzbaCallback c) {
        this.callbackovi.remove(c);
    }

    @Override
    public void obavestiSve(String ime, String brindexa) throws RemoteException {
       for(EStudSluzbaCallback c:callbackovi)
       {
           c.callback(ime, brindexa);
       }
      }

    @Override
    public void dodajStudenta(Student stud) throws RemoteException {
        this.student.add(stud);  }
    
}
