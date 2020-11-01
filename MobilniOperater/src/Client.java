import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.*;


public class Client {
	
	private OperaterManager OM;
	private OperaterCallback cc;
	public Client() {
	//private EStudCallback cc;
    	cc=null;
       try {
		OM=(OperaterManager)Naming.lookup("rmi://localhost:1099/ListaKorisnika");
		System.out.println("Nadjen server");
		Unos unos=null;
		boolean beskonacno=true;
		cc=new OperaterCallbackImpl();
		OM.register(cc);
		while(beskonacno)
		{
		System.out.println("Dobrodosli na mobitel servis ");
		System.out.println("a) Administratorski mod: ");
		System.out.println("b) Korisnicki mod: ");
		unos=new Unos();
		//int uneto=Integer.parseInt(unos.getUserInput(""));
		String uneto=unos.getUserInput("");
		if(uneto.equals("b")) {
			System.out.println("a)Uplata minuta ");
			System.out.println("b)Uplata poruka ");
			System.out.println("c)Uplata interneta ");
			System.out.println("d)Provera stanje ");
			System.out.println("e)Kraj");
			 uneto=unos.getUserInput("/>");
			 
			 //int broj=Integer.parseInt(unos.getUserInput("Unesite broj korisnika"));
			 String broj=unos.getUserInput("Unesite broj korisnika");
			 
			 Korisnik ref=OM.vratiKorisnika(broj);
			 
			 if(uneto.equals("a")) {
				 int brojj=Integer.parseInt(unos.getUserInput("Unesite broj minuta"));
				// broj=unos.getUserInput("Unesite broj minuta");
				 ref.uplatiMinute(brojj);
				 //System.out.println(ref.vratiBroj());
				 System.out.println("Trenutni broj minuta  je: "+ref.vratiStanje().vratiMinute());
			 }
			 else if(uneto.equals("b")) {
				 int brojj=Integer.parseInt(unos.getUserInput("Unesite broj poruka"));
				 ref.uplatiPoruke(brojj);
				 
			 }
			 else if(uneto.equals("c")) {
				int brojj=Integer.parseInt(unos.getUserInput("Unesite kolicinu interneta"));
				 ref.uplatiInternet(brojj);
			 }
			 else if(uneto.equals("d")) {
				 float racun=ref.vratiStanje().vratiRacun();
				 System.out.println("Trenutni racun je: "+racun);
			 }
			 else if(uneto.equals("e"))
				 beskonacno=false;
			
			
			
		}
		else if(uneto.equals("a"))
		{
			System.out.println("\n Dobrodosli u administratorski mod ");
			System.out.println("a) Posalji svima obavestenje ");
			System.out.println("b) Izadji ");
			uneto=unos.getUserInput("");
			if(uneto.equals("a")) {
				String obavestenje=unos.getUserInput("Unesite obavestenje");
				OM.posaljiObavestenje(obavestenje);
			}
			else if(uneto.equals("b"))
				beskonacno=false;
			
		}
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
	
	public static void main(String[] args) {
		new Client();
		try {
			System.in.read();
		}catch(IOException ioException) {
			
		}
		System.exit(0);

	}
	
	public class OperaterCallbackImpl extends UnicastRemoteObject implements OperaterCallback{
		
		public OperaterCallbackImpl()throws RemoteException {
			
		}
		@Override
		public void callback(String k) throws RemoteException {
			System.out.print(k);
			
		}
		
	}

}
