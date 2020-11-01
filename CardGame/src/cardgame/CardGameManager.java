/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;
import java.rmi.*;
import java.util.*;
import java.io.*;
import java.*;
/**
 *
 * @author Joks
 */
public interface CardGameManager extends java.rmi.Remote {
    
    Card requestCard(Player player) throws java.rmi.RemoteException;
    
    void pass(Player player) throws java.rmi.RemoteException;
    
    void registerPlayer(Player player) throws java.rmi.RemoteException;
    
    void register(Callback c) throws java.rmi.RemoteException;
    
}
