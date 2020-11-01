
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class GymReservationServer {
    
   
    public GymReservationServer(){
        
    }
    
    public static void main(String args[]){
        try {
            GymReservationManager grm=new GymReservationImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/gym",grm);
        } catch (RemoteException ex) {
            Logger.getLogger(GymReservationServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GymReservationServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server started");
    }
    
}
