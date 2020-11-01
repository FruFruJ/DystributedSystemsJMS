
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
public class Client {
	private EStudentManager ESM;
	private EStudSluzbaCallback cc;
        
    public Client(){
    	cc=null;
       try {
		ESM=(EStudentManager)Naming.lookup("rmi://localhost:1092/ListaStudenata");
		System.out.println("Nadjen server");
		
		cc=new EStudCallbackImpl();
		ESM.registruj(cc);
		//cc=new EAukcijaCallbackImpl();
		boolean polik=true;
		while(polik)
		{
		System.out.println("Dobrodosli u korisnicki servis studentske sluzbe.Za nastavak izaberite opciju: ");
		System.out.println("a) Prijava ispita");
		System.out.println("b) Provera prijavljenih ispita");
		System.out.println("c) Kraj");
		
		Unos unos=new Unos();
		//int uno=Integer.parseInt(unos.getUserInput(""));
		String uno=unos.getUserInput("");
		//System.out.println(uno);
		if(uno.equals("a")) {
			System.out.println("Izabrali ste opciju za prijavu ispita");
			//int identifikator=Integer.parseInt(unos.getUserInput("Unesite broj indeksa: "));
			String identifikator=unos.getUserInput("Unesite broj indeksa: ");
			Student k=ESM.vratiStudenta(identifikator);
			//Prijava p=new PrijavaImpl();
			//Prijava vratiNovu=k.vratiPrijavu();
			//k.postaviPrijavu(p);
			
			String identi=(unos.getUserInput("Unesite naziv ispita: "));
			
			
			k.prijaviIspit(identi);
                        ESM.obavestiSve(k.vratiIndex(), identi);
			
			
			
			
			
		}
		else if(uno.equals("b")) {
			System.out.println("Izabrali ste opciju za prikaz ispita ");
			//int identifikator=Integer.parseInt(unos.getUserInput("Unesite broj indeksa: "));
			String identifikator=unos.getUserInput("Unesite broj indeksa: ");
			Student k=ESM.vratiStudenta(identifikator);
                        
			System.out.println(k.vratiIspite());
		}
		else if(uno.equals("c"))
                        ESM.unregistruj(cc);
			polik=false;
		}
		
		
                //int identifikator=Integer.parseInt(unos.getUserInput("Unesite indeks studenta: "));
                
              //  Student k=ESM.vratiStudenta(identifikator);
                
              //  System.out.println(k.vratiIndeks());
		
		
		
		
		
		
		}
		catch(RemoteException e)
		{
			System.out.println("Greska"+e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Greska"+e.getMessage());
		} 
    }

	public static void main(String[] args) {
		new Client();
		try {
			System.in.read();
		}catch(IOException ioException) {
			
		}
		System.exit(0);

	}
	
	public class EStudCallbackImpl extends UnicastRemoteObject implements EStudSluzbaCallback{

		public EStudCallbackImpl() throws RemoteException{
			
		}
		@Override
		public void callback(String indeks,String predmet) throws RemoteException {
			System.out.println("Student sa indeksom: "+indeks+" je prijavio predmet: "+predmet);
			
			
		}
		
	}

}
