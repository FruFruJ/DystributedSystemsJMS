package Racuni;



import static com.sun.common.util.logging.LoggingXMLNames.jms;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class Centrala {
    
    static Context ictx=null;
    
    public static List<Racun> racuni=new ArrayList<Racun>();
    
   public static void main(String[] args) throws NamingException, JMSException, IOException
    {
            racuni.add(new Racun(5000000,"Princess","10345"));
            racuni.add(new Racun(10000,"orbdg","24305"));
            
            ictx= new InitialContext();
            
            Queue qplatiTroskove=  (Queue) ictx.lookup("jms/platiTroskove");
            
            Queue qdodajRacun=(Queue) ictx.lookup("jms/dodajRacun");
            
            Queue qListing=(Queue) ictx.lookup("jms/prikaziListing");
            
            Queue qracuni=(Queue) ictx.lookup("jms/klijentRacune");
            
            QueueConnectionFactory QCF=(QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");
            
            
            
            ictx.close();
            
            QueueConnection QC=QCF.createQueueConnection();
            
            QueueSession QS=QC.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
            
            QueueReceiver qRecvPlatiTroskove=QS.createReceiver((javax.jms.Queue) qplatiTroskove);
            qRecvPlatiTroskove.setMessageListener(new CentralaPlatiML());
            
            QueueReceiver qRecvDodajRacun=QS.createReceiver((javax.jms.Queue) qdodajRacun);
            qRecvDodajRacun.setMessageListener(new CentralaDodajML());
            
            QueueReceiver qRecvPrikazi=QS.createReceiver((javax.jms.Queue) qListing);
            qRecvPrikazi.setMessageListener(new CentralaVratiRacunePaListing());
            
            QueueReceiver qVratiRacune=QS.createReceiver((javax.jms.Queue) qracuni);
            qVratiRacune.setMessageListener(new CentralaVratiRacuneML());
            
            
            QC.start();
            
            System.in.read();
            
            QC.close();
    }
    
}
