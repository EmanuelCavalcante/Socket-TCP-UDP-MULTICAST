package trabalhodesd.TCP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Emanuel Cavalcante
 */
public class ServidorTCP extends Thread {

    JTextArea console;

    public ServidorTCP(JTextArea console) {
        this.console = console;
    }

    public void run() {
        try {
            //Cria um socket endereco local e na porta 9876
            ServerSocket server = new ServerSocket(9876);
            while (true) {

                console.append("Esperando... \n");

                //Esperando o cliente
                Socket clSocket = server.accept();

                //Lê o stream do cliente
                InputStream in = clSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(isr);

                //Recebe o nome do arquivo
                String fName = reader.readLine();
                System.out.println(fName);
                console.append("Recebeu arquivo: " + fName + "\n");

                //cria um arquivo no path e escreve o que recebe da stream do cliente
                File f1 = new File("C:\\Users\\emanu\\Desktop\\Servidor/" + fName);
                FileOutputStream out = new FileOutputStream(f1);

                int c;
                //Converte o arquivo para escrevelo no ciclo de while

                while ((c = in.read()) != -1) {
                    System.out.println(c);
                    //Escreve o arquivo
                    out.write(c);
                }
                //Fechando as conexoes de todas as maneiras possiveis 
                out.flush();
                out.close();
                in.close();
                isr.close();
            }

        } catch (IOException ex) {

            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
