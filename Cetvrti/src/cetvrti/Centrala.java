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
import java.util.Random;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Centrala implements MessageListener {

	private static Random random;
	
	private static JMSContext jmsContext;
	
    private static Queue zahtev;
    private static Queue potez;
    private static Queue potvrdaStanja;
    private static Topic stanjePartije;
    private static Topic stanjePoteza;

	private static TopicPublisher stanjePartijePublisher;
    private static TopicPublisher stanjePotezaPublisher;
    
	private static TopicConnectionFactory tcf;
    private static TopicConnection tc;
    private static TopicSession ts;
	
    private List<String> listaZahteva;
    private List<Karta> prviIgrac;
    private List<Karta> drugiIgrac;
	private Partija partija;
    
	public static void main(String[] args) throws NamingException, JMSException, IOException {
		new Centrala();
	}
	
    public List<String> getListaZahteva() {
		return listaZahteva;
	}

	public void setListaZahteva(List<String> listaZahteva) {
		this.listaZahteva = listaZahteva;
	}
	
	private Centrala() throws NamingException, JMSException, IOException {
		listaZahteva = new ArrayList<String>();
		ObradaZahteva obradaZahteva = new ObradaZahteva(this);
		ObradaPoteza obradaPoteza = new ObradaPoteza(this);
		partija = new Partija();
		random = new Random();
		prviIgrac = new ArrayList<Karta>();
		drugiIgrac = new ArrayList<Karta>();
		
		Context initialContext = new InitialContext();
		jmsContext = ((ConnectionFactory) initialContext.lookup("java:comp/DefaultJMSConnectionFactory")).createContext();
		
		zahtev = (Queue) initialContext.lookup("Zahtev");
		potez = (Queue) initialContext.lookup("Potez");
		potvrdaStanja = (Queue) initialContext.lookup("PotvrdaStanja");
		stanjePartije = (Topic) initialContext.lookup("StanjePartije");
		stanjePoteza = (Topic) initialContext.lookup("StanjePoteza");
		
		jmsContext.createConsumer(zahtev).setMessageListener(obradaZahteva);
		jmsContext.createConsumer(potez).setMessageListener(obradaPoteza);
		jmsContext.createConsumer(potvrdaStanja).setMessageListener(this);
		
		tcf = (TopicConnectionFactory) initialContext.lookup("TFaktori");
		tc = tcf.createTopicConnection();
		ts = tc.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
		stanjePartijePublisher = ts.createPublisher(stanjePartije);
		stanjePotezaPublisher = ts.createPublisher(stanjePoteza);
		tc.start();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		bufferedReader.readLine();
		bufferedReader.close();
		tc.close();
	}

	
	
	public void CheckGame() throws JMSException {
		if (partija.isAktivna()) {
			return;
		}
		if (listaZahteva.size() >= 2) {
			partija.setAktivna(true);
			String ig1 = listaZahteva.remove(0);
			String ig2 = listaZahteva.remove(0);
			partija.setIgrac1(ig1);
			partija.setIgrac2(ig2);
			partija.setTurn(true);
			
			int[] niz = new int[52];
			for (int i=0; i<52; i++) {
				niz[i] = i;
			}
			
			for (int i=0; i<16; i++) {
				int indeks = random.nextInt(52-i)+i;
				int temp = niz[indeks];
				niz[indeks]=niz[i];
				niz[i] = temp;
			}
			Karta[] karte = new Karta[16];
			for (int i=0; i<16; i++) {
				int broj = (niz[i] % 13) + 1;
				if (broj>10) {
					broj++;
				}
				String znak = "";
				switch (niz[i]/13) {
				case 0:
					znak = "Herc";
					break;
				case 1:
					znak = "Pik";
					break;
				case 2:
					znak = "Tref";
					break;
				case 3:
					znak = "Karo";
					break;
				}
				karte[i] = new Karta(znak, broj);
			}
			
			prviIgrac.clear();
			drugiIgrac.clear();
			partija.getTalon().clear();
			partija.setSkor1(0);
			partija.setSkor2(0);
			
			for (int i=0; i<6; i++) {
				prviIgrac.add(karte[i]);
			}
			for (int i=6; i<12; i++) {
				drugiIgrac.add(karte[i]);
			}
			for (int i=12; i<16; i++) {
				partija.getTalon().add(karte[i]);
			}		
			
			ArrangeGame();
		}
	}

	public Partija getPartija() {
		return partija;
	}

	public void setPartija(Partija partija) {
		this.partija = partija;
	}

	public List<Karta> getPrviIgrac() {
		return prviIgrac;
	}

	public void setPrviIgrac(List<Karta> prviIgrac) {
		this.prviIgrac = prviIgrac;
	}

	public List<Karta> getDrugiIgrac() {
		return drugiIgrac;
	}

	public void setDrugiIgrac(List<Karta> drugiIgrac) {
		this.drugiIgrac = drugiIgrac;
	}
	
	public void ArrangeGame() throws JMSException {
		Message message = jmsContext.createObjectMessage(partija);
		stanjePartijePublisher.publish(message);
		
		ts.commit();
	}

	@Override
	public void onMessage(Message message) {
		try {
			String user = message.getBody(String.class);
			boolean turn = message.getBooleanProperty("Turn");
			
			Message poruka = null;
			if (user.equals(partija.getIgrac1())) {
				poruka = jmsContext.createObjectMessage(new ListaKarti(prviIgrac));
			}
			else if (user.equals(partija.getIgrac2())) {
				poruka = jmsContext.createObjectMessage(new ListaKarti(drugiIgrac));
			}
			poruka.setStringProperty("Username", user);
			poruka.setBooleanProperty("Potez", turn);
			stanjePotezaPublisher.publish(poruka);
			
			ts.commit();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}		
	}
}
