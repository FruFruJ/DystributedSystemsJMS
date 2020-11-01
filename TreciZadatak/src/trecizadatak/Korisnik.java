import trecizadatak.KorisnikDodatProizvod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Random;
import javax.jms.*;
import javax.naming.*;

public class Korisnik {
    
    static Context ictx = null;
    static int pomid=0;
    
    public int stanje;
    public int id;
    
    public Korisnik()
    {
        this.stanje = new Random().nextInt(10000000)+5;
        this.id=pomid++;
    }
    
     public static void main(String[] args) throws NamingException, JMSException, IOException {
        // TODO code application logic here
        
        Korisnik platisa = new Korisnik();
        
        ictx = new InitialContext();
        
        Queue qklijentRacune = (Queue) ictx.lookup("jms/dodajProizvodQ");
      
        QueueConnectionFactory QCF = (QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");
        Topic qtDodato = (Topic) ictx.lookup("jms/dodatProizvod");      
        TopicConnectionFactory TCF = (TopicConnectionFactory) ictx.lookup("TFaktori");
       ictx.close();
         TopicConnection TC = TCF.createTopicConnection(); 
         TopicSession TS = TC.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        TopicSubscriber ts = TS.createSubscriber(qtDodato);
        ts.setMessageListener(new KorisnikDodatProizvod());
        
        QueueConnection QC = QCF.createQueueConnection(); 
        QueueSession QS = QC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        QueueSender qdodajP = QS.createSender(qklijentRacune);    
        
        
        QC.start();
        TC.start();
        
        System.out.println("Unesite broj jedne opcije: \n 1) Plati troskove \n 2)DodajRacun \n 3) Prikazi listing placanja \n");
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int opcija;
        String uneto =  bufferedReader.readLine();
        if(uneto.equals(""))
            opcija = 0;         //za slucaj da se pritisne enter
        else
            opcija = Integer.valueOf(uneto);

            TextMessage opcija2 = QS.createTextMessage();
            TextMessage opcija3 = QS.createTextMessage();
        
        while(opcija!=4)
        {

            switch (opcija) {
                
            case 1:  
            {
                System.out.println("unesite ime");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String imeProizvoda=br.readLine();
                System.out.println("unesite kolicinu");
                int kolicina=parseInt(br.readLine());
                Message pom=QS.createMessage();
                pom.setIntProperty("kolicina", kolicina);
                pom.setStringProperty("imeProizvoda", imeProizvoda);
                qdodajP.send(pom);
                
                
            }
                  break;   
                     
            case 2:  
            {
          
                
            }  
                break;
            case 3:  
            {
              
            }
                  break;   
                     
            case 4:  
            {
                System.out.println("Zavrsavam sa proizvodnjom...\n");
                QC.close();
                TC.close();
                
                
            }
                     break;
                     
            default: 
                     break;
                     
            }
              System.out.println("\nUnesite broj jedne opcije: \n 1) Plati troskove \n 2)DodajRacun \n 3) Prikazi listing placanja \n");
             uneto =  bufferedReader.readLine();
            if(uneto.equals(""))
                opcija = 0;     //za slucaj da se pritisne enter
            else
                opcija = Integer.valueOf(uneto);

            
        }
        
        
    }
    
}
