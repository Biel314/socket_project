package exerc3;

import java.io.*;
import java.net.*;

public class Servidor {

    private static final int PORTA = 9003;
    private static final int TAMANHO_BUFFER = 4096;

    public static void abrirServidor() throws IOException {
        ServerSocket servidor = new ServerSocket(PORTA);
        System.out.println("Porta " + PORTA + " liberada. Aguardando conexoes...");

        Socket cliente = servidor.accept();
        System.out.println("Cliente se conectou!");

        DataInputStream entrada = new DataInputStream(cliente.getInputStream());

        FileOutputStream gravador = new FileOutputStream("girassol.jpg");

        System.out.println("Nome do arquivo que sera recebido: " + entrada.readUTF());
        long tamanhoArquivo = entrada.readLong();

        byte[] bloco = new byte[TAMANHO_BUFFER];

        long acumulado = 0;

        while (acumulado < tamanhoArquivo) {
            int lidos = entrada.read(bloco);

            if (lidos == -1) {
                break;
            }

            gravador.write(bloco, 0, lidos);

            acumulado += lidos;
        }

        PrintWriter esc = new PrintWriter(cliente.getOutputStream(), true);

        esc.println("Servidor recebeu a imagem com sucesso!");

        gravador.close();
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