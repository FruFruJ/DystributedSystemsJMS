
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joks
 */

public class Reservation implements Serializable {
    public int id;
    private int day;
    private int month;
    public int numHours;
    public int hour;
    public static int count=0;
    public  Reservation(int day,int month,int hour,int numHours){
    this.id=count++;
    this.hour=hour;
    this.day=day;
    this.month=month;
    this.numHours=numHours;
    }
}
