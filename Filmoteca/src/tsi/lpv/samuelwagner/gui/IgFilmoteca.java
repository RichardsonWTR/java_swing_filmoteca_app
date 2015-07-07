package tsi.lpv.samuelwagner.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.tratadorevento.TratadorEventoPesquisarFilme;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
/**
 * A classe <code>IgFilmoteca</code> � a respons�vel por construir a janela gr�fica principal.
 * @author Wagner Almeida
 * @author Samuel Gon�alves.
 *
 */
public class IgFilmoteca extends JFrame {
	private Color corBase =  new Color(17, 17, 17);
	private Color corFonte = new Color(237,255,40);
	private Color corMenu = new Color(148,151,151);
	private Color corSubMenu = new Color(124,61,139);
	private JTextField pesquisarTextField;
	private static JButton buscarFilmeButton;
	private JPanel exibicaoPanel;
	private JList <String> resultadoJList;
	private JScrollPane resultadoScrollPane;
	private JButton atorButton;
	private JButton autorButton;
	private JButton diretorButton;
	private JButton generoButton;
	private JButton rankingButton;
	
	/**
	 * Construtor da classe IgFilmoteca.
	 */
	public IgFilmoteca() {
		//Define o nome do app
		setTitle("Darth Movies");
		
		//Muda o icone da barra de t�tulos da janela. Cria um objeto url e passa o endere�o da imagem.
		URL url = this.getClass().getResource("/tsi/lpv/samuelwagner/imagens/Darth_Vader_Mask_64.png");
	
		//Cria um objeto imagem, Obt�m a refer�ncia da imagem da barra e seta a nova imagem.
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		
		//Define a nova imagem para a janela.
		this.setIconImage(imagemTitulo);
		
		//Muda a cor do painel.
		getContentPane().setBackground(corBase);
		getContentPane().setForeground(corFonte);
		getContentPane().setLayout(null);
		
		//Cria o painel lateral.
		JPanel panel = new JPanel();
		panel.setBackground(corBase);
		panel.setBounds(0, 108, 153, 305);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Cria o bot�o de cadastrar filme.
		JButton cadastrarFilmeButton = new JButton("Cadastrar Filme");
		cadastrarFilmeButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/video.png")));
		cadastrarFilmeButton.setBackground(Color.BLACK);
		cadastrarFilmeButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		cadastrarFilmeButton.setForeground(Color.WHITE);
		cadastrarFilmeButton.setToolTipText("Cadastrar Filmes");
		cadastrarFilmeButton.setBounds(0, 11, 153, 38);
		panel.add(cadastrarFilmeButton);
		
		//Registra o tratador de eventos do bot�o cadastrar filme.
		cadastrarFilmeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastrarFilme(IgFilmoteca.this);
			}
		});
		
		//Cria o bot�o de procurar diretor.
		diretorButton = new JButton("Buscar Diretor");
		diretorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/director_sit.png")));
		diretorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		diretorButton.setForeground(Color.WHITE);
		diretorButton.setBackground(Color.BLACK);
		diretorButton.setBounds(0, 158, 153, 38);
		panel.add(diretorButton);
		diretorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgPesquisa("Pesquisar Filmes Diretor", "Nome do Diretor: ", IgFilmoteca.this, IgFilmoteca.this.diretorButton);
			}
		});
		
		//Cria o bot�o de buscar por ator.
		atorButton = new JButton("Buscar Ator");
		atorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/actordark.png")));
		atorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		atorButton.setBackground(Color.BLACK);
		atorButton.setForeground(Color.WHITE);
		atorButton.setBounds(0, 60, 153, 38);
		panel.add(atorButton);
		
		//Registra o tratador de eventos do artista
		atorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgPesquisa("Pesquisar Filmes Artista", "Nome do artista: ", IgFilmoteca.this, IgFilmoteca.this.atorButton);
			}
		});
		
		//Cria bot�o de busca por autor.
		autorButton = new JButton("Buscar Autor");
		autorButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/author.png")));
		autorButton.setBackground(Color.BLACK);
		autorButton.setForeground(Color.WHITE);
		autorButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		autorButton.setBounds(0, 109, 153, 38);
		panel.add(autorButton);
		//Registra o tratador de eventos do autor.
		autorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgPesquisa("Pesquisar Filmes Autor", "Nome do autor: ", IgFilmoteca.this, IgFilmoteca.this.autorButton);
			}
		});
		
		//Criar bot�o de busca por gen�ro.
		generoButton = new JButton("Filmes G\u00EAnero");
		generoButton.setBounds(0, 207, 153, 38);
		panel.add(generoButton);
		generoButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/Hollywood_sign_24.png")));
		generoButton.setBackground(Color.BLACK);
		generoButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		generoButton.setForeground(Color.WHITE);
		generoButton.setToolTipText("Pesquisar por g\u00EAneros");
		
		generoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgPesquisa("Pesquisar Filmes pelo G�nero", "G�nero: ", IgFilmoteca.this, IgFilmoteca.this.generoButton);
			}
		});
		
		//Criar bot�o de exibir por filmes melhor classificados.
		rankingButton = new JButton("Ver Preferidos");
		rankingButton.setBounds(0, 256, 153, 38);
		panel.add(rankingButton);
		rankingButton.setIcon(new ImageIcon(IgFilmoteca.class.getResource("/tsi/lpv/samuelwagner/imagens/keditbookmarks.png")));
		rankingButton.setBackground(Color.BLACK);
		rankingButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		rankingButton.setForeground(Color.WHITE);
		rankingButton.setToolTipText("Visualizar filmes na ordem de prefer\u00EAncia.");
		
		//Cria o painel pra barra de pesquisa.
		JPanel pesquisarPanel = new JPanel();
		pesquisarPanel.setBackground(corSubMenu);
		pesquisarPanel.setBounds(0, 21, 839, 38);
		getContentPane().add(pesquisarPanel);
		pesquisarPanel.setLayout(null);
		
		//Inst�ncia o objeto respons�vel por conter os dados da pesquisa.
		pesquisarTextField = new JTextField();
		
		//Registra o tratador de eventos do jtextField relativo ao teclado.
		pesquisarTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pesquisarTextField.setText("");
			}
		});
		
		pesquisarTextField.setText("Pesquisar Filme");
		pesquisarTextField.setToolTipText("Pesquisar Filme");
		pesquisarTextField.setBounds(606, 11, 121, 20);
		pesquisarPanel.add(pesquisarTextField);
		pesquisarTextField.setColumns(10);
		
		buscarFilmeButton = new JButton("Buscar");
		buscarFilmeButton.setBounds(737, 10, 92, 23);
		pesquisarPanel.add(buscarFilmeButton);
		
		//Registra o tratador de eventos da pesquisa.
		buscarFilmeButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TratadorEventoPesquisarFilme(IgFilmoteca.this, pesquisarTextField.getText()).pesquisarFilme();
			}
		});
		
		//Inst�ncia o painel de exibi��o.
		exibicaoPanel = new JPanel();
		exibicaoPanel.setBackground(corBase);
		exibicaoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Darth Movies", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		exibicaoPanel.setBounds(161, 70, 668, 387);
		getContentPane().add(exibicaoPanel);
		exibicaoPanel.setLayout(null);
		
		//Cria o resultado ScroopPane
		resultadoScrollPane = new JScrollPane();
		resultadoScrollPane.setBounds(10, 37, 648, 295);
		exibicaoPanel.add(resultadoScrollPane);
		
		//Cria o resultadoJList
		resultadoJList = new JList<String>();
		resultadoJList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		resultadoJList.setBackground(corBase);
		resultadoJList.setForeground(Color.WHITE);
		resultadoJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultadoScrollPane.setViewportView(resultadoJList);
		
		JButton detalhesButton = new JButton("Detalhes");
		
		//Registra o tratador de eventos do bot�o de detalhes
		detalhesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(resultadoJList.getSelectedIndex() == -1) new IgMensagem(IgFilmoteca.this, "Voc� deve selecionar um item retornado da pesquisa");
				else new TratadorEventoPesquisarFilme(IgFilmoteca.this, resultadoJList.getSelectedValue()).pesquisarFilme();
			}
		});
		detalhesButton.setToolTipText("Selecione um filme da pesquisa e clique em detalhes para ver mais informa\u00F5es do filme.");
		detalhesButton.setBounds(569, 353, 89, 23);
		exibicaoPanel.add(detalhesButton);
		
		//Define a tela como n�o redimension�vel.
		setResizable(false);
		
		setLocationByPlatform(true);
		
		//Define o tamanho da janela.
		setSize(845, 517);
		
		//Define a a��o a ser tomada quando o bot�o fechar for apertado.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar principalMenuBar = new JMenuBar();
		principalMenuBar.setBackground(corMenu);
		setJMenuBar(principalMenuBar);
		
		JMenu arquivoMenu = new JMenu("Arquivo");
		arquivoMenu.setBackground(corBase);
		arquivoMenu.setForeground(Color.WHITE);
		principalMenuBar.add(arquivoMenu);
		
		JMenuItem apagarMenuItem= new JMenuItem("Apagar banco de dados");
		apagarMenuItem.setForeground(Color.WHITE);
		apagarMenuItem.setBackground(corBase);
		arquivoMenu.add(apagarMenuItem);
		
		//Define a tela como v�sivel.
		setLocationRelativeTo(null);
		setVisible(false);
	}//IgFilmoteca

	/**
	 * Obt�m a refer�ncia da caixa de texto de pesquisa.
	 * @return <code>JTextField</code>
	 */
	public JTextField getPesquisarTextField() {
		return pesquisarTextField;
	}

	/**
	 * Obt�m a refer�ncia do painel de exibi��o da tela principal onde ser�o realizadas as opera��es.
	 * @return <code>JPanel</code>
	 */
	public JPanel getExibicaoPanel() {
		return exibicaoPanel;
	}
	
	/**
	 * Obt�m a refer�ncia do bot�o de pesquisar filme da tela principal.
	 * @return <code>JButton</code>
	 */
	public static JButton getBuscarFilmeButton() {
		return buscarFilmeButton;
	}
	
	/**
	 * Obt�m a refer�ncia da lista onde ser�o exibidos os resultados das pesquisas.
	 * @return <code>JList</code>
	 */
	public JList <String> getResultadoJList() {
		return resultadoJList;
	}

	/**
	 * Ob�m a refer�ncia do painel rol�vel onde ser�o exibidos os resultados das pesquisa.
	 * @return <code>JSCrollPane</code>
	 */
	public JScrollPane getResultadoScrollPane() {
		return resultadoScrollPane;
	}

	public JButton getAtorButton() {
		return atorButton;
	}

	public JButton getAutorButton() {
		return autorButton;
	}

	public JButton getDiretorButton() {
		return diretorButton;
	}

	public JButton getBtnGenro() {
		return generoButton;
	}

	public JButton getRankingButton() {
		return rankingButton;
	}
}//class IgFilmoteca
