/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Joks
 */
public class ArrayHalf implements Serializable {
    
    public List<Integer> niz;
    public List<Integer> prethodni;
     public List<Integer> prethodniSortId;
    
    public ArrayHalf(List<Integer> niz,List<Integer> prethodni,List<Integer> prethodniSortId)
    {
        this.niz=niz;
        this.prethodni=prethodni;
        this.prethodniSortId=prethodniSortId;
    }
}
