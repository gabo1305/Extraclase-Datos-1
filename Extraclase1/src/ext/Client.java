package ext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

    private int port;
    private String message;
    public Client(int port, String message);
    this.port=port;
    this.message=message;


    @Override
    public void run() {

        // write your code here
        final String HOST = "127.0.0.1";
        final int PORT= 3000;
        DataInputStream in;
        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, PORT);
            in = new DataInputStream(sc.getInputStream());
            out= new DataOutputStream(sc.getOutputStream());
            out.writeUTF(message);
            

            sc.close();


        } catch (IOException ex) {
                Logger.getLogger(extraclase_socket.Client.class.getName()).log(Level.SEVERE, null,ex);
        }

    }
}

