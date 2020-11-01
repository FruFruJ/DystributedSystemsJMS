import java.rmi.*;
import java.rmi.server.*;
public class StanjeImpl extends UnicastRemoteObject implements Stanje {

	private String broj;
	private int minuta;
	private int poruke;
	private int internet;
	private float racun;
	public StanjeImpl(String broj,int minuta,int poruke,int internet,float raccun)throws RemoteException{
		this.broj=broj;
		this.minuta=minuta;
		this.poruke=poruke;
		this.internet=internet;
		this.racun=raccun;
	}
	@Override
	public int vratiMinute() throws RemoteException {
		// TODO Auto-generated method stub
		return this.minuta;
	}

	@Override
	public int vratiPoruke() throws RemoteException {
		// TODO Auto-generated method stub
		return this.poruke;
	}

	@Override
	public int vratiInternet() throws RemoteException {
		// TODO Auto-generated method stub
		return this.internet;
	}

	@Override
	public float vratiRacun() throws RemoteException {
		// TODO Auto-generated method stub
		return this.racun;
	}
	@Override
	public void dodajMinute(int minuti) throws RemoteException {
		this.minuta+=minuti;
		
	}
	@Override
	public void dodajPoruke(int poruke) throws RemoteException {
		this.poruke+=poruke;
		
	}
	@Override
	public void dodajInternet(int internet) throws RemoteException {
		// TODO Auto-generated method stub
		this.internet+=internet;
		
	}
	@Override
	public void postaviRacun(float k) throws RemoteException {
		// TODO Auto-generated method stub
		this.racun+=k;
		
	}
	

}

