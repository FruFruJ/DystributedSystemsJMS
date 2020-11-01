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
import javax.jms.QueueSender;
import javax.jms.TextMessage;
import static recepti.Klijent.vracamRecept;

/**
 *
 * @author Joks
 */
public class ImamReceptListener implements MessageListener {

    public ImamReceptListener() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            QueueSender vracamRecept=Klijent.QS.createSender(Klijent.vracamRecept);
            String recept=message.getStringProperty("recept");
            
            String receptZaSlanje=Klijent.recepti.get(Klijent.receptiIme.indexOf(recept));
            TextMessage tm1=Klijent.QS.createTextMessage(receptZaSlanje);
            tm1.setStringProperty("id", message.getStringProperty("id"));
            vracamRecept.send(tm1);
            
        } catch (JMSException ex) {
            Logger.getLogger(ImamReceptListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
