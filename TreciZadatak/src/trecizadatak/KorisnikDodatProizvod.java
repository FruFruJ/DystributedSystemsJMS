package trecizadatak;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class KorisnikDodatProizvod implements MessageListener {

    public KorisnikDodatProizvod() {
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("poslata je poruka");
        String pom;
        try {
            pom = message.getStringProperty("vrednost");
            System.out.println(pom);
        } catch (JMSException ex) {
            Logger.getLogger(KorisnikDodatProizvod.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
