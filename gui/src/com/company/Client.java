package com.company;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        //InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;


        while (true) {
            socket = new Socket(/*host.getHostName()*/"127.0.0.1", 9876);
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.print("Podaj polecenie: ");
            Scanner sc = new Scanner(System.in);
            oos.writeObject(sc.next());
            oos.close();
            socket.close();
        }
    }
}