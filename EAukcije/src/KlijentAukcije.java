import java.io.Serializable;
public class KlijentAukcije implements java.io.Serializable{
    public String klijentAukcijeId;
    public String ime;
    public String prezime;
    public KlijentAukcije(String klijentAukcijeId,String ime,String prezime)
    {
    	this.klijentAukcijeId=klijentAukcijeId;
        this.ime=ime;
        this.prezime=prezime;
    }
}

