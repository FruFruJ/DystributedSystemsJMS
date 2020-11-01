/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Racuni;

import javax.jms.Message;
import javax.jms.MessageListener;
import static Racuni.Centrala.racuni;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import static Racuni.Centrala.ictx;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.QueueReceiver;
import javax.naming.NamingException;
/**
 *
 * @author Joks
 */
public class CentralaVratiRacunePaListing implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("CentralaVratiRacunePaLIsting");
            List<String> lista=new ArrayList<String>();
            
            for(Racun r :racuni)
            {
            lista.add(r.id);
            }
            ictx = new InitialContext();
        
    
            Queue qPoeni = (Queue) ictx.lookup("jms/RacuniPaListing");   
            Queue qListing=(Queue) ictx.lookup("jms/Listing");
            
            QueueConnectionFactory QCF = (QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");
        
            ictx.close();
            
            QueueConnection QC = QCF.createQueueConnection(); 
            QueueSession QS = QC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueSender qsendPoeni = QS.createSender(qPoeni);
            QueueReceiver qrecvList=QS.createReceiver(qListing);
            qrecvList.setMessageListener(new CentralaPrikaziML());

            QC.start();

           ObjectMessage msgPoeni = QS.createObjectMessage();
           msgPoeni.setObject((Serializable) lista);
           System.out.println(lista);
           qsendPoeni.send(msgPoeni);
           QC.close(); 
           } catch (NamingException ex) {
            Logger.getLogger(CentralaVratiRacunePaListing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(CentralaVratiRacunePaListing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
