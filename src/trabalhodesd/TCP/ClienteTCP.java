package trabalhodesd.TCP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Emanuel Cavalcante
 */
public class ClienteTCP {

    String nomeArquivo;

    public ClienteTCP(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void enviarArquivo() throws Exception {

        File file = new File("C:\\Users\\emanu\\Desktop\\Cliente/" + nomeArquivo);
        //transforma o arquivo em stream e armazena no in 
        FileInputStream in = new FileInputStream(file);

        //Cria um socket endereco local e na porta 9876
        Socket socket = new Socket("localhost", 9876);

        //Obtém os fluxos de entrada e saída do soquete
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out);

        BufferedWriter writer = new BufferedWriter(osw);

        //escreve na saída o nome do arquivo 
        writer.write(file.getName() + "\n");

        writer.flush();
        int c;
        //Converte o arquivo para escrevelo no ciclo de while
        while ((c = in.read()) != -1) {
            System.out.println(c);
            //escrevendo o arquivo dps de ter convertido
            out.write(c);
        }
        /*Fechando a conexao de todas as maneiras possiveis ehauheauhe*/
        out.flush();
        out.close();
        socket.close();
        in.close();
        writer.flush();
        writer.close();

    }
}
