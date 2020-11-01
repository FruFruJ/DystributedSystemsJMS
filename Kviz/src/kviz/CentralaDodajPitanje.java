/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kviz;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.MessageListener;
import javax.jms.*;
/**
 *
 * @author Joks
 */
public class CentralaDodajPitanje implements MessageListener {

    public CentralaDodajPitanje() {
    }
    public void onMessage(Message message)
    {
        try {
            Pitanje novo=message.getBody(Pitanje.class);
            Kviz.pitanja.add(novo);
        } catch (JMSException ex) {
            Logger.getLogger(CentralaDodajPitanje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
}
