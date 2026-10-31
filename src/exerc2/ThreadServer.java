package exerc2;

import java.io.*;
import java.net.*;

public class ThreadServer implements Runnable {

    private final Socket cliente;
    private final ServerSocket servidor;
    private final int num;

    public ThreadServer(Socket cliente, ServerSocket servidor, int num) {
        this.cliente = cliente;
        this.servidor = servidor;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            BufferedReader ler = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            PrintWriter esc = new PrintWriter(cliente.getOutputStream(), true);

            String texto;

            while ((texto = ler.readLine()) != null) {
                System.out.println("Recebido do Client-" + num + ":\t" + texto);

                if (texto.equals("Hello World!")) {
                    System.out.println("Mensagem Hello World! identificada");
                    esc.println("Mas que mensagem mais generica ein...");
                }

                if (texto.equals("teste")) {
                    System.out.println("Mensagem teste identificada");
                    esc.println("O que voce quer testar exatamente??");
                }

                if (texto.equals("SHUTDOWN")) {
                    servidor.close();
                    break;
                }

                esc.println("Server recebeu a mensagem: " + "\"" + texto + "\"");
            }

            System.out.println("Cliente " + this.num + " saiu da conexao.");

        } catch (IOException ex) {
            System.out.println("Cliente " + this.num + " saiu da conexao.");
        }
    }
}