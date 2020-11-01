/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public interface GymReservationManager extends java.rmi.Remote{
    public Reservation makeReservation(int day,int month,int hour,int numHours) throws java.rmi.RemoteException;
    public Reservation extendReservation(Reservation res,int numExtraHours) throws java.rmi.RemoteException;
    public void cancelReservation(Reservation res) throws java.rmi.RemoteException;
}
