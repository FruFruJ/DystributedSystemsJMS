/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

/**
 *
 * @author Joks
 */
public class CentralaVratiRacuneML implements MessageListener {



    @Override
    public void onMessage(Message message) {
             
        try {
            
           List<String> listaRacuna=new ArrayList<String>();
              System.out.println("Centrala vrati racune ML");
              listaRacuna.add("456");
            for(Racun racun :racuni)
           {
             listaRacuna.add(racun.id);
             
           }
            System.out.println(listaRacuna);
          
           ictx=new InitialContext();
           
           Queue qVratiSveRacune=(Queue) ictx.lookup("jms/vratiRacunePlacanje");
           Queue qplatiTroskove=  (Queue) ictx.lookup("jms/platiTroskove");
           QueueConnectionFactory QCF=(QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");
           
           ictx.close();
           
           QueueConnection QC=QCF.createQueueConnection();
           QueueSession QS=QC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
           QC.start();
           
           QueueReceiver qRecvPlatiTroskove=QS.createReceiver((javax.jms.Queue) qplatiTroskove);
           qRecvPlatiTroskove.setMessageListener(new CentralaPlatiML());
           
           QueueSender qsendRacuni=QS.createSender(qVratiSveRacune);
           ObjectMessage msgOdgovori = QS.createObjectMessage();

            msgOdgovori.setObject((Serializable) listaRacuna);
            
            
            qsendRacuni.send(msgOdgovori);
            System.out.println("izaso2\n");
           QC.close();
            
            
        } catch (NamingException ex) {
            Logger.getLogger(CentralaPlatiML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(CentralaPlatiML.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    
}
