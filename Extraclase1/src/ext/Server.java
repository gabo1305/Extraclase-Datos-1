package ext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Observable implements Runnable{

    private int port;

    public Server(int puerto){
        this.port=port;
    }

    @Override
    public void run() {
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;



        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started");

            while (true){
                sc = server.accept();

                System.out.println("client conected");

                in = new DataInputStream(sc.getInputStream());


                String message= in.readUTF();

                System.out.println(message);


                this.setChanged();
                this.notifyObservers(message);
                this.clearChanged();

                sc.close();
                System.out.println("Client desconected ");




            }

        } catch (IOException ex) {
            Logger.getLogger(extraclase_socket.Server.class.getName()).log(Level.SEVERE, null,ex);

        }


    }
}
