
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
public class GymReservationClient {
    
    public GymReservationClient(){
    }
    public static void main(String[] args) throws RemoteException
    { 
        GymReservationManager grm;
        try {
            
            grm = (GymReservationManager)Naming.lookup("rmi://localhost:1099/gym");
             System.out.println("Client started");
             Reservation r=grm.makeReservation(2, 2, 4, 4);
             grm.extendReservation(r, 3);
             grm.cancelReservation(r);
        } catch (NotBoundException ex) {
            Logger.getLogger(GymReservationClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GymReservationClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(GymReservationClient.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
}
