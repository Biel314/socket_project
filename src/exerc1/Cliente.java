package exerc1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PORTA = 9001;

    public static void enviarRequisicao() throws IOException {
        Socket conexao = new Socket(HOST, PORTA);

        PrintWriter esc = new PrintWriter(conexao.getOutputStream(), true);

        BufferedReader lei = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Você está conectado ao servidor!");
            System.out.println("Digite o que sera enviado para o server na porta " + PORTA + ": ");
            String mensagem = in.nextLine();
            esc.println(mensagem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String retorno = lei.readLine();
        System.out.println("Resposta do server: " + retorno);

        conexao.close();
        in.close();
    }

    public static void main(String[] args) {
        try {
            enviarRequisicao();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}