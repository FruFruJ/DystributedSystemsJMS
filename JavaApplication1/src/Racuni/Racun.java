package Racuni;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public  class Racun implements Serializable{
    static int pom=2;
    public int stanje;
    public String id;
    public List<String> isplate= new ArrayList<String>();
    public long vratiId(){
    long minimum=0;
    long maximum=999999999;
    Random rn = new Random();
    long range = maximum - minimum + 1;
    long randomNum =  rn.nextInt((int)range) + minimum;
    return randomNum;
    }
    public  Racun(int stanje,String razlog,String id)
    {
    this.id=id;
    this.stanje=stanje;
    this.isplate.add(razlog);
    }
    public boolean plati(int zaPlacanje,String ciljIsplate)
    {
       if(this.stanje-zaPlacanje<100)
           return false;
       else
       {
           this.stanje-=zaPlacanje;
           isplate.add(ciljIsplate);
           return true;
       }
       
    }
}

