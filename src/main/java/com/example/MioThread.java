package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    Socket s;
    MioThread(Socket s, int numero){
        this.s = s;
    
    try {

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());          

            String stringaRicevuta;

            String risposta = "";


            int numTentativi = 0;


            do {

                numTentativi = numTentativi + 1;

                stringaRicevuta = in.readLine();

                if(Integer.parseInt(stringaRicevuta) < numero && Integer.parseInt(stringaRicevuta) >= 0){
                    risposta = "<";
                    out.writeBytes(risposta + '\n');
                }

                if(Integer.parseInt(stringaRicevuta) > numero && Integer.parseInt(stringaRicevuta) <= 100){
                    risposta = ">";
                    out.writeBytes(risposta + '\n');
                }

                if(Integer.parseInt(stringaRicevuta) == numero){
                    risposta = "=";
                    out.writeBytes(risposta + '\n');
                    out.write(numTentativi);
                    break;
                }
                
                if(Integer.parseInt(stringaRicevuta) < 0 || Integer.parseInt(stringaRicevuta) > 100){
                    risposta = "!";
                    out.writeBytes(risposta + '\n');
                }

                
            } while (!risposta.equals("="));

            this.s.close();
            out.close();
            in.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
             
    }
}