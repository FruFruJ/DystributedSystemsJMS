import java.rmi.*;
import java.rmi.server.*;
public class KorisnikImpl extends UnicastRemoteObject implements Korisnik {

	private String broj;
	private int minuti;
	private int poruke;
	private int internet;
	private int minutaTarifa;
	private int porukeTarifa;
	private int internetTarifa;
	private Stanje korisnik;
	public KorisnikImpl(String broj,int min,int por,int inter,int minTar,int porTar,int intTar)throws RemoteException {
		this.broj=broj;
		minuti=min;
		poruke=por;
		this.internet=inter;
		minutaTarifa=minTar;
		porukeTarifa=porTar;
		internetTarifa=intTar;
		
	}
	@Override
	public void uplatiMinute(int minut) throws RemoteException {
		int cenaDodatka=minut*this.minutaTarifa;
		korisnik.dodajMinute(minut);
		korisnik.postaviRacun(cenaDodatka);
		
		
	}

	@Override
	public void uplatiPoruke(int poruke) throws RemoteException {
		int cenaDodatka=poruke*this.porukeTarifa;
		korisnik.dodajPoruke(poruke);
		korisnik.postaviRacun(cenaDodatka);
		
	}

	@Override
	public void uplatiInternet(int internet) throws RemoteException {
		int cenaDodatka=internet*this.internetTarifa;
		korisnik.dodajInternet(internet);
		korisnik.postaviRacun(cenaDodatka);
		
	}

	@Override
	public Stanje vratiStanje() throws RemoteException {
		// TODO Auto-generated method stub
		return this.korisnik;
	}
	@Override
	public String vratiBroj() throws RemoteException {
		// TODO Auto-generated method stub
		return this.broj;
	}
	@Override
	public void postaviStanje(Stanje k) throws RemoteException {
		// TODO Auto-generated method stub
		this.korisnik=k;
		
	}

}
