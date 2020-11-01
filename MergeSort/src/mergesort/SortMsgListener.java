/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.QueueSender;
import static mergesort.MergeSort.QS;
import static mergesort.MergeSort.QSendNesortirano;
import static mergesort.MergeSort.pracenje;
import static mergesort.MergeSort.sortirano;

/**
 *
 * @author Joks
 */
public class SortMsgListener implements MessageListener {

    public static int count=0;
    public int id;
    public SortMsgListener() {
       id=0;
    }

    @Override
    public void onMessage(Message message) {
         
    }
    
}
