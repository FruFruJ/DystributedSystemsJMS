/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;

import java.io.Serializable;

/**
 *
 * @author Joks
 */
public class User implements Serializable {
    
    public int id;
    public String userName;
    public static int counter=0;
    public User(String userName)
    {
        this.userName=userName;
        id=counter++;
    }
}
