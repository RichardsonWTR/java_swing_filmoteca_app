package tsi.lpv.samuelwagner.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JSeparator;

import tsi.lpv.samuelwagner.tratadorevento.RespostaEventoPesquisaElemento;
/**
 * A classe <code>IgPesquisa</code> � respons�vel por construir a interface gr�fica de pesquisa do aplicativo
 * Filmoteca.
 * @author Wagner Almeida
 * @author Samuel Gon�alves
 *
 */
public class IgPesquisa extends JDialog {
	private JTextField pesquisaTextField;
	private Color corBase = new Color(17,17,17);

	/**
	 * Construtor da classe <code>IgPesquisar</code> respons�vel por construir a interface gr�fica da janela de pesquisa.
	 * @param tipoPesquisa <code>String</code> com o tipo da pesquisa.
	 * @param mensagem <code>String</code> com a mensagem da pesquisa.
	 * @param igFilmoteca <code>IgFilmoteca</code> com a refer�ncia da tela principal.
	 * @param botaoChamada <code>JButton</code> com a refer�ncia do bot�o que disparou o evento.
	 */
	public IgPesquisa(String tipoPesquisa, String mensagem, IgFilmoteca igFilmoteca, JButton botaoChamada) {
		setSize(443,205);
		setTitle(tipoPesquisa);
		
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(corBase);
		
		setLocationRelativeTo(igFilmoteca);
		
		//Cria o painel de pesquisa.
		JPanel pesquisaPanel = new JPanel();
		pesquisaPanel.setBackground(corBase);
		pesquisaPanel.setBorder(new TitledBorder(null, tipoPesquisa, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pesquisaPanel.setBounds(10, 11, 418, 155);
		getContentPane().add(pesquisaPanel);
		pesquisaPanel.setLayout(null);
		
		//Cria o r�tulo da pesquisa.
		JLabel pesquisaLabel = new JLabel(mensagem);
		pesquisaLabel.setForeground(Color.WHITE);
		pesquisaLabel.setBounds(10, 50, 99, 14);
		pesquisaPanel.add(pesquisaLabel);
		
		setModal(true);
		
		//Cria o campo de texto para realizar a pesquisa.
		pesquisaTextField = new JTextField();
		pesquisaTextField.setBounds(127, 47, 267, 20);
		pesquisaPanel.add(pesquisaTextField);
		pesquisaTextField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(206, 110, 89, 23);
		pesquisaPanel.add(btnPesquisar);
		
		//Registra o tratador de eventos do bot�o pesquisar.
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RespostaEventoPesquisaElemento(igFilmoteca, pesquisaTextField.getText(), botaoChamada).pesquisa();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(305, 110, 89, 23);
		pesquisaPanel.add(btnCancelar);
		
		//Registra o tratador de eventos do bot�o pesquisar.
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IgPesquisa.this.dispose();
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 97, 398, 2);
		pesquisaPanel.add(separator);
		
		//Registra o tratador de eventos da janela de pesquisa.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgPesquisa.this.dispose();
			}
		});
		
		setVisible(true);
	}
}//class IgPesquisa
