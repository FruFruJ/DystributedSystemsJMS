/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nadgledanjeautobusa;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Joks
 */
public class SviCujte implements MessageListener {

    public SviCujte() {
    }

    @Override
    public void onMessage(Message message) {
       
        try {
            System.out.println(message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(SviCujte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
