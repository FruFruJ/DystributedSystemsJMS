/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetvrti;
import java.io.Serializable;

public class Karta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String znak;
	private int broj;
	
	public Karta(String z, int b) {
		znak = z;
		broj = b;
	}
	
	public String getZnak() {
		return znak;
	}

	public void setZnak(String znak) {
		this.znak = znak;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}
	
	@Override
	public String toString() {
		return Integer.toString(broj) + znak.charAt(0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Karta.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
		final Karta k = (Karta) obj;
		return (this.broj == k.broj && this.znak.equals(k.znak));
	}
}