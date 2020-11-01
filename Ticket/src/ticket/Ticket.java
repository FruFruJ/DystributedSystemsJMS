/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Time;
import java.util.Vector;

/**
 *
 * @author Joks
 */
public class Ticket implements Serializable {
private static int counter;
public int id;
public Vector<Integer> tiket;

public Ticket(Vector<Integer> tiketP) throws RemoteException
{
    tiket=tiketP;
    id=counter++;
   
}

   
    
}
