/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetvrti;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Igrac {

    private static Queue zahtev;
    private static Queue potez;
    private static Queue potvrdaStanja;
	private static Topic stanjePartije;
	private static Topic stanjePoteza;
	
    /*private static QueueConnectionFactory qcf;
    private static QueueConnection qc;
    private static QueueSession qs;*/
    
    private static TopicConnectionFactory tcf;
    private static TopicConnection tc;
    private static TopicSession ts;
    
    private static JMSProducer jmsProducer;
    private static JMSContext jmsContext;
    
    private static String username;
    private static boolean status;
	private static List<Karta> karte;
  
	public static void main(String[] args) throws NamingException, JMSException, IOException {
		status = false;
		karte = null;
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		username = bufferedReader.readLine();
		
		Context initialContext = new InitialContext();
		jmsContext = ((ConnectionFactory) initialContext.lookup("java:comp/DefaultJMSConnectionFactory")).createContext();
		zahtev = (Queue) initialContext.lookup("Zahtev");
		potez = (Queue) initialContext.lookup("Potez");
		potvrdaStanja = (Queue) initialContext.lookup("PotvrdaStanja");
		stanjePartije = (Topic) initialContext.lookup("StanjePartije");
		stanjePoteza = (Topic) initialContext.lookup("StanjePoteza");
		
		jmsProducer = jmsContext.createProducer();
		
		tcf = (TopicConnectionFactory) initialContext.lookup("TFaktori");
		tc = tcf.createTopicConnection();
		ts = tc.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
		
		ts.createSubscriber(stanjePartije).setMessageListener(new GetStanjePartije());	
		jmsContext.createConsumer(stanjePoteza, "Username = '" + username + "'").setMessageListener(new GetStanjePoteza());
		tc.start();
			
		while (true) {
			String komanda = bufferedReader.readLine();
			if (karte == null) {
				if (komanda.equalsIgnoreCase("exit")) {
					break;
				}
				if (komanda.equalsIgnoreCase("play")) {
					jmsProducer.send(zahtev, username);
				}
			}
			else if (status) {
				List<Karta> lista = parsiranjeKomande(komanda);
				if (lista != null) {
					Message message = jmsContext.createObjectMessage(new ListaKarti(lista));
					message.setBooleanProperty("Uzimanje", komanda.charAt(0) == '1');
					message.setStringProperty("Igrac", username);
					jmsProducer.send(potez, message);
				}
			}
		}
		bufferedReader.close();
		tc.close();
	}

	
	public static boolean isStatus() {
		return status;
	}

	public static void setStatus(boolean status) {
		Igrac.status = status;
	}
	
	public static List<Karta> getKarte() {
		return karte;
	}

	public static void setKarte(List<Karta> karte) {
		Igrac.karte = karte;
	}

	public static List<Karta> parsiranjeKomande(String komanda) {
		String[] komandice = komanda.split("\\s+");
		
		if (!komandice[0].equals("1") && !komandice[0].equals("2")) {
			return null;
		}
		
		List<Karta> karte = new ArrayList<Karta>();
		for(int i=1; i<komandice.length; i++) {
			String k = komandice[i];
			String br = k.substring(0, k.length()-1);
			String zn = k.substring(k.length()-1, k.length());
			
			String znak = null;
			switch(zn) {
			case "T":
				znak = "Tref";
				break;
			case "H":
				znak = "Herc";
				break;
			case "P":
				znak = "Pik";
				break;
			case "K":
				znak = "Karo";
				break;
			}
			if (znak == null) {
				return null;
			}
			
			int broj;
			try {
				broj = Integer.parseInt(br);
			} catch (NumberFormatException nfe) {
				broj = 0;
			}
			if (broj < 1 || broj > 14 || broj==11) {
				return null;
			}
			
			karte.add(new Karta(znak, broj));
		}
		if (komandice[0].equals("2") && karte.size() != 1) {
			return null;
		}
		return karte;
	}
	
	public static void potvrdaStanja(boolean turn) {
		Message message = jmsContext.createTextMessage(username);
		try {
			message.setBooleanProperty("Turn", turn);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		jmsProducer.send(potvrdaStanja, message);
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Igrac.username = username;
	}
}