
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
private EAukcijaManager EAM;
private EAukcijaCallback cc;
	public Client() {
		try {
			EAM=null;
			cc=null;
		EAM=(EAukcijaManager)Naming.lookup("rmi://localhost:1089/ListaPredmeta");
		System.out.println("Nadjen server");
		
		cc=new EAukcijaCallbackImpl();
		EAM.registruj(cc);
		
		Unos unos=new Unos();
		boolean beskonacno=true;
		boolean beskonacno1=true;
		while(beskonacno)
		{
		System.out.println("Dobrodosli na elektronsku aukciju. Za nastavak unesite vase licne podatke");
		String identifikator=unos.getUserInput("Identifikator:\n /> ");
		String ime=unos.getUserInput("Ime:\n /> ");
		String prezime=unos.getUserInput("Prezime:\n /> ");
		
		KlijentAukcije klijent=new KlijentAukcije(identifikator,ime,prezime);
		while(beskonacno1) {
		//int idEksponat=Integer.parseInt(unos.getUserInput("Unesite identifikator eksponata: \n />"));
		String idEksponat=unos.getUserInput("Unesite identifikator za eksponat od interesa: \n />");
		
		Eksponat l=EAM.vratiEksponat(idEksponat);
		l.prijaviLicitaciju(klijent);
		System.out.println("Cena ekponata je: \n />"+l.vratiCenu());
		//l.vratuCenu();
		
		System.out.println("a) Licitacija ");
		System.out.println("b) Odustajanje ");
		
		String prom=unos.getUserInput("");
		
		if(prom.equals("a")) {
			//System.out.println("Uvecavate cenu za: ");
			int povecaj=Integer.parseInt(unos.getUserInput("Za koliko uvecavate iznos eksponata? "));
			l.povecajCenu(povecaj);
                        EAM.obavestiSve(klijent.klijentAukcijeId, l.vratiCenu());
		}
		else if(prom.equals("b"))
                {
                        EAM.unregistruj(cc);
			beskonacno1=false;
                }
		//System.out.println(l.vratiNaziv());
		//System.out.println(klijent.ime);
		//Eksponat molika=EAM.vratiEksponat(idEksponat);
		//molika.prikaziListu();
		}
		beskonacno1=true;
		//l.prijaviLicitaciju(klijent,0);
		//l.prijaviLicitaciju(klijent);
		/*int dodatacCena=Integer.parseInt(unos.getUserInput("Unesite cenu eksponata: "));
		
		l.povecajCenu(dodatacCena);
		
		System.out.println("Trenutna cena je: "+l.vratuCenu());
		l.prikaziPodatke();*/
		
		
		
		
		
		}
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
	/*public void prikaziPodatke()
	{
		for(KlijentAukcije c:)
	}*/
	public void prikaziCenu()
	{
		
	}

	public static void main(String[] args) {
		
		new Client();
		try {
			System.in.read();
		}catch(IOException ioException) {
			
		}
		System.exit(0);
		

	}
	public class EAukcijaCallbackImpl extends UnicastRemoteObject implements EAukcijaCallback{

            private static final long serialVersionUID = 1L;
            public EAukcijaCallbackImpl() throws RemoteException{
			
		}

        @Override
        public void obavesti(String klijent, int cena) {
            System.out.println("Klijent "+klijent+" je povecao cenu za "+cena);
        }
       

		/**
		 * 
		 */
		
	}

}
