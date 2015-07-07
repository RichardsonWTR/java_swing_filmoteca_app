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
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/Cinema-icon.png")));
		setSize(443,222);
		setTitle(tipoPesquisa);
		
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(corBase);
		
		setLocationRelativeTo(igFilmoteca);
		
		//Cria o painel de pesquisa.
		JPanel pesquisaPanel = new JPanel();
		pesquisaPanel.setBackground(corBase);
		pesquisaPanel.setBorder(new TitledBorder(null, tipoPesquisa, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pesquisaPanel.setBounds(10, 11, 418, 172);
		getContentPane().add(pesquisaPanel);
		pesquisaPanel.setLayout(null);
		
		//Cria o r�tulo da pesquisa.
		JLabel pesquisaLabel = new JLabel(mensagem);
		pesquisaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		pesquisaLabel.setForeground(Color.WHITE);
		pesquisaLabel.setBounds(10, 63, 120, 14);
		pesquisaPanel.add(pesquisaLabel);
		
		setModal(true);
		
		//Cria o campo de texto para realizar a pesquisa.
		pesquisaTextField = new JTextField();
		pesquisaTextField.setBounds(140, 60, 268, 20);
		pesquisaPanel.add(pesquisaTextField);
		pesquisaTextField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setMnemonic(KeyEvent.VK_P);
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPesquisar.setIcon(new ImageIcon(IgPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/Watching_a_video_on_a_tablet_16.png")));
		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.setBounds(178, 123, 110, 38);
		pesquisaPanel.add(btnPesquisar);
		
		//Registra o tratador de eventos do bot�o pesquisar.
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RespostaEventoPesquisaElemento(igFilmoteca, pesquisaTextField.getText(), botaoChamada).pesquisa();
				if(!pesquisaTextField.getText().equals(""))
					IgPesquisa.this.dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setMnemonic(KeyEvent.VK_C);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setIcon(new ImageIcon(IgPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/Film_reel_circular_shape_16.png")));
		btnCancelar.setBounds(298, 123, 110, 38);
		pesquisaPanel.add(btnCancelar);
		
		//Registra o tratador de eventos do bot�o pesquisar.
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IgPesquisa.this.dispose();
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 110, 398, 2);
		pesquisaPanel.add(separator);
		
		JLabel popLabel = new JLabel("");
		popLabel.setIcon(new ImageIcon(IgPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/Popcorn_from_cinema_24.png")));
		popLabel.setBounds(360, 11, 48, 38);
		pesquisaPanel.add(popLabel);
		
		JLabel cineLabel = new JLabel("");
		cineLabel.setIcon(new ImageIcon(IgPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/3d_boy.png")));
		cineLabel.setBounds(10, 128, 39, 33);
		pesquisaPanel.add(cineLabel);
		
		JLabel ticketLabel = new JLabel("");
		ticketLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ticketLabel.setIcon(new ImageIcon(IgPesquisa.class.getResource("/tsi/lpv/samuelwagner/imagens/Cinema_date_day_calendar_page_24.png")));
		ticketLabel.setBounds(10, 11, 32, 41);
		pesquisaPanel.add(ticketLabel);
		
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
