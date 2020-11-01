import java.rmi.*;
public interface OperaterManager extends Remote {
	Korisnik vratiKorisnika(String broj)throws RemoteException;
	void posaljiObavestenje(String poruka)throws RemoteException;
	void dodajKorisnika(Korisnik k)throws RemoteException;
	void register(OperaterCallback cli)throws RemoteException;
	void unregister(OperaterCallback cli)throws RemoteException;
	

}

