package Racuni;


import Racuni.PlatisaPrikaziRacune;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Random;
import javax.jms.*;
import javax.naming.*;

public class Platisa {
    
    static Context ictx = null;
    static int pomid=0;
    
    public int stanje;
    public int id;
    
    public Platisa()
    {
        this.stanje = new Random().nextInt(10000000)+5;
        this.id=pomid++;
    }
    
     public static void main(String[] args) throws NamingException, JMSException, IOException {
        // TODO code application logic here
        
        Platisa platisa = new Platisa();
        
        ictx = new InitialContext();
        
        Queue qklijentRacune = (Queue) ictx.lookup("jms/klijentRacune");
        Queue qvratiSveRacune= (Queue) ictx.lookup("jms/vratiRacunePlacanje");
        Queue qplatiTroskove = (Queue) ictx.lookup("jms/platiTroskove");
        Queue qDodajRacun=(Queue) ictx.lookup("jms/dodajRacun");
        Queue qprikaziListing=(Queue) ictx.lookup("jms/prikaziListing");
        QueueConnectionFactory QCF = (QueueConnectionFactory) ictx.lookup("jms/QueueConnectionFactory");
        Queue qRpaL = (Queue) ictx.lookup("jms/RacuniPaListing");   
        
        ictx.close();
        
        QueueConnection QC = QCF.createQueueConnection(); 
        QueueSession QS = QC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
        QueueSender qsendVratiRacune = QS.createSender(qklijentRacune);        
        QueueSender qsenddodajRacun = QS.createSender(qDodajRacun);           
        QueueSender qsendprikaziListing = QS.createSender(qprikaziListing);    
        
        
        QC.start();
        
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
                Message pom=QS.createMessage();
                qsendVratiRacune.send(pom);
                
                QueueReceiver qrecvSviRacuni=QS.createReceiver(qvratiSveRacune);
                qrecvSviRacuni.setMessageListener(new PlatisaPrikaziRacune(platisa));
            }
                  break;   
                     
            case 2:  
            {
          
                 System.out.println("Dodajemo racun \n ");
            
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("unesite sumu novca\n");
                        int stanje =  parseInt(br.readLine());
                        String razlog=br.readLine();
                        String id=br.readLine();
                                System.out.println(id);
                        Racun rac=new Racun(stanje,razlog,id);
                        ObjectMessage objm=QS.createObjectMessage(rac);
                        qsenddodajRacun.send(objm);
            }  
                break;
            case 3:  
            {
               Message pom=QS.createMessage();
               qsendprikaziListing.send(pom);
               
               QueueReceiver qrecvSviRacuni=QS.createReceiver(qRpaL);
                qrecvSviRacuni.setMessageListener(new PlatisaPrikaziRacunePaListing());
            }
                  break;   
                     
            case 4:  
            {
                System.out.println("Zavrsavam sa proizvodnjom...\n");
                QC.close();
                
                
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

