import java.rmi.*;
public interface Stanje extends Remote {
	int vratiMinute()throws RemoteException;
	int vratiPoruke()throws RemoteException;
	int vratiInternet()throws RemoteException;
	float vratiRacun()throws RemoteException;
	void dodajMinute(int minuti)throws RemoteException;
	void dodajPoruke(int poruke)throws RemoteException;
	void dodajInternet(int internet)throws RemoteException;
	void postaviRacun(float k)throws RemoteException;
	

}

