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
public class ChatMessage implements Serializable {
    
    public User fromUser;
    public User toUser;
    public int hour;
    public int minute;
    public String msg;
    
    public ChatMessage(User fromUse,User toUser,int h,int min,String msg)
    {
        this.fromUser=fromUse;
        this.toUser=toUser;
        this.hour=h;
        this.minute=min;
        this.msg=msg;
    }
    
}
