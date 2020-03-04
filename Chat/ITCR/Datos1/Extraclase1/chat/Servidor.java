package ITCR.Datos1.Extraclase1.chat;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**Declaracion de la clase Servidor
 * @author Gabriel Solano
 * @version 3.0
 *
 */
public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GaboServidor migabo=new GaboServidor();
		migabo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

/**
 * Esta clase contiene interfaz, hilos
 * @author Gabriel Solano
 */
class GaboServidor extends JFrame implements Runnable {
	
	public GaboServidor(){

		//Interfaz*

		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);

		//Implementacionn= de hilos
		Thread mihilo = new Thread(this);
		mihilo.start();

		}

	/**
	 *Metodo del componente Runnable
	 * @author Gabriel Solano
	 *
	 *
 	 */
	@Override
	public void run() {
		try {
			ServerSocket servidor = new ServerSocket(9999);
			String id,ip,message; //id= identificador

			PaqueteEnvio paqueteRecibido;


			//Ciclo infinito para que el servidor siga esuchcando
			while (true) {
				Socket misocket = servidor.accept();

				ObjectInputStream paqueteDatos= new ObjectInputStream(misocket.getInputStream());

				paqueteRecibido= (PaqueteEnvio) paqueteDatos.readObject();

				id=paqueteRecibido.getId();
				ip=paqueteRecibido.getIp();
				message=paqueteRecibido.getMessage();

				areatexto.append("\n "+ id+": "+message+ "to: "+ ip);

				Socket enviaDestinatario=new Socket(ip,9090);

				ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
				paqueteReenvio.writeObject(paqueteRecibido);

				paqueteReenvio.close();

				enviaDestinatario.close();

				misocket.close();
			}



		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}


	}
	private	JTextArea areatexto;
}
