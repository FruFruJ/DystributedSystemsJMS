/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetvrti;
import java.io.Serializable;
import java.util.List;

public class ListaKarti implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Karta> lista;
	
	ListaKarti(List<Karta> l) {
		lista = l;
	}

	public List<Karta> getLista() {
		return lista;
	}

	public void setLista(List<Karta> lista) {
		this.lista = lista;
	}
}