package Racuni;



import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import static  Racuni.Centrala.ictx;
import static Racuni.Centrala.racuni;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class CentralaPlatiML implements MessageListener{


    @Override
    public void onMessage(Message message) {
        
        try {
            System.out.println("centrala plati");
            String id=message.getStringProperty("id");
            int novac=message.getIntProperty("novac");
            String cilj=message.getStringProperty("cilj");
            System.out.println("centrala plati"+id+" "+cilj);
            for(Racun racun:racuni)
            {
            if(id.compareTo(racun.id)!=0)
               {
                racun.plati(novac, cilj);
                //moze if da se doda
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(CentralaPlatiML.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
    }

 
    
}
