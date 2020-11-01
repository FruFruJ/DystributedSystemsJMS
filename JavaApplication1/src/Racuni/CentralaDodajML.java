package Racuni;



import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import static Racuni.Centrala.racuni;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class CentralaDodajML implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Dodavanje");
            Racun noviRacun=message.getBody(Racun.class);
            racuni.add(noviRacun);
            for(Racun rac:racuni)
            {
            System.out.println(rac.id);
            }
            System.out.println(noviRacun.stanje);
            
        } catch (JMSException ex) {
            Logger.getLogger(CentralaDodajML.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
}
