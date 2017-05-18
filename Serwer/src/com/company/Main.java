/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.IOException;


/**
 *
 * @author Mateusz Markuszewski
 */

public class Main {

      public static void main(String[] args) throws Exception{
          //tworzenie wątku nasłuchującego na broadcast
          Runnable runner = new DatagramReceiver();
          Thread thread= new Thread(runner);
          thread.start();
          //serwer
          Serwer server = new Serwer();
          server.connection();

      }
}
