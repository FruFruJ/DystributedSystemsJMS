/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.io.Serializable;

/**
 *
 * @author Joks
 */
public class Card implements Serializable {
    public String color;
    public int value;
    public Card(String col,int val)
    {
        this.color=col;
        this.value=val;
    }
}
