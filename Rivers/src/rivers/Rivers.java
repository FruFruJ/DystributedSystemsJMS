/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivers;
import javax.*;
import java.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joks
 */
public class Rivers implements MessageListener {

   public static Context ictx;
   public int x;
   public int y;
   public double zagadjenje;
   
   public int poslednjeX;
   public int poslednjeY;
   public double poslednjeZagadjenje;
   
   private static TopicConnectionFactory TCF;
   private static QueueConnectionFactory QCF;
   private static Queue QPoslednje;
   private static Topic PozzSvimaIspod;
   private static TopicConnection TC;
   private static TopicSession TS;
   private static QueueSession QS;
   private static QueueConnection QC;
   
   private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   
   public Rivers() throws NamingException, IOException, JMSException
   {
       ictx=new InitialContext();
       TCF=(TopicConnectionFactory)ictx.lookup("TFaktori");
       QCF=(QueueConnectionFactory)ictx.lookup("jms/QueueConnectionFactory");
       
       QPoslednje=(Queue)ictx.lookup("jms/SlusamISaljem");
       PozzSvimaIspod=(Topic)ictx.lookup("jms/Obavestavam");
       ictx.close();
       
       System.out.println("Unesite dimenziju x");
       x=Integer.parseInt(br.readLine());
       
       System.out.println("Unesite dimenziju y");
       y=Integer.parseInt(br.readLine());
       
       System.out.println("Unesite zagadjenje");
       zagadjenje=Double.parseDouble(br.readLine());
       
       TC=TCF.createTopicConnection();
       TS=TC.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        if(x!=0&&y!=0)
           this.proslediNanize();
       
       QC=QCF.createQueueConnection();
       QS=QC.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
      
       if(x==0&&y==0)
       this.obavestiSledeceg();
       
       TopicSubscriber ts=TS.createSubscriber(PozzSvimaIspod,x+">x and "+y+">y",true);
       ts.setMessageListener(new ObavestenjeListener());
       
       QueueReceiver qr=QS.createReceiver(QPoslednje,x+">x and "+y+">y");
       qr.setMessageListener((MessageListener)this);
       
       QC.start();
       TC.start();
       System.in.read();
       QC.close();
       TC.close();
       
   }
  
    public static void main(String[] args) throws NamingException, IOException, JMSException {
      new Rivers();
    }

    private void proslediNanize() throws JMSException {
       TopicPublisher tp= TS.createPublisher(PozzSvimaIspod);
       
       TextMessage tm=TS.createTextMessage();
       tm.setIntProperty("x",x);
       tm.setIntProperty("y",y);
       tm.setDoubleProperty("z",zagadjenje);
       
       tp.publish(tm);
    }

    private void obavestiSledeceg() throws JMSException {
        QueueSender qs=QS.createSender(QPoslednje);
        
        TextMessage tm=QS.createTextMessage();
         tm.setIntProperty("x",x);
       tm.setIntProperty("y",y);
       tm.setDoubleProperty("z",zagadjenje);
       
       qs.send(tm);
    }

    @Override
    public void onMessage(Message m) {
        double novoZagadjenje;
       try {
           novoZagadjenje = m.getDoubleProperty("z");
            System.out.println("Primio sam");
       System.out.println("Njegove koordinate su:"+m.getIntProperty("x")+" njegova y koordinata je "+m.getIntProperty("y")+" njegovo zagadnjenje je "+novoZagadjenje);
       
      zagadjenje+=novoZagadjenje;
      this.obavestiSledeceg();
       } catch (JMSException ex) {
           Logger.getLogger(Rivers.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }
    
}
