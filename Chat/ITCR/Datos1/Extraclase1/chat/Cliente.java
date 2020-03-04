package ITCR.Datos1.Extraclase1.chat;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GaboCliente migabo=new GaboCliente();
		
		migabo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class GaboCliente extends JFrame{
	
	public GaboCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaGaboCliente milamina=new LaminaGaboCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaGaboCliente extends JPanel{
	
	public LaminaGaboCliente(){

		id=new JTextField(5);
		add(id);
		ip = new JTextField(8);
		add(ip);
	
		JLabel texto=new JLabel("CHAT");
		
		add(texto);

		campochat=new JTextArea(11,19);

		add(campochat);
	
		campo1=new JTextField(19);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		SendText event= new SendText();
		miboton.addActionListener(event);
		
		add(miboton);	
		
	}
	private class SendText implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Socket misocket = new Socket("127.0.0.1",5050);
				PaqueteEnvio data= new PaqueteEnvio();
				data.setId(id.getText());
				data.setPort(ip.getText());
				data.setMessage(campo1.getText());

				ObjectOutputStream paqueteDatos = new ObjectOutputStream(misocket.getOutputStream());
				paqueteDatos.writeObject(data);
				misocket.close();

				//DataOutputStream flujoSalida = new DataOutputStream(misocket.getOutputStream());
				//flujoSalida.writeUTF(campo1.getText());
				//flujoSalida.close();

			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}





		}
	}
	
	
	
		
		
		
	private JTextField campo1,id,ip;

	private JTextArea campochat;
	
	private JButton miboton;
	
}

class PaqueteEnvio implements Serializable {
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