package exerc3;

import java.io.*;
import java.net.*;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PORTA = 9003;
    private static final int TAMANHO_BUFFER = 4096;

    public static void enviarRequisicao() throws IOException {
        Socket conexao = new Socket(HOST, PORTA);

        File arquivo = new File("img/girassolAesthetic.jpg");

        System.out.println("Arquivo existe? " + arquivo.exists());
        System.out.println("Caminho absoluto: " + arquivo.getAbsolutePath());

        DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

        FileInputStream leitorArquivo = new FileInputStream(arquivo);

        BufferedReader ler = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

        try {
            saida.writeUTF(arquivo.getName());
            saida.writeLong(arquivo.length());

            byte[] bloco = new byte[TAMANHO_BUFFER];

            int lidos;

            while ((lidos = leitorArquivo.read(bloco)) != -1) {
                saida.write(bloco, 0, lidos);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String retorno = ler.readLine();
        System.out.println("O servidor respondeu: " + retorno);

        saida.flush();
        leitorArquivo.close();
        conexao.close();
    }

    public static void main(String[] args) {
        try {
            enviarRequisicao();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}