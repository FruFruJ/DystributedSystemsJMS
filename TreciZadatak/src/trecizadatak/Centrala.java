/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trecizadatak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Joks
 */
public class Centrala {
    
    static Context ictx=null;
    public static List<Proizvod> proizvodi=new ArrayList<Proizvod>();
    
     public static void main(String[] args) throws NamingException, JMSException, IOException {
                
         proizvodi.add(new Proizvod("banana",2));
         proizvodi.add(new Proizvod("milka",10));
         
            
        ictx = new InitialContext();
        
        Queue qdodajProizvod=(Queue) ictx.lookup("jms/dodajProizvodQ");
        //Queue qPitanja = (Queue) ictx.lookup("jms/Pitanja");
        //Queue qRegistruj = (Queue) ictx.lookup("jms/Registruj");
        //Queue qPoeni = (Queue) ictx.lookup("jms/Poeni");
        QueueConnectionFactory QCF = (QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");
        
        ictx.close();
        
        QueueConnection QC = QCF.createQueueConnection(); 
        QueueSession QS = QC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        QueueReceiver tconsDodajProizvod=QS.createReceiver(qdodajProizvod);
        tconsDodajProizvod.setMessageListener(new CentralaDodajPorizvod());
        
        
        //QueueReceiver  qrecvRegistruj=QS.
        
        
       // QueueSender qsendDodajPitanje = QS.createSender(qDodajPitanje);        
        //QueueSender qsendIgrajKviz = QS.createSender(qIgrajKviz);       
      
//  QueueSender qsendOdgovori = QS.createSender(qOdgovori);
        
        QC.start();
        
        System.in.read();
        
  
        QC.close();
        
    }
    
}
