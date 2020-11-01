import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
public class OperaterManagerImpl extends UnicastRemoteObject implements OperaterManager {
	private ArrayList<Korisnik> lista=new ArrayList<Korisnik>();
	private ArrayList<OperaterCallback> clients = new ArrayList<OperaterCallback>();

	
	public OperaterManagerImpl()throws RemoteException {
		
	}
	@Override
	public Korisnik vratiKorisnika(String broj) throws RemoteException {
		Korisnik pom=null;
		
		for(Korisnik c:lista) {
			if(c.vratiBroj().equals(broj))
				pom=c;
		}
		return pom;
	}

	@Override
	public void posaljiObavestenje(String poruka) throws RemoteException {
		
		for(OperaterCallback c:clients) {
			if(c!=null) {
				c.callback(poruka);
			}
		}
	}
	@Override
	public void dodajKorisnika(Korisnik k) throws RemoteException {
		lista.add(k);
		
	}
	@Override
	public synchronized void register(OperaterCallback cli) throws RemoteException {
		// TODO Auto-generated method stub
		clients.add(cli);
		
	}
	@Override
	public synchronized void unregister(OperaterCallback cli) throws RemoteException {
		// TODO Auto-generated method stub
		clients.remove(cli);
		
	}
	

}