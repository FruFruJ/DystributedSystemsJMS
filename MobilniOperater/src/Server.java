import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

	public static void main(String[] args) {
		
		 try{
	           LocateRegistry.createRegistry(1099);
	       } catch(RemoteException e){
	          System.out.println("java RMI registry already exists"); 
	       }
	       try{
	           OperaterManager OM=new OperaterManagerImpl();
	           Stanje polo=new StanjeImpl("222284",0,0,0,0);
	           Korisnik pom=new KorisnikImpl("222284",0,0,0,10,5,4);
	           pom.postaviStanje(polo);
	           OM.dodajKorisnika(pom);
	           
	            polo=new StanjeImpl("552153",0,0,0,0);
	            pom=new KorisnikImpl("552153",0,0,0,10,5,4);
	           pom.postaviStanje(polo);
	           OM.dodajKorisnika(pom);
	           
	           
	          
	           
	           Naming.rebind("rmi://localhost:1099/ListaKorisnika", OM);
				System.out.println("Lista klijenata mobilne telefonija server spreman");
	           
	       }catch(Exception e) {
				System.out.println("Lista klijenata mobilne telefonija server "+e.getMessage());
			}

	}

}

