/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recepti;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.Session;
import javax.jms.TextMessage;
import static recepti.Klijent.QCF;
import static recepti.Klijent.QS;
import static recepti.Klijent.caoITebi;

/**
 *
 * @author Joks
 */
public class IdentifikujSe implements MessageListener {

    
    public IdentifikujSe(String id) {
       
    }

    @Override
    public void onMessage(Message message) {
        try {
            
                 
            String id=message.getStringProperty("id");
            System.out.println(message.getBody(String.class));
            QueueSender qsPozdravITebi=Klijent.QS.createSender(Klijent.caoITebi);
            TextMessage tm=QS.createTextMessage("Pozdrav i tebi od mene "+Klijent.id);
            tm.setStringProperty("id", id);
            qsPozdravITebi.send(tm);
        } catch (JMSException ex) {
            Logger.getLogger(IdentifikujSe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
