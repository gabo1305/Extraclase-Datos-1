package ITCR.Datos1.Extraclase1.chat;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
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
	
		JLabel texto=new JLabel("CLIENTE");
		
		add(texto);
	
		campo1=new JTextField(20);
	
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
				DataOutputStream flujoSalida = new DataOutputStream(misocket.getOutputStream());
				flujoSalida.writeUTF(campo1.getText());
				flujoSalida.close();
				System.out.println(campo1.getText());
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}





		}
	}
	
	
	
		
		
		
	private JTextField campo1;
	
	private JButton miboton;
	
}