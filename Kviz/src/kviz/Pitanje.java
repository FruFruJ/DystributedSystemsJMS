/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kviz;
import java.io.Serializable;

/**
 *
 * @author Joks
 */
public class Pitanje implements Serializable {
    
    private String pitanje;
    private String odgovor;
    private int id;
    private static int broj;
    public Pitanje(String pitanje,String odgovor){
    this.pitanje=pitanje;
    this.odgovor=odgovor;
    }
    public String getPitanje()
    {
        return this.pitanje;
    }
    public void setPitanje(String pitanje)
    {
         this.pitanje=pitanje;
    }
    public String getOdgovor()
    {
        return this.odgovor;
    }
    public void setOdgovor(String odgovor)
    {
        this.odgovor=odgovor;
    }
    public  int getId()
    {
        return this.id;
    }
    
    
}
