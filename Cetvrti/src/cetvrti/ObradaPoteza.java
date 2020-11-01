/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetvrti;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ObradaPoteza implements MessageListener {

	private Centrala centrala;
	
	public ObradaPoteza(Centrala c) {
		centrala = c;
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			ListaKarti lk = message.getBody(ListaKarti.class);
			List<Karta> onajStoIgra = null;
			if (message.getStringProperty("Igrac").equals(centrala.getPartija().getIgrac1())) {
				onajStoIgra = centrala.getPrviIgrac();
			}
			else if (message.getStringProperty("Igrac").equals(centrala.getPartija().getIgrac2())) {
				onajStoIgra = centrala.getDrugiIgrac();
			}
			if (onajStoIgra == null) {
				return;
			}
			
			if (!message.getBooleanProperty("Uzimanje")) {
				if (onajStoIgra.contains(lk.getLista().get(0))) {
					onajStoIgra.remove(lk.getLista().get(0));
					centrala.getPartija().getTalon().add(lk.getLista().get(0));
					if (message.getStringProperty("Igrac").equals(centrala.getPartija().getIgrac1())) {
						centrala.getPartija().setTurn(false);
						if (centrala.getPrviIgrac().size() == 0 && centrala.getDrugiIgrac().size() == 0) {
							centrala.getPartija().setAktivna(false);
						}
						centrala.ArrangeGame();
					}
					else {
						centrala.getPartija().setTurn(true);
						if (centrala.getPrviIgrac().size() == 0 && centrala.getDrugiIgrac().size() == 0) {
							centrala.getPartija().setAktivna(false);
						}
						centrala.ArrangeGame();
					}
				}
			}
			else {
				int bodovi = 0;
				
				List<Karta> talonKopija = new ArrayList<Karta>();
				for (Karta k : centrala.getPartija().getTalon()) {
					talonKopija.add(k);
				}
				boolean okej = true;
				for (int i=1; i<lk.getLista().size(); i++) {
					if (talonKopija.contains(lk.getLista().get(i))) {
						talonKopija.remove(lk.getLista().get(i));
					}
					else {
						okej = false;
						break;
					}
				}
				if (!okej) {
					return;
				}
				if (!onajStoIgra.contains(lk.getLista().get(0))) {
					return;
				}
				
				int sum = 0;
				boolean kecuga = false;
				
				for (int i=1; i<lk.getLista().size(); i++) {
					sum += lk.getLista().get(i).getBroj();
					if (lk.getLista().get(i).getBroj() == 1) {
						kecuga = true;
					}
				}
				
				int sumche = lk.getLista().get(0).getBroj();
				okej = false;
				if (sum == sumche) {
					okej = true;
				}
				if ((sum+10==sumche) && kecuga) {
					okej = true;
				}
				if (sumche == 1 && 11 == sum) {
					okej = true;
				}
				
				if (!okej) {
					return;
				}
				
				for (int i=0; i<lk.getLista().size(); i++) {
					int broj = lk.getLista().get(i).getBroj();
					String znak = lk.getLista().get(i).getZnak();
					if (broj >= 10 || broj == 1) {
						bodovi++;
					}
					if (broj == 2 && znak.equals("Tref")) {
						bodovi++;
					}
					if (broj == 10 && znak.equals("Herc")) {
						bodovi++;
					}
				}
				
				centrala.getPartija().setTalon(talonKopija);
				onajStoIgra.remove(lk.getLista().get(0));

				if (message.getStringProperty("Igrac").equals(centrala.getPartija().getIgrac1())) {
					centrala.getPartija().setSkor1(centrala.getPartija().getSkor1()+bodovi);
					centrala.getPartija().setTurn(false);
					if (centrala.getPrviIgrac().size() == 0 && centrala.getDrugiIgrac().size() == 0) {
						centrala.getPartija().setAktivna(false);
					}
					centrala.ArrangeGame();
				}
				else {
					centrala.getPartija().setSkor2(centrala.getPartija().getSkor2()+bodovi);
					centrala.getPartija().setTurn(true);
					if (centrala.getPrviIgrac().size() == 0 && centrala.getDrugiIgrac().size() == 0) {
						centrala.getPartija().setAktivna(false);
					}
					centrala.ArrangeGame();
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

