/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Racuni;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Joks
 */
public class PlatisaPrikaziListing implements MessageListener {

    public PlatisaPrikaziListing() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            List<String> lista=message.getBody(List.class);
            System.out.println("Listing treba da se stampa");
            for(String l:lista)
            {
            System.out.println(l);
            }
        } catch (JMSException ex) {
            Logger.getLogger(PlatisaPrikaziListing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
