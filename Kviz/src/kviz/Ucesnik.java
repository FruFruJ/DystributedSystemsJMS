/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kviz;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.*;
import javax.*;
import java.*;
import java.io.*;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.*;
import static kviz.Kviz.ictx;

/**
 *
 * @author Joks
 */
public class Ucesnik {
    static Context ictx;
    static int counter=0;
    public int id;
    public Ucesnik()
    {
    this.id=counter++;
    }
    
    public static void main(String[] args) throws NamingException, JMSException,IOException
    {
        Ucesnik uc=new Ucesnik();
        ictx=new InitialContext();
        Queue dodajPitanje=(Queue)ictx.lookup("jms/addQuestion");
       Queue izmeniPitanje=(Queue)ictx.lookup("jms/ChangeQuestion");
       Queue igraj=(Queue)ictx.lookup("jms/playQuiz");
       Queue novoPitanje=(Queue)ictx.lookup("jms/NewQuestion");
       Queue odgovori=(Queue)ictx.lookup("jms/Answers");
       Queue poeni=(Queue)ictx.lookup("jms/Poeni");
       
       QueueConnectionFactory qcf=(QueueConnectionFactory)ictx.lookup("jms/QueueConnectionFactory");
        
        ictx.close();
        
        QueueConnection qc=qcf.createQueueConnection();
        QueueSession qs=qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        QueueSender qsDodajPitanje=qs.createSender(dodajPitanje);
        QueueSender qsIzmeniPitanje=qs.createSender(izmeniPitanje);
        QueueSender qsIgraj=qs.createSender(igraj);
        QueueSender qsNovoPitanje=qs.createSender(novoPitanje);
        
       qc.start();
       System.out.println("Unesite broj jedne opcije: \n 1) Dodaj pitanje \n 2) Izmeni pitanje \n 3) Kviz \n");
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       String uneto=br.readLine();
       while(Integer.parseInt(uneto)!=4)
       {
           if(Integer.parseInt(uneto)==1)
           {
               Pitanje nPitanje=new Pitanje("1+2=?","3");
               
               ObjectMessage porukaNovoPitanje=qs.createObjectMessage();
               porukaNovoPitanje.setObject(nPitanje);
               qsDodajPitanje.send(porukaNovoPitanje);
           }
           else if(Integer.parseInt(uneto)==2)
               
           {
               System.out.println("Pocinjemo sa kvizom");
               TextMessage porukaPocni=qs.createTextMessage();
               qsIgraj.send(porukaPocni);
               
               QueueReceiver qrKviz=qs.createReceiver(odgovori);
               qrKviz.setMessageListener(new KvizReceiver(uc));
               
               QueueReceiver qrPoeni=qs.createReceiver(poeni,"id="+uc.id);
               qrPoeni.setMessageListener(new PoeniReciver(uc));
               
           }
            else
           {
           break;
           }
           
           System.out.println("Unesite broj jedne opcije: \n 1) Dodaj pitanje \n 2) Izmeni pitanje \n 3) Kviz \n");
    
             uneto=br.readLine();
       }
       
       qc.close();
    }
}
