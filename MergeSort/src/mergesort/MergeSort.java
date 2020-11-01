/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;
import java.io.*;
import javax.jms.*;
import javax.*;
import java.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;
import mergesort.ArrayHalf;
import static mergesort.SortMsgListener.count;

/**
 *
 * @author Joks
 */
public class MergeSort implements MessageListener {

    /**
     * @param args the command line arguments
     */
    static Context ictx=null;
    public static Queue nesortirano;
    public static Queue sortirano;
    public static QueueConnectionFactory QCF;
    public static QueueConnection QC;
    public static QueueSession QS;
    public static int count=0;
    public static int countSort=0;
    public  int sortId=0;
    public int countSent=0;
    public ArrayList<Integer> poslatiNizovi;
    public static Hashtable<Integer,ArrayList<Integer>> pracenje;
    public int id;
  //  public List<Integer> prethodni;
    public List<Integer> niz;
    public static QueueSender QSendNesortirano;
    public BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public MergeSort() throws NamingException, JMSException, IOException
    {
        
        //prethodni=new ArrayList<Integer>();
        pracenje=new Hashtable<Integer,ArrayList<Integer>>();
        niz=new ArrayList<Integer>();
        poslatiNizovi=new ArrayList<Integer>();
         MergeSort.count++;
        id=MergeSort.count;
        System.out.println(id);
        ictx=new InitialContext();    //throws NamingException
        QCF=(QueueConnectionFactory)ictx.lookup("jms/QueueConnectionFactory");
        nesortirano=(Queue)ictx.lookup("Zahtev");
        sortirano=(Queue)ictx.lookup("Queue");
        ictx.close();
        
        QC=QCF.createQueueConnection();  //throws jms Exception
        QS=QC.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
        
        QSendNesortirano=QS.createSender(nesortirano);
        
        QueueReceiver QRecNesortirano=QS.createReceiver(nesortirano);
        QRecNesortirano.setMessageListener((MessageListener)this);
        
        QueueReceiver QRecSortirano=QS.createReceiver(sortirano,"id="+id);
        //QRecSortirano.setMessageListener(new SortMsgListener(id));
        
        System.out.println("Unosite niz da ne?");
        String daNe=br.readLine();
        if(daNe.equals("da"))
        {
            System.out.println("Koliko elemenata unosite ?");
            int broj=Integer.parseInt(br.readLine());
            for(int i=0;i<broj;i++)
            {
                niz.add(Integer.parseInt(br.readLine()));
            }
            
            List<Integer> prvaPolovina=new ArrayList<Integer>();
            List<Integer> drugaPolovina=new ArrayList<Integer>();
            int parnoNeparno=niz.size()%2;
            if(parnoNeparno==0)
            {
                for(int i=0;i<niz.size()/2;i++)
                {
                    prvaPolovina.add(niz.get(i));
                    drugaPolovina.add(niz.get(i+niz.size()/2));
                }
            }
            else{
             for(int i=0;i<=niz.size()/2;i++)
                {
                    prvaPolovina.add(niz.get(i));
                }
              for(int i=niz.size()/2+1;i<niz.size();i++)
                {
                    drugaPolovina.add(niz.get(i));
                }
            }
            List<Integer> prethodni=new ArrayList<Integer>();
            prethodni.add(id);
            
            List<Integer> prethodniSortId=new ArrayList<Integer>();
            prethodniSortId.add(countSort);
          
            System.out.println("Moj id je "+id+" broj ovog sorta je "+sortId);
           
            ArrayHalf prvaP=new ArrayHalf(prvaPolovina,prethodni,prethodniSortId);
            ObjectMessage om1=QS.createObjectMessage(prvaP);
            ArrayHalf drugaP=new ArrayHalf(drugaPolovina,prethodni,prethodniSortId);
            ObjectMessage om2=QS.createObjectMessage(drugaP);
   
            
            
            
            QSendNesortirano.send(om1);
            QSendNesortirano.send(om2);
            System.out.println("lala");
            
        }
        QC.start();
        System.in.read();
        QC.close();
       
        
        
        
        
        
    }
    
    public static void main(String[] args) throws NamingException, JMSException, IOException {
       
        new MergeSort();
      
    }

    @Override
    public void onMessage(Message message) {
        
        id=MergeSort.count;
        try {
                    
          //   System.out.println("Id procesa je "+id+" id deljenja odnosno count je "+sortId);
            ArrayHalf polovinaNiza=message.getBody(ArrayHalf.class);
            ArrayList<Integer> polovina=(ArrayList<Integer>) polovinaNiza.niz;
            ArrayList<Integer> prethodni=(ArrayList<Integer>) polovinaNiza.prethodni;
            ArrayList<Integer> prethodniSortId=(ArrayList<Integer>) polovinaNiza.prethodniSortId;
            System.out.println(polovina.toString());
            System.out.println(prethodni.toString());
            System.out.println(prethodniSortId.toString());
            if(polovina.size()==1)
            {
                System.out.println("kraj");
                int Prethodni=0;
                if(prethodni.size()-1>0)
                {
                Prethodni=prethodni.get(prethodni.size()-1);
                prethodni.remove(prethodni.size()-1);
                }
                QueueSender QSendSortirano=QS.createSender(sortirano);
                
                TextMessage tm=QS.createTextMessage();
                tm.setIntProperty("id", Prethodni);
                 }
            else
            {
            sortId=countSort++;
            System.out.println("Moj id je "+id+" broj ovog sorta je "+sortId);
            
            prethodni.add(id);
            prethodniSortId.add(sortId);
            List<Integer> prvaPolovina=new ArrayList<Integer>();
            List<Integer> drugaPolovina=new ArrayList<Integer>();
            int parnoNeparno=polovina.size()%2;
            if(parnoNeparno==0)
            {
                for(int i=0;i<polovina.size()/2;i++)
                {
                    prvaPolovina.add(polovina.get(i));
                    drugaPolovina.add(polovina.get(i+polovina.size()/2));
                }
            }
            else{
             for(int i=0;i<=polovina.size()/2;i++)
                {
                    prvaPolovina.add(polovina.get(i));
                }
              for(int i=polovina.size()/2+1;i<polovina.size();i++)
                {
                    drugaPolovina.add(polovina.get(i));
                }
            }
            
     ArrayHalf prvaP=new ArrayHalf(prvaPolovina,prethodni,prethodniSortId);
            ObjectMessage om1=QS.createObjectMessage(prvaP);
            ArrayHalf drugaP=new ArrayHalf(drugaPolovina,prethodni,prethodniSortId);
            ObjectMessage om2=QS.createObjectMessage(drugaP);
   
            
            
            
            QSendNesortirano.send(om1);
            QSendNesortirano.send(om2);
            count++;
            }
            
        } catch (JMSException ex) {
            Logger.getLogger(MergeSort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
