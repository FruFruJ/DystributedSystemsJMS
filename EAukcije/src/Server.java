
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class Server {
    public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1089);
			LocateRegistry.getRegistry();
		}catch(RemoteException e) {
			System.out.println("java RMI registry already exists");
		}
		try {
			EAukcijaManager EAM=new EAukcijaImpl();
			
			Eksponat pom=new EksponatImpl("1","jabuka",20);
			EAM.dodajEksponat(pom);
			
			 pom=new EksponatImpl("2","kruska",40);
			EAM.dodajEksponat(pom);
			
		     pom=new EksponatImpl("3","breskva",60);
			EAM.dodajEksponat(pom);
			
			 pom=new EksponatImpl("4","sljiva",100);
			EAM.dodajEksponat(pom);
			
			 pom=new EksponatImpl("5","tresnja",10);
			EAM.dodajEksponat( pom);
			//System.out.println(EAM.vratiEksponat(1).vratuCenu());
			
			Naming.rebind("rmi://localhost:1089/ListaPredmeta", (Remote) EAM);
			System.out.println("Lista predmeta server spreman");
		}catch(Exception e) {
			System.out.println("Lista predmeta server "+e.getMessage());
		}

	}
}
