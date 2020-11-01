/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetvrti;
import  cetvrti.Igrac;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class GetStanjePartije implements MessageListener {

	@Override
	public void onMessage(Message message) {
		Partija p = null;
		try {
			p = message.getBody(Partija.class);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		if (p.isAktivna()) {
			System.out.println("~~~ ~~~ ~~~");
			System.out.println("Igrac 1: " + p.getIgrac1() + ", Broj poena: " + p.getSkor1());
			System.out.println("Igrac 2: " + p.getIgrac2() + ", Broj poena: " + p.getSkor2());
			if (p.isTurn()) {
				System.out.println("Na potezu je: " + p.getIgrac1());
			}
			else {
				System.out.println("Na potezu je: " + p.getIgrac2());
			}
			System.out.println("Talon:");
			if (p.getTalon().size() > 0) {
				System.out.print(p.getTalon().get(0));
				for (int i=1; i<p.getTalon().size(); i++) {
					System.out.print(" " + p.getTalon().get(i));
				}
				System.out.println();
			}
			if (p.getIgrac1().equals(Igrac.getUsername())) {
				Igrac.potvrdaStanja(p.isTurn());
			}
			if (p.getIgrac2().equals(Igrac.getUsername())) {
				Igrac.potvrdaStanja(!p.isTurn());
			}
		}
		else {
			System.out.println("~~~ ~~~ ~~~");
			System.out.println("Igrac 1: " + p.getIgrac1() + ", Broj poena: " + p.getSkor1());
			System.out.println("Igrac 2: " + p.getIgrac2() + ", Broj poena: " + p.getSkor2());
			System.out.println("Talon:");
			if (p.getTalon().size() > 0) {
				System.out.print(p.getTalon().get(0));
				for (int i=1; i<p.getTalon().size(); i++) {
					System.out.print(" " + p.getTalon().get(i));
				}
				System.out.println();
			}
			System.out.println("~~~ ~~~ ~~~");
			Igrac.setKarte(null);
			Igrac.setStatus(false);
		}
	}
}
