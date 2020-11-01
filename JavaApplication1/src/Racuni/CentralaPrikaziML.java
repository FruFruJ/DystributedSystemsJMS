package Racuni;



import static Racuni.Centrala.ictx;
import static Racuni.Centrala.racuni;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class CentralaPrikaziML implements MessageListener {

    @Override
    public void onMessage(Message message) {
       
        try {
            System.out.println("CentralaPrikaziML");
            List<String> lista=new ArrayList<String>();
            String id=message.getStringProperty("id");
            for(Racun racun:racuni)
            {
            if(id.compareTo(racun.id)==0)
                lista=racun.isplate;
            }
            
            
            ictx= new InitialContext();
            
            Queue qListing=(Queue) ictx.lookup("jms/ListaListing");
            QueueConnectionFactory QCF=(QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");
            
            ictx.close();
            
            QueueConnection QC=(QueueConnection) QCF.createConnection();
            QueueSession QS=QC.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
            
            QueueSender qsendListing=QS.createSender(qListing);
            QC.start();
            System.out.println("kaj");
            System.out.println(id);
            ObjectMessage msgListing=QS.createObjectMessage((Serializable) lista);
            qsendListing.send(msgListing);
            QC.close();
            
        } catch (JMSException ex) {
            Logger.getLogger(CentralaPrikaziML.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (NamingException ex) {
                Logger.getLogger(CentralaPrikaziML.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

  
    
}
