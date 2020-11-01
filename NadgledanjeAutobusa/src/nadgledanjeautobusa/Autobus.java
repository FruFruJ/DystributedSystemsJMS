/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nadgledanjeautobusa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author Joks
 */
public class Autobus {
    
    public String ime;
    public String[] stanice;
    
    public int i=0;
    public Autobus() throws IOException, JMSException, NamingException
    {
        System.out.println("Unesite ime autobusa");
        stanice=new String[100];
        stanice[0]="jovana";
        stanice[1]="kal";
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ime=br.readLine();
        String uneto="";
        while(uneto!="end")
        {
            uneto=br.readLine();
            if(uneto.equals("stanica"))
                stanice[i++]=br.readLine();
            else if(uneto.equals("stigao"))
                Klijent.stigao(ime,stanice);
            else
                Klijent.UKvaru(ime);
        }
        
    }
    public static void main(String[] args) throws IOException, JMSException, NamingException
    {
        new Autobus();
    }
}
