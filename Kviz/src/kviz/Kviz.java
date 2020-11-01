/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kviz;

import java.util.ArrayList;
import javax.jms.*;
import java.*;
import javax.naming.*;

/**
 *
 * @author Joks
 */
public class Kviz {

  public static ArrayList<Pitanje> pitanja;
  static Context ictx;
  public Kviz()
  {
  pitanja=new ArrayList<Pitanje>();
  }

    public static void main(String[] args) throws NamingException, JMSException {
       
       ictx= new InitialContext();
       Queue dodajPitanje=(Queue)ictx.lookup("jms/addQuestion");
       Queue igraj=(Queue)ictx.lookup("jms/playQuiz");
       Queue odgovori=(Queue)ictx.lookup("jms/Answers");
      
       
       QueueConnectionFactory qcf=(QueueConnectionFactory)ictx.lookup("jms/QueueConnectionFactory");
       
       ictx.close();
       
       QueueConnection qc=qcf.createQueueConnection();
       QueueSession qs=qc.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
       
       QueueReceiver qrDodajPitanje=qs.createReceiver(dodajPitanje);
       qrDodajPitanje.setMessageListener(new CentralaDodajPitanje());
       
      
       
       QueueReceiver qrIgraj=qs.createReceiver(igraj);
       qrIgraj.setMessageListener(new CentralaIgraj());
       
       QueueReceiver qrOdgovori=qs.createReceiver(odgovori);
       qrOdgovori.setMessageListener(new CentralaOdgovori());
    }
    
}
