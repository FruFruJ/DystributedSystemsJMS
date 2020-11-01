package Racuni;

import Racuni.Racun;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import static Racuni.Platisa.ictx;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.naming.NamingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */
public class PlatisaPrikaziRacune implements MessageListener {
Platisa pp;
public PlatisaPrikaziRacune(Platisa p){
   this.pp=p;
 }
    @Override
    public void onMessage(Message message) {
        
  
        try{
           List<String> pom=new ArrayList<String>();
           pom=message.getBody(List.class);
           System.out.println("platisa prikazi racune");
           System.out.println(pom);
            for(String r:pom)
                {
                    System.out.println(r);
                }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("unesite brojRacuna\n unesite vasuSumu\n unesite cilj\n");
            String brojRacuna =  bufferedReader.readLine();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            int novac =  parseInt(br1.readLine());
            //posalji odgovore i svoj id
            ictx = new InitialContext();
				 
            Queue qOdgovori = (Queue) ictx.lookup("jms/platiTroskove");
            QueueConnectionFactory QCF = (QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");

            ictx.close();

            QueueConnection QC = QCF.createQueueConnection(); 
            QueueSession QS = QC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueSender qsendOdgovori = QS.createSender(qOdgovori);


            QC.start();

            ObjectMessage msgOdgovori = QS.createObjectMessage();

            msgOdgovori.setStringProperty("id", brojRacuna);
            msgOdgovori.setStringProperty("cilj","neki cilj");
            msgOdgovori.setIntProperty("novac",5000);
            
            
            qsendOdgovori.send(msgOdgovori);
             System.out.println("izaso1\n"+msgOdgovori);
            QC.close();
            
             
        } catch (JMSException ex) {
            Logger.getLogger(PlatisaPrikaziRacune.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlatisaPrikaziRacune.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(PlatisaPrikaziRacune.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
   }






    

