/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetvrti;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ObradaZahteva implements MessageListener {
	
    private Centrala centrala;
	
	public ObradaZahteva(Centrala c) {
		centrala = c;
	}

	@Override
	public void onMessage(Message message) {
		try {
			centrala.getListaZahteva().add(message.getBody(String.class));
			centrala.CheckGame();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}