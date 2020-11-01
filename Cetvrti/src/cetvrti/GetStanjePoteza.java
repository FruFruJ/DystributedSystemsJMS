/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetvrti;
import cetvrti.ListaKarti;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class GetStanjePoteza implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("Vase karte:");
		ListaKarti lk = null;
		try {
			lk = message.getBody(ListaKarti.class);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		Igrac.setKarte(lk.getLista());
		if (lk.getLista().size() > 0) {
			System.out.print(lk.getLista().get(0));
			for (int i=1; i<lk.getLista().size(); i++) {
				System.out.print(" " + lk.getLista().get(i));
			}
			System.out.println();
		}
		System.out.println("~~~ ~~~ ~~~");
		
		try {
			Igrac.setStatus(message.getBooleanProperty("Potez"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
