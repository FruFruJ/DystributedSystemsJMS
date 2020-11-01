

import java.util.ArrayList;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;


public class EAukcijaImpl extends UnicastRemoteObject implements EAukcijaManager{

    private ArrayList<Eksponat> listaEksponata=new ArrayList<Eksponat>();
    private ArrayList<EAukcijaCallback> listaSubscribera=new ArrayList<EAukcijaCallback>();

    public EAukcijaImpl ()throws RemoteException
    {
        
    }
    @Override
    public Eksponat vratiEksponat(String idEksponata) throws RemoteException {
         for(Eksponat eksponat:listaEksponata)
        {
          if(eksponat.vratiId().equals(idEksponata))
              return eksponat;
        }
        return null;
    }

    @Override
    public void dodajEksponat(Eksponat eksponat) throws RemoteException {
         this.listaEksponata.add(eksponat);
    }

    @Override
    public void registruj(EAukcijaCallback c) throws RemoteException {
         this.listaSubscribera.add(c);
    }

    @Override
    public void unregistruj(EAukcijaCallback c) throws RemoteException {
         int i=0;int pom=-1;
        for(EAukcijaCallback cal:this.listaSubscribera)
        {
                 if(cal.equals(c))
                     pom=i;  
                 i++;
        }
        this.listaSubscribera.remove(pom);
    }

    @Override
    public void obavestiSve(String naziv, int cena) throws RemoteException {
         for(EAukcijaCallback c:listaSubscribera)    
           {
               c.obavesti(naziv, cena);
           }
    }
  

   

   
    
    
}



 

  