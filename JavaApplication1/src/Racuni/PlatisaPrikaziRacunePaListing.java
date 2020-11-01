/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Racuni;

import java.util.List;
import javax.jms.Message;
import javax.jms.MessageListener;
import Racuni.Racun;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import static Racuni.Platisa.ictx;
import java.io.IOException;
import java.util.Scanner;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author Joks
 */
public class PlatisaPrikaziRacunePaListing implements MessageListener {

    public PlatisaPrikaziRacunePaListing() {
        
    }

    @Override
    public void onMessage(Message message) {
        try {
            Scanner scan = new Scanner(System.in);
            List<String> racuni=message.getBody(List.class);
            
            for(String rac:racuni)
                {
                        System.out.println(rac+"\n");
                                            
                }
            System.out.println("Unesite id racuna");
            
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String uneto =  bufferedReader.readLine();
            System.in.read();
            ictx = new InitialContext();
    
            Queue qListing = (Queue) ictx.lookup("jms/Listing");   
            Queue qList=(Queue) ictx.lookup("jms/ListaListing");
            QueueConnectionFactory QCF = (QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");
        
            ictx.close();
            
            QueueConnection QC = QCF.createQueueConnection(); 
            QueueSession QS = QC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver qrlist=QS.createReceiver(qList);
            qrlist.setMessageListener(new PlatisaPrikaziListing());
            QueueSender qsendPoeni = QS.createSender(qListing);
            

            QC.start();

           Message msgPoeni = QS.createMessage();
           msgPoeni.setStringProperty("id" ,uneto);

           qsendPoeni.send(msgPoeni);
           System.out.println(uneto);
           QC.close(); 
        } catch (JMSException ex) {
            Logger.getLogger(PlatisaPrikaziRacunePaListing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(PlatisaPrikaziRacunePaListing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlatisaPrikaziRacunePaListing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
