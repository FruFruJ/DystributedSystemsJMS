/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joks
 */
public class TaxiManagerImpl extends UnicastRemoteObject implements TaxiManager {

    public List<Taxi> listaVozila;
    public List<String> listaAdresa;
    public List<TaxiCallback> callbacks;
    
    public TaxiManagerImpl() throws RemoteException
    {
        listaVozila=new ArrayList<Taxi>();
        listaAdresa=new ArrayList<String>();
        callbacks=new ArrayList<TaxiCallback>();
    }
    
    @Override
    public boolean requestTaxi(String address) throws RemoteException {
       for(Taxi t:listaVozila)
        {
            if(t.isFree)
            {
                    t.address=address;
                    for(int i=0;i<callbacks.size();i++)
                    {
                        callbacks.get(i).notifyTaxi(address);
                    }
                    t.isFree=false;
                    return true;
                
                
            }
            
        }
       listaAdresa.add(address);
       return false;
    }

    @Override
    public void setTaxiStatus(String id, boolean isFree) throws RemoteException {
        for(Taxi t:listaVozila)
        {
            if(t.id.equals(id))
            {
            t.isFree=isFree;
                if(isFree&&listaAdresa.size()>0)
                {
                    t.address=listaAdresa.remove(0);
                    for(int i=0;i<callbacks.size();i++)
                    {
                        callbacks.get(i).notifyTaxi(t.address);
                    }
                }
                
            }
            
        }
    }

    @Override
    public void Register(TaxiCallback tc) throws RemoteException {
       callbacks.add(tc);
    }

    @Override
    public void Deregister(TaxiCallback tc) throws RemoteException{
     callbacks.remove(tc);
    }

    @Override
    public void addTaxi(Taxi x) throws RemoteException {
        listaVozila.add(x);
    }
    
}
