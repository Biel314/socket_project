package exerc1;

import java.io.*;
import java.net.*;

public class Servidor {

    private static final int PORTA = 9001;

    public static void abrirServidor() throws IOException {
        ServerSocket servidor = new ServerSocket(PORTA);
        System.out.println("Porta " + PORTA + " aberta.");

        Socket cliente = servidor.accept();
        System.out.println("1 cliente se conectou.");

        BufferedReader ler = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter esc = new PrintWriter(cliente.getOutputStream(), true);

        try {
            String recebida = ler.readLine();
            System.out.println("Mensagem do cliente: " + recebida);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        esc.println("Servidor recebeu a mensagem com sucesso!");

        cliente.close();
        servidor.close();
    }

    public static void main(String[] args) {
        try {
            abrirServidor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
