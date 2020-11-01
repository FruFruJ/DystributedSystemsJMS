/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetvrti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Partija implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean aktivna;
	private String igrac1, igrac2;
	private int skor1, skor2;
	private boolean turn;
	private List<Karta> talon;

	public Partija() {
		aktivna = false;
		igrac1 = "";
		igrac2 = "";
		skor1 = 0;
		skor2 = 0;
		turn = true;
		talon = new ArrayList<Karta>();
	}

	public String getIgrac1() {
		return igrac1;
	}

	public void setIgrac1(String igrac1) {
		this.igrac1 = igrac1;
	}

	public String getIgrac2() {
		return igrac2;
	}

	public void setIgrac2(String igrac2) {
		this.igrac2 = igrac2;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean isAktivna() {
		return aktivna;
	}

	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}
	
	public List<Karta> getTalon() {
		return talon;
	}

	public void setTalon(List<Karta> talon) {
		this.talon = talon;
	}
	
	public int getSkor1() {
		return skor1;
	}

	public void setSkor1(int skor1) {
		this.skor1 = skor1;
	}

	public int getSkor2() {
		return skor2;
	}

	public void setSkor2(int skor2) {
		this.skor2 = skor2;
	}
}