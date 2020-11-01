import java.rmi.*;
public interface Korisnik extends Remote {
	void uplatiMinute(int minut)throws RemoteException;
	void uplatiPoruke(int poruke)throws RemoteException;
	void uplatiInternet(int internet)throws RemoteException;
	Stanje vratiStanje()throws RemoteException;
	String vratiBroj()throws RemoteException;
	void postaviStanje(Stanje k)throws RemoteException;

}
