package com.example;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Server avviato");

        try (ServerSocket server = new ServerSocket(3000)) {
            do {
                Socket s = server.accept();
                Random random = new Random();
                int numero = random.nextInt(100);
                System.out.println("Client collegato");
                MioThread t = new MioThread(s, numero);
                t.start();
            } while (true);
        }


    }       
}