
 import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
public class Server {

	public static void main(String[] args) {
		 try{
	           LocateRegistry.createRegistry(1092);
	       } catch(RemoteException e){
	          System.out.println("java RMI registry already exists"); 
	       }
	       try{
	           EStudentManager ESM=new EStudentManagerImpl();
	            
	           Student pom=new StudentImpl("16254");
                   Prijava prij=new PrijavaImpl("16254");
                   pom.postaviPrijavu(prij);
	           ESM.dodajStudenta(pom);
	           
	            Student pom1=new StudentImpl("16200");
                   Prijava prij1=new PrijavaImpl("16200");
                   prij1.prijaviIspit("RM");
                   prij1.prijaviIspit("OS");
                   pom.postaviPrijavu(prij);
	           ESM.dodajStudenta(pom);
	           
	        
	           
	           Naming.rebind("rmi://localhost:1092/ListaStudenata", ESM);
				System.out.println("Lista studenata server spreman");
	           
	       }catch(Exception e) {
				System.out.println("Lista predmeta server "+e.getMessage());
			}

	}

}

