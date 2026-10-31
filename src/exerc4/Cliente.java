package exerc4;

import java.net.*;
import java.io.*;

public class Cliente {

    private static final int PORTA_HTTP = 80;

    public static void requisitarHTTP(String endereco) throws IOException {
        Socket conexao = new Socket(endereco, PORTA_HTTP);

        PrintWriter esc = new PrintWriter(conexao.getOutputStream(), true);

        BufferedReader ler = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

        esc.println("GET / HTTP/1.1");
        esc.println("Host: " + endereco);
        esc.println("Connection: close");
        esc.println();

        String texto;

        while ((texto = ler.readLine()) != null) {
            System.out.println(texto);
        }

        conexao.close();
    }

    public static void main(String[] args) {
        try {
            requisitarHTTP("www.aied.com.br");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}