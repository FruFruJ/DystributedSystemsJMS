/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nadgledanjeautobusa;

import antlr.StringUtils;
import javax.naming.Context;
import javax.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import  javax.jms.*;
import javax.naming.*;
/**
 *
 * @author Joks
 */
public class Klijent implements MessageListener {

    /**
     * @param args the command line arguments
     */
    public static Context ictx;
    public static ArrayList<String> staniceAutobusa;
    public static TopicConnectionFactory TCF;
    public static Topic topicStigao;
    public static Topic topicPokvario;
    public static TopicConnection TC;
    public TopicSubscriber tc;
    public static TopicSession TS;
    public String stanica;
   
    
    public Klijent() throws NamingException, JMSException, IOException{
        
        staniceAutobusa=new ArrayList<String>();
        if(ictx==null)
        {
        ictx=new InitialContext();
        TCF=(TopicConnectionFactory)ictx.lookup("TFaktori");
        topicStigao=(Topic)ictx.lookup("Topic");
        topicPokvario=(Topic)ictx.lookup("jms/Obavestavam");
        ictx.close();
        
        TC=TCF.createTopicConnection();
        TS=TC.createTopicSession(false,Session.AUTO_ACKNOWLEDGE );
        }
       
        
        
        
        System.out.println("Dobrodosli \n Unesite ime vase stanice");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        stanica="la";
        
        TopicSubscriber consumerStigao=TS.createSubscriber(topicStigao,"'"+stanica+"'" +" IN ('la','ba') ",true);
        consumerStigao.setMessageListener((MessageListener)this);
        TopicSubscriber subPokvarioSe=TS.createSubscriber(topicPokvario);
        subPokvarioSe.setMessageListener(new SviCujte());
        TC.start();
        System.in.read();
        TC.close();
    }
    
    public static void stigao(String autobus,String[] stanice) throws JMSException, NamingException
    {
         ictx=new InitialContext();
        TCF=(TopicConnectionFactory)ictx.lookup("TFaktori");
        topicStigao=(Topic)ictx.lookup("Topic");
        topicPokvario=(Topic)ictx.lookup("jms/Obavestavam");
           ictx.close();
        
        TC=TCF.createTopicConnection();
        TS=TC.createTopicSession(false,Session.AUTO_ACKNOWLEDGE );
       TopicPublisher tpStigao=(TopicPublisher)TS.createPublisher(topicStigao);
       TextMessage objM=TS.createTextMessage("Stigao autobus na stanicu "+stanice[0]);
       objM.setStringProperty("stanice", "( " +String.join( ",",stanice)+" )");
       tpStigao.publish(objM);
    }
    
    public static void UKvaru(String autobus) throws JMSException, NamingException
    {
            ictx=new InitialContext();
        TCF=(TopicConnectionFactory)ictx.lookup("TFaktori");
        topicStigao=(Topic)ictx.lookup("Topic");
        topicPokvario=(Topic)ictx.lookup("jms/Obavestavam");
           ictx.close();
        
        TC=TCF.createTopicConnection();
        TS=TC.createTopicSession(false,Session.AUTO_ACKNOWLEDGE );
       TopicPublisher tpUKvaru=(TopicPublisher)TS.createPublisher(topicPokvario);
       TextMessage objM=TS.createTextMessage("Pokvario se bus "+autobus);
       tpUKvaru.publish(objM);
    }
    public static void main(String[] args) throws NamingException, JMSException, IOException {
       new Klijent();
    }

    @Override
    public void onMessage(Message message) {
        
        try {
            System.out.println(message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
