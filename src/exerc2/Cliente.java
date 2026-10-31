package exerc2;

import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PORTA = 9002;

    public static void enviarRequisicao() throws IOException {
        Socket conexao = new Socket(HOST, PORTA);

        PrintWriter escritor = new PrintWriter(conexao.getOutputStream(), true);

        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("Informe a mensagem para enviar ao servidor (porta " + PORTA + "): ");
            String mensagem = teclado.nextLine();
            escritor.println(mensagem);
        } catch (NoSuchElementException ex) {
            System.out.println("Ctrl+C identificado. Finalizando a conexao...");
        }

        String retorno = leitor.readLine();
        System.out.println("O servidor respondeu: " + retorno);

        conexao.close();
        teclado.close();
    }

    public static void main(String[] args) {
        try {
            enviarRequisicao();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}