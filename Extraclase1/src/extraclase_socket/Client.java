package extraclase_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
	// write your code here
        final String HOST = "127.0.0.1";
        final int PORT= 3000;
        DataInputStream in;
        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, PORT);
            in = new DataInputStream(sc.getInputStream());
            out= new DataOutputStream(sc.getOutputStream());

            out.writeUTF("Hi");

            String message = in.readUTF();

            System.out.println(message);

            sc.close();


        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null,ex);
        }

    }
}
