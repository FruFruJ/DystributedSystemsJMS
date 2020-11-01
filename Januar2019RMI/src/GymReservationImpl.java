
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class GymReservationImpl extends java.rmi.server.UnicastRemoteObject implements GymReservationManager {

   private ArrayList<Reservation> reservations;

   
   public GymReservationImpl() throws java.rmi.RemoteException
   {
    this.reservations=new ArrayList<Reservation>();
   }
    
    public Reservation makeReservation(int day, int month, int hour, int numHours) throws RemoteException {
        System.out.println("Napravljenja rezervacija");
        Reservation res=new Reservation(day,month,hour,numHours);
        this.reservations.add(res);
        return res;
    }

    @Override
    public Reservation extendReservation(Reservation res, int numExtraHours) throws RemoteException {
      for(int i=0;i<reservations.size();i++)
      {
          if(reservations.get(i).id==res.id)
          {
              if(reservations.get(i).numHours+numExtraHours<24)
              {
                  reservations.get(i).numHours+=numExtraHours;
                  return reservations.get(i);
              }
          }
          System.out.println("Rezervacija broj "+reservations.get(i).id);
      }
      return null;
    }

    @Override
    public void cancelReservation(Reservation res) throws RemoteException {
      for(int i=0;i<reservations.size();i++)
      {
          if(reservations.get(i).id==res.id)
          {
             reservations.remove(i);
          }
      }
      System.out.println(reservations.size());
    }
    
}
