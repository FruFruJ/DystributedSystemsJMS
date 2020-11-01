import java.rmi.*;
public interface OperaterCallback extends Remote {
	void callback(String k)throws RemoteException;

}