import java.rmi.RemoteException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class EksponatImpl extends UnicastRemoteObject implements Eksponat{

    public String id;
    public String naziv;
    public int cena;
    private ArrayList<KlijentAukcije> listaKlijenata;
    
    public EksponatImpl(String id,String naziv,int cena) throws RemoteException
    {
        this.id=id;
        this.cena=cena;
        this.naziv=naziv;
        this.listaKlijenata=new ArrayList<KlijentAukcije>();
    }

    @Override
    public void prijaviLicitaciju(KlijentAukcije ka) throws RemoteException {
        this.listaKlijenata.add(ka);
    }

    @Override
    public KlijentAukcije vratiKlijentaAukcije(String id) throws RemoteException {
          int i=0;
        KlijentAukcije pom=null;
       for(KlijentAukcije client :listaKlijenata)
       {
           if(client.klijentAukcijeId.equalsIgnoreCase(id));
                pom=client;
       }
      return pom;
    }

    @Override
    public void odustaniOdLicitacije(String klijentAukcijeId) throws RemoteException {
                int i=0;int pos=-1;
       for(KlijentAukcije client :listaKlijenata)
       {
           if(client.klijentAukcijeId.equalsIgnoreCase(klijentAukcijeId));
                pos=i;
           i++;
       }
       this.listaKlijenata.remove(pos);
    }

    @Override
    public String vratiNaziv() throws RemoteException {
        return this.naziv;
    }

    @Override
    public int vratiCenu() throws RemoteException {
        return this.cena;
    }

    @Override
    public void povecajCenu(int iznos) throws RemoteException {
        this.cena+=iznos;
    }

    @Override
    public String vratiId() throws RemoteException {
         return this.id;
    }
   
    
}
 