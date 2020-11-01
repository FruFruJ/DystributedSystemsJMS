/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Joks
 */
public class ObavestenjeListener implements MessageListener {

    public ObavestenjeListener() {
    }

    @Override
    public void onMessage(Message message) {
        double novoZagadjenje;
        try {
            novoZagadjenje = message.getDoubleProperty("z");
             System.out.println("Obavestenje prijavio se! ");
   System.out.println("Njegove koordinate su:"+message.getIntProperty("x")+" njegova y koordinata je "+message.getIntProperty("y")+" njegovo zagadnjenje je "+novoZagadjenje);
  
        } catch (JMSException ex) {
            Logger.getLogger(ObavestenjeListener.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
    }
    
}
