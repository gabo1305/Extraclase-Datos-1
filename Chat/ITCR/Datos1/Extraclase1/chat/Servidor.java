package ITCR.Datos1.Extraclase1.chat;

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GaboServidor migabo=new GaboServidor();
		
		migabo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class GaboServidor extends JFrame implements Runnable {
	
	public GaboServidor(){


		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		Thread mihilo = new Thread(this);
		mihilo.start();

		}
	


	@Override
	public void run() {

		System.out.println("ff");
		try {
			ServerSocket servidor = new ServerSocket(5050);
			String id,ip,message;

			PaqueteEnvio paqueteRecibido;

			while (true) {
				Socket misocket = servidor.accept();
				//DataInputStream flujoEntrada = new DataInputStream(misocket.getInputStream());

				ObjectInputStream paqueteDatos= new ObjectInputStream(misocket.getInputStream());



				paqueteRecibido= (PaqueteEnvio) paqueteDatos.readObject();
				//String message = flujoEntrada.readUTF();

				//areatexto.append("\n" + message);
				id=paqueteRecibido.getId();
				ip=paqueteRecibido.getIp();
				message=paqueteRecibido.getMessage();

				areatexto.append("\n "+ id+": "+message+ "to: "+ip);

				misocket.close();
			}



		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}


	}
	private	JTextArea areatexto;
}
