package ITCR.Datos1.Extraclase1.chat;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Declaracion de clase cleinte
 * @author Gabriel Solano
 * version 3.0
 *
 */

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GaboCliente migabo=new GaboCliente();
		
		migabo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

/**
 * Para implementar interfaz
 * @author Gabriel Solano
 */
class GaboCliente extends JFrame{
	
	public GaboCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaGaboCliente milamina=new LaminaGaboCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

/**
 * Esta clase implementa la interfaz y la extension Runnable
 *
 */
class LaminaGaboCliente extends JPanel implements Runnable{
	
	public LaminaGaboCliente(){
		String idUser= JOptionPane.showInputDialog("User:");

		JLabel nid= new JLabel("User:");

		add(nid);

		id=new JLabel();

		id.setText(idUser);
		add(id);
		ip = new JTextField(8);
		add(ip);
	
		JLabel texto=new JLabel("-CHAT-");
		
		add(texto);

		campochat=new JTextArea(11,19);

		add(campochat);
	
		campo1=new JTextField(19);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		SendText event= new SendText();
		miboton.addActionListener(event);
		
		add(miboton);

		//Hilos
		Thread mihilo= new Thread(this);
		mihilo.start();
		
	}

	/**
	 * Declaracion de clase privada, creada para enviar el texto
	 * implemena ActionListener.
	 *
	 *
	 */
	private class SendText implements ActionListener {

		/**
		 *
		 * Metododo Para capturar acciones del useuario
		 * @param e
		 * paramentro: El evento, como ingreso de texto
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			campochat.append("\n"+ campo1.getText());
			try {
				Socket misocket = new Socket("192.168.56.1",9999);
				PaqueteEnvio data= new PaqueteEnvio();
				data.setId(id.getText());
				data.setPort(ip.getText());
				data.setMessage(campo1.getText());

				ObjectOutputStream paqueteDatos = new ObjectOutputStream(misocket.getOutputStream());
				paqueteDatos.writeObject(data);
				misocket.close();

			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}

		}
	}

	private JTextField campo1,ip;
	private JLabel id;
	private JTextArea campochat;
	private JButton miboton;
	@Override
	/**
	 * Metodo generado automaticamente por Runnable
	 *
	 */
	public void run() {
		try{
			ServerSocket servidorCliente= new ServerSocket(9090);
			Socket cliente;
			PaqueteEnvio paqueteRecibido;
			//Ciclo infinito
			while(true){

				cliente=servidorCliente.accept();
				ObjectInputStream flujoentrada= new ObjectInputStream(cliente.getInputStream());
				paqueteRecibido= (PaqueteEnvio) flujoentrada.readObject();

				campochat.append("\n"+ paqueteRecibido.getId() + ": "+ paqueteRecibido.getMessage());
			}


		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
}

/**
 * Declaracion de clase PaqueteEnvio
 * son Setter y getter de id,ip y message, para poder modificar
 * y obtener sus valores
 */
class PaqueteEnvio implements Serializable {
	/**
	 * @param id,ip,message
	 *
	 *
	 */
	private String id,ip,message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setPort(String ip) {
		this.ip = ip ;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}