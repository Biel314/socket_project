package exerc2;

import java.io.*;
import java.net.*;

public class Servidor {

    private static final int PORTA = 9002;

    public static void abrirServidor() throws IOException {
        ServerSocket servidor = new ServerSocket(PORTA);
        System.out.println("Porta " + PORTA + " liberada. Aguardando conexoes...");

        int num = 0;

        while (!servidor.isClosed()) {
            try {
                Socket cliente = servidor.accept();
                System.out.println("Cliente " + num + " se conectou!");

                ThreadServer tarefa = new ThreadServer(cliente, servidor, num);

                new Thread(tarefa).start();

                num++;
            } catch (SocketException ex) {
                System.out.println("Porta " + PORTA + " encerrada...");
                break;
            }
        }

        servidor.close();
    }

    public static void main(String[] args) {
        try {
            abrirServidor();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}