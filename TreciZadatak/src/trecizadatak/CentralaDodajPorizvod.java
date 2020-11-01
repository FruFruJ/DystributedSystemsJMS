/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trecizadatak;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import trecizadatak.Proizvod;
import static trecizadatak.Centrala.ictx;
import static trecizadatak.Centrala.proizvodi;
/**
 *
 * @author Joks
 */
public class CentralaDodajPorizvod implements MessageListener {

    public CentralaDodajPorizvod() {
    }

    @Override
    public void onMessage(Message message) {
        try {
        System.out.println("lalala");
        String imeP=message.getStringProperty("imeProizvoda");
        
       int kolicina=message.getIntProperty("kolicina");
       proizvodi.add(new Proizvod(imeP,kolicina));
       ictx = new InitialContext();
    
            Topic qtDodato = (Topic) ictx.lookup("jms/dodatProizvod");      
            TopicConnectionFactory TCF = (TopicConnectionFactory) ictx.lookup("TFaktori");
        
            ictx.close();
            
            TopicConnection TC = TCF.createTopicConnection(); 
            TopicSession TS = TC.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);

            TopicPublisher tp = TS.createPublisher(qtDodato);
            


          TC.start();

           TextMessage msgPoeni = TS.createTextMessage();
            tp.publish(msgPoeni);
            TS.commit();
       
            TC.close();
        }
       catch (JMSException ex) {
            Logger.getLogger(CentralaDodajPorizvod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CentralaDodajPorizvod.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
}
