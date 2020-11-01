/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trecizadatak;

/**
 *
 * @author Joks
 */
class Proizvod {
    public String imeProizvoda;
    public int kolicina;
    
    public Proizvod(String ime,int kol)
    {
    this.imeProizvoda=ime;
    kolicina=kol;
    }
    
    public void povecajKol()
    {
    kolicina++;
    }
}
