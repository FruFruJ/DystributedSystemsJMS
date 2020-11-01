/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recepti;

import javax.jms.*;
import java.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;

/**
 *
 * @author Joks
 */
public class Klijent implements MessageListener {

    /**
     * @param args the command line arguments
     */
    public static String id;
    public static Context ictx;
    public static Topic caoSvimaTopic;
    public static Queue caoITebi;
    public static Queue trazimRecept;
    public static Queue vracamRecept;
    public static QueueConnectionFactory QCF;
    public static TopicConnectionFactory TCF;
    public static QueueSession QS;
    public static TopicSession TS;
    public static ArrayList<String> receptiIme;
    public static ArrayList<String> recepti;
    
    public Klijent() throws JMSException ,IOException,NamingException
    {
        ictx=new InitialContext();
        QCF=(QueueConnectionFactory)ictx.lookup("jms/QueueConnectionFactory");
        TCF=(TopicConnectionFactory)ictx.lookup("TFaktori");
        
        recepti=new ArrayList<String>();
        receptiIme=new ArrayList<String>();
        
        caoSvimaTopic=(Topic)ictx.lookup("Topic");
        caoITebi=(Queue)ictx.lookup("Queue");
        trazimRecept=(Queue)ictx.lookup("Zahtev");
        vracamRecept=(Queue)ictx.lookup("PotvrdaStanja");
        
        ictx.close();
        
        TopicConnection TC=TCF.createTopicConnection();
        TopicSession TS=TC.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        
        QueueConnection QC=QCF.createQueueConnection();
         QS=QC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        
        
        
        System.out.println("Unesite vas id");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        id=br.readLine();
        
        System.out.println("Unesite vas broj recepta");
        int broj=Integer.parseInt(br.readLine());
        for(int i=0;i<broj;i++)
        {
            receptiIme.add(br.readLine());
            recepti.add(br.readLine());
            
        }
        System.out.println("Da li trazite ?");
        String daLiTrazite=br.readLine();
        if(daLiTrazite.equals("da"))
        {
            System.out.println("Unesite recept");
            
            String recept=br.readLine();
            
            QueueSender qTrazimRecept=QS.createSender(trazimRecept);
            TextMessage tmess=QS.createTextMessage();
            tmess.setStringProperty("recept", recept);
            tmess.setStringProperty("id", id);
            qTrazimRecept.send(tmess);
        }
        
        
        QueueReceiver qCaoITebi=QS.createReceiver((Queue)caoITebi,"id='"+id+"'");
        qCaoITebi.setMessageListener(new PozdravITebi());
        
        QueueReceiver qDobijamRecept=QS.createReceiver((Queue)vracamRecept,"id='"+id+"'");
        qDobijamRecept.setMessageListener((MessageListener)this);
        
        
        TopicSubscriber tCaoSvima=TS.createSubscriber(caoSvimaTopic);
        tCaoSvima.setMessageListener(new IdentifikujSe(id));
        
        TopicPublisher tpCaoSvima=TS.createPublisher(caoSvimaTopic);
        TextMessage tm1=TS.createTextMessage("Cao od mene moj id je"+id);
        tm1.setStringProperty("id", id);
        
        tpCaoSvima.send(tm1);
        
        QueueReceiver qImamRecept=QS.createReceiver(trazimRecept,"recept in "+vratiNiz());
        qImamRecept.setMessageListener(new ImamReceptListener());
        
        QC.start();
        TC.start();
        System.in.read();
        TC.close();
        QC.close();
        
        
        
    }
    public  static String vratiNiz()
    {
        String receptiFilter="( ";
        for(int i=0;i<receptiIme.size()-1;i++)
        {
        receptiFilter+="'"+receptiIme.get(i)+"' ,";
        }
        if(receptiIme.size()>0)
        receptiFilter+="'"+receptiIme.get(receptiIme.size()-1)+"' )";
        return receptiFilter;
    }
    
    public static void main(String[] args) throws JMSException, IOException, NamingException {
       new Klijent();
       
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Dobio sam recept i on je "+message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
