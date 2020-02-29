package extraclase_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    public static void main (String [] args){

        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;

        final int PORT=3000;

        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started");

            while (true){
                sc = server.accept();

                System.out.println("client conected");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                String message= in.readUTF();

                System.out.println(message);

                out.writeUTF("Soy el server");

                sc.close();

                System.out.println("Client desconected ");




            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null,ex);

        }


    }

}
