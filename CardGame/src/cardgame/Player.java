/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Joks
 */
public class Player implements Serializable {
    public int id;
    public static int counter=0;
    public String ime;
    public int brojPoena;
    public boolean reject;
    public int krug;
    public ArrayList<Integer> cards;
    
    public Player(String ime)
    {
        this.ime=ime;
        this.brojPoena=0;
        id=counter++;
        reject=false;
        cards=new ArrayList<Integer>();
        krug=0;
    }
    
}
