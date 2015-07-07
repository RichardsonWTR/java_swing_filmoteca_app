package tsi.lpv.samuelwagner.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import tsi.lpv.samuelwagner.tipo.Filme;
import java.awt.Toolkit;
/**
 * A classe <code>IgResultadoPesquisaFilme</code> constr�i a janela gr�fica respons�vel por exibir os resultados da busca 
 * por um filme, caso este esteja cadastrado no banco de dados.
 * 
 * @author Wagner Almeida
 * @author Samuel Gon�alves
 */
public class IgResultadoPesquisaFilme extends JDialog {
	private Color corBase = new Color(17,17,17);
	private Color corLista = new Color(114,124,115);
	private JLabel anoLabel;
	private JLabel duracaoLabel;
	private JLabel etariaLabel;
	private JLabel generoLabel;
	private JLabel nomeFilme;
	private JPanel fotoPanel;
	private JTextArea sinopseTextArea;
	private JTextArea elencoTextArea;
	private JTextField autorTextField;
	private JTextField diretorTextField;
	private JTextField imdbTextField;
	private JTextField pessoalTextField;
	private JTextField midiaTextField;
	private JTextField lancamentoTextField;
	private JLabel paisLabel;
	private JLabel fotoLabel;
	/**
	 * Construtor da classe <code>IgResultadoFilme</code>.
	 * @param componente <code>IgFilmoteca</code> com a refer�ncia do m�todo que realizou a chamada a classe.
	 * @param filme
	 */
	public IgResultadoPesquisaFilme(IgFilmoteca componente, Filme filme) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgResultadoPesquisaFilme.class.getResource("/tsi/lpv/samuelwagner/imagens/Cinema-Batman-Old-icon.png")));
		//Define o tamanho da janela.
		setSize(841, 607);
		
		setLocationRelativeTo(componente);
		
		getContentPane().setBackground(corBase);
		
		//Define o t�tulo da janela.
		setTitle(filme.getTitulo());
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgResultadoPesquisaFilme.this.dispose();
			}
		});
		
		//Define a janela como n�o redimension�vel.
		setResizable(false);
		
		//Define a janela como modal
		setModal(true);
		getContentPane().setLayout(null);
		
		//Cria o pain�l de exibi��o da foto.
		fotoPanel = new JPanel();
		fotoPanel.setBorder(new TitledBorder(null, "Imagem", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		fotoPanel.setBackground(corBase);
		fotoPanel.setBounds(10, 11, 235, 286);
		getContentPane().add(fotoPanel);
		fotoPanel.setLayout(null);

		fotoLabel = new JLabel("N�o h� Foto.");
		fotoLabel.setBounds(10, 21, 215, 254);
		fotoPanel.add(fotoLabel);
		
		//Cria o t�tulo.
		nomeFilme = new JLabel("T\u00EDtulo");
		nomeFilme.setForeground(Color.WHITE);
		nomeFilme.setFont(new Font("Tahoma", Font.BOLD, 14));
		nomeFilme.setBounds(265, 11, 560, 22);
		getContentPane().add(nomeFilme);
		
		//Cria o painel adicional
		JPanel adicionalPanel = new JPanel();
		adicionalPanel.setBackground(corBase);
		adicionalPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informa\u00E7\u00F5es adicionais", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		adicionalPanel.setBounds(10, 308, 815, 268);
		getContentPane().add(adicionalPanel);
		adicionalPanel.setLayout(null);
		
		//Cria o painel data de lancamento.
		JPanel dataLancamentoPanel = new JPanel();
		dataLancamentoPanel.setBounds(10, 31, 795, 28);
		dataLancamentoPanel.setBackground(corLista);
		adicionalPanel.add(dataLancamentoPanel);
		dataLancamentoPanel.setLayout(null);
		
		JLabel dataLabel = new JLabel("Data de Lan\u00E7amento:");
		dataLabel.setBounds(10, 7, 103, 14);
		dataLancamentoPanel.add(dataLabel);
		dataLabel.setForeground(Color.WHITE);
		
		//Cria o lancamentoTextField
		lancamentoTextField = new JTextField();
		lancamentoTextField.setBorder(null);
		lancamentoTextField.setBackground(corLista);
		lancamentoTextField.setForeground(Color.WHITE);
		lancamentoTextField.setEditable(false);
		lancamentoTextField.setBounds(123, 4, 193, 20);
		dataLancamentoPanel.add(lancamentoTextField);
		lancamentoTextField.setColumns(10);
		
		//Cria o m�dia panel
		JPanel midiaPanel = new JPanel();
		midiaPanel.setBackground(corBase);
		midiaPanel.setBounds(10, 58, 795, 28);
		adicionalPanel.add(midiaPanel);
		midiaPanel.setLayout(null);
		
		//Cria o midia label
		JLabel midiaLabel = new JLabel("M\u00EDdia:");
		midiaLabel.setBounds(10, 7, 28, 14);
		midiaPanel.add(midiaLabel);
		midiaLabel.setForeground(Color.WHITE);
		
		//Cria o midiaTextField
		midiaTextField = new JTextField();
		midiaTextField.setBorder(null);
		midiaTextField.setBackground(corBase);
		midiaTextField.setForeground(Color.WHITE);
		midiaTextField.setEditable(false);
		midiaTextField.setBounds(123, 4, 189, 20);
		midiaPanel.add(midiaTextField);
		midiaTextField.setColumns(10);
		
		//Cria o pessoalPanel
		JPanel pessoalPanel = new JPanel();
		pessoalPanel.setBackground(corLista);
		pessoalPanel.setBounds(10, 86, 795, 28);
		adicionalPanel.add(pessoalPanel);
		pessoalPanel.setLayout(null);
		
		//Cria o pessoalLabel
		JLabel pessoalLabel = new JLabel("Classifica\u00E7\u00E3o Pessoal:");
		pessoalLabel.setBounds(10, 7, 116, 14);
		pessoalPanel.add(pessoalLabel);
		pessoalLabel.setForeground(Color.WHITE);
		
		//Cria o pessoalTextField
		pessoalTextField = new JTextField();
		pessoalTextField.setBorder(null);
		pessoalTextField.setBackground(corLista);
		pessoalTextField.setForeground(Color.WHITE);
		pessoalTextField.setEditable(false);
		pessoalTextField.setBounds(123, 4, 190, 20);
		pessoalPanel.add(pessoalTextField);
		pessoalTextField.setColumns(10);
		
		//Cria o imdbPanel
		JPanel imdbPanel = new JPanel();
		imdbPanel.setBackground(corBase);
		imdbPanel.setBounds(10, 114, 795, 28);
		adicionalPanel.add(imdbPanel);
		imdbPanel.setLayout(null);
		
		//Cria o imdbLabel
		JLabel imdbLabel = new JLabel(" IMDB:");
		imdbLabel.setBounds(10, 7, 32, 14);
		imdbPanel.add(imdbLabel);
		imdbLabel.setForeground(Color.WHITE);
		
		//Cria o imdbTextField.
		imdbTextField = new JTextField();
		imdbTextField.setBackground(corBase);
		imdbTextField.setBorder(null);
		imdbTextField.setForeground(Color.WHITE);
		imdbTextField.setEditable(false);
		imdbTextField.setBounds(123, 4, 192, 20);
		imdbPanel.add(imdbTextField);
		imdbTextField.setColumns(10);
		
		//Cria o direcaoPanel
		JPanel direcaoPanel = new JPanel();
		direcaoPanel.setBackground(corLista);
		direcaoPanel.setBounds(10, 142, 795, 28);
		adicionalPanel.add(direcaoPanel);
		direcaoPanel.setLayout(null);
		
		//Cria o direcaoLabel.
		JLabel direcaoLabel = new JLabel("Dire\u00E7\u00E3o:");
		direcaoLabel.setBounds(10, 7, 49, 14);
		direcaoPanel.add(direcaoLabel);
		direcaoLabel.setForeground(Color.WHITE);
		
		//Cria o diretorTextField
		diretorTextField = new JTextField();
		diretorTextField.setBorder(null);
		diretorTextField.setBackground(corLista);
		diretorTextField.setForeground(Color.WHITE);
		diretorTextField.setEditable(false);
		diretorTextField.setBounds(123, 4, 602, 20);
		direcaoPanel.add(diretorTextField);
		diretorTextField.setColumns(10);
		
		//Cria o autorPanel
		JPanel autorPanel = new JPanel();
		autorPanel.setBackground(corBase);
		autorPanel.setBounds(10, 170, 795, 28);
		adicionalPanel.add(autorPanel);
		autorPanel.setLayout(null);
		
		//Cria o autorLabel
		JLabel autorLabel = new JLabel("Autor:");
		autorLabel.setBounds(10, 7, 49, 14);
		autorPanel.add(autorLabel);
		autorLabel.setForeground(Color.WHITE);
		
		//Cria o autorTextField
		autorTextField = new JTextField();
		autorTextField.setBackground(corBase);
		autorTextField.setBorder(null);
		autorTextField.setForeground(Color.WHITE);
		autorTextField.setEditable(false);
		autorTextField.setBounds(123, 4, 596, 20);
		autorPanel.add(autorTextField);
		autorTextField.setColumns(10);
		
		//Cria o elencoPanel
		JPanel elencoPanel = new JPanel();
		elencoPanel.setBackground(corLista);
		elencoPanel.setBounds(10, 199, 795, 58);
		adicionalPanel.add(elencoPanel);
		elencoPanel.setLayout(null);
		
		//Cria o lblEleco
		JLabel lblElenco = new JLabel("Elenco:");
		lblElenco.setForeground(Color.WHITE);
		lblElenco.setBounds(10, 11, 46, 14);
		elencoPanel.add(lblElenco);
		
		//Cria o elencoScrollPanel
		JScrollPane elencoScrollPanel = new JScrollPane();
		elencoScrollPanel.setBounds(123, 11, 591, 36);
		elencoScrollPanel.setBorder(null);
		elencoPanel.add(elencoScrollPanel);
		
		//Cria o elencoTextArea
		elencoTextArea = new JTextArea();
		elencoTextArea.setForeground(Color.WHITE);
		elencoTextArea.setLocation(123, 0);
		elencoTextArea.setEditable(false);
		elencoTextArea.setWrapStyleWord(true);
		elencoTextArea.setLineWrap(true);
		elencoTextArea.setBackground(corLista);
		elencoScrollPanel.setViewportView(elencoTextArea);
		
		//Cria o sinopsePanel.
		JPanel sinopsePanel = new JPanel();
		sinopsePanel.setBackground(corBase);
		sinopsePanel.setBorder(new TitledBorder(null, "Sinopse", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		sinopsePanel.setBounds(265, 93, 560, 204);
		getContentPane().add(sinopsePanel);
		sinopsePanel.setLayout(null);
		
		//Cria o sinopaseScrollPane
		JScrollPane sinopseScrollPane = new JScrollPane();
		sinopseScrollPane.setBorder(null);
		sinopseScrollPane.setBounds(10, 17, 540, 151);
		sinopsePanel.add(sinopseScrollPane);
		
		//Cria o sinopseTextArea
		sinopseTextArea = new JTextArea();
		sinopseTextArea.setForeground(Color.WHITE);
		sinopseTextArea.setBackground(corBase);
		sinopseTextArea.setEditable(false);
		sinopseTextArea.setLineWrap(true);
		sinopseScrollPane.setViewportView(sinopseTextArea);
		
		//Cria o anoLabel
		anoLabel = new JLabel("Ano:");
		anoLabel.setBounds(690, 17, 135, 14);
		anoLabel.setBounds(265, 44, 111, 14);
		getContentPane().add(anoLabel);
		anoLabel.setForeground(Color.WHITE);
		
		//Cria o dura��oLabel
		duracaoLabel = new JLabel("Dura\u00E7\u00E3o:");
		duracaoLabel.setBounds(155, 44, 197, 14);
		duracaoLabel.setBounds(386, 44, 197, 14);
		getContentPane().add(duracaoLabel);
		duracaoLabel.setForeground(Color.WHITE);
		
		//Cria o etariaLabel
		etariaLabel = new JLabel("Classifica\u00E7\u00E3o Et\u00E1ria:");
		etariaLabel.setBounds(155, 69, 326, 14);
		etariaLabel.setBounds(265, 69, 280, 14);
		getContentPane().add(etariaLabel);
		etariaLabel.setForeground(Color.WHITE);
		
		//Cria o generoLabel
		generoLabel = new JLabel("G\u00EAnero:");
		generoLabel.setForeground(Color.WHITE);
		generoLabel.setBounds(491, 69, 334, 14);
		generoLabel.setBounds(590, 69, 235, 14);
		getContentPane().add(generoLabel);
		
		paisLabel = new JLabel("Pa\u00EDs:");
		paisLabel.setForeground(Color.WHITE);
		paisLabel.setBounds(491, 44, 150, 14);
		paisLabel.setBounds(590, 44, 235, 14);
		getContentPane().add(paisLabel);
	}//IgResultadoFilme()
	
	/**
	 * Obt�m a refer�ncia do <code>JLabel</code> com o ano do filme.
	 * @return <code>JLabel</code>
	 */
	public JLabel getAnoLabel() {
		return anoLabel;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JLabel</code> duracao do filme.
	 * @return <code>JLabel</code>
	 */
	public JLabel getDuracaoLabel() {
		return duracaoLabel;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JLabel</code> faixa et�ria.
	 * @return <code>JLabel</code>
	 */
	public JLabel getEtariaLabel() {
		return etariaLabel;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JLabel</code> g�nero.
	 * @return <code>JLabel</code>
	 */
	public JLabel getGeneroLabel() {
		return generoLabel;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JLabel</code> nome do filme.
	 * @return <code>JLabel</code>
	 */
	public JLabel getNomeFilme() {
		return nomeFilme;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JPanel</code> que exibir� a foto.
	 * @return <code>JPanel</code>
	 */
	public JPanel getFotoPanel() {
		return fotoPanel;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JTextArea</code> que exibir� a sinopse do filme.
	 * @return <code>JTextArea</code>
	 */
	public JTextArea getSinopseTextArea() {
		return sinopseTextArea;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JTextArea</code> que exibir� o elenco do filme.
	 * @return <code>JTextArea</code>
	 */
	public JTextArea getElencoTextArea() {
		return elencoTextArea;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JTextField</code> que exibir� o autor do filme.
	 * @return <code>JTextField</code>
	 */
	public JTextField getAutorTextField() {
		return autorTextField;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JTextField</code> que exibir� o diretor do filme.
	 * @return <code>JTextField</code>
	 */
	public JTextField getDiretorTextField() {
		return diretorTextField;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JTextField</code> que exibir� a classifica��o imdb do filme.
	 * @return <code>JTextField</code>
	 */
	public JTextField getImdbTextField() {
		return imdbTextField;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JTextField</code> que exibir� a classifica��o pessoal do filme.
	 * @return <code>JTextField</code>
	 */
	public JTextField getPessoalTextField() {
		return pessoalTextField;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JTextField</code> que exibir� a m�dia em que o filme foi salva.
	 * @return <code>JTextField</code>
	 */
	public JTextField getMidiaTextField() {
		return midiaTextField;
	}
	
	/**
	 * Obt�m a refer�ncia do <code>JTextField</code> que exibir� a data de lan�amento do filme.
	 * @return <code>JTextField</code>
	 */
	public JTextField getLancamentoTextField() {
		return lancamentoTextField;
	}

	/**
	 * Obt�m a refer�ncia do <code>JLabel</code> que exibir� o pa�s do filme.
	 * @return <code>JLabel</code>
	 */
	public JLabel getPaisLabel() {
		return paisLabel;
	}

	/**
	 * Obt�m a refer�ncia do <code>JLabel</code> que exibe a foto.
	 * @return <code>JLabel</code>
	 */
	public JLabel getFotoLabel() {
		return fotoLabel;
	}
}//class IgResultadoFilme
