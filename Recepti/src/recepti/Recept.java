/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recepti;

import java.io.Serializable;

/**
 *
 * @author Joks
 */
public class Recept implements Serializable {
    public String imeRecepta;
    public String recept;
    public Recept(String imeRecepta,String recept)
    {
        this.imeRecepta=imeRecepta;
        this.recept=recept;
    }
}
