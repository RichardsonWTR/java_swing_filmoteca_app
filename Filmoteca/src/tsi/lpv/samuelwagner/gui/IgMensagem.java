package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
/**
 * A classe <code>IgMensagem</code> constr�i a janela gr�fica respons�vel por exibir mensagens do usu�rio.
 * 
 * @author Wagner Almeida
 * @author Samuel Gon�alves
 */
public class IgMensagem extends JDialog {
	private Color corBase = new Color(17,17,17);
	/**
	 * Construtor da classe <code>IgMensagem</code>.
	 * @param componente <code>Component</code> com a refer�ncia da janela sobre a qual a janela de mensagem sera exibida.
	 * @param mensagem <code>String</code> com a mensagem a ser exibida.
	 */
	public IgMensagem(Component componente, String mensagem) {
		//Define o tamanho da janela.
		setSize(385, 186);
		
		//Define a cor do painel.
		getContentPane().setBackground(corBase);
		
		//Define o t�tulo da janela.
		setTitle("Filme");
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgMensagem.this.dispose();
			}
		});
		
		//Define a janela como n�o redimension�vel.
		setResizable(false);
		
		//Define a janela como modal
		setModal(true);
		getContentPane().setLayout(null);
		
		//Cria o painel de exibi��o da mensagem.
		JPanel mensagemPanel = new JPanel();
		mensagemPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Darth Movie Informa", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
		mensagemPanel.setBackground(corBase);
		mensagemPanel.setBounds(10, 11, 359, 139);
		getContentPane().add(mensagemPanel);
		mensagemPanel.setLayout(null);
		
		//Cria o bot�o de confirma��o para fechar a caixa de mensagem.
		JButton btnOk = new JButton("OK");
		btnOk.setForeground(Color.WHITE);
		btnOk.setBackground(Color.BLACK);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IgMensagem.this.dispose();
			}
		});
		
		btnOk.setBounds(135, 105, 89, 23);
		mensagemPanel.add(btnOk);
		
		JLabel mensagemLabel = new JLabel(mensagem);
		mensagemLabel.setForeground(Color.WHITE);
		mensagemLabel.setBounds(50, 46, 256, 16);
		mensagemPanel.add(mensagemLabel);
		
		//Define o posicionamento da janela.
		setLocationRelativeTo(componente);
		
		//Define a janela como visivel.
		setVisible(true);
	}//IgMensagem
}//class IgMensagem
