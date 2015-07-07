package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.border.LineBorder;

import tsi.lpv.samuelwagner.controller.CadastroControle;
import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;
import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroDAO;
import tsi.lpv.samuelwagner.persistencia.PaisDAO;
import tsi.lpv.samuelwagner.tipo.Artista;
import tsi.lpv.samuelwagner.tipo.Autor;
import tsi.lpv.samuelwagner.tipo.Diretor;
import tsi.lpv.samuelwagner.tipo.Filme;
import tsi.lpv.samuelwagner.tipo.Genero;
import tsi.lpv.samuelwagner.tipo.Pais;

/** Classe <code>TratadorEventoCadastraFilme</code> responsavel por tratar os eventos 
 *  da classe <code>IgCadastrarFilme</code>.
 * @author Samuel
 * @author Wagner
 */
public class TratadorEventoCadastraFilme implements ActionListener {
	private IgCadastrarFilme igCadastrarFilme;
	
	/**Construtor Sobrecarregado da classe <code>TratadorEventoCadastraFilme</code>.
	 * @param igCadastrarFilme referencia da Classe <code>IgCadastrarFilme</code>.
	 */
	public TratadorEventoCadastraFilme(IgCadastrarFilme igCadastrarFilme) {
		this.igCadastrarFilme = igCadastrarFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//Verifica qual botao foi clicado pelo usuario.
		if(igCadastrarFilme.getCadastrarButton() == event.getSource()){
			validaCadastro();
		}else
			if(igCadastrarFilme.getCancelarButton() == event.getSource())
				igCadastrarFilme.dispose();
	}
	
	private void validaCadastro(){
		//Valida se o usuario forneceu os dados correto para o cadastro do Filme.
		if(validaCadastroEAvisaUsuario(validaNomeFilme(),validaNomePais(),validaDuracao(),validaNomesAtor(),
				validaNomesAutor(),validaNomesDiretor())){
				cadastraFilme();
				FuncaoAuxiliar.exibirMensagem(igCadastrarFilme, "Filme Cadastrado Com sucesso.", "Cadastra Filme.");
					igCadastrarFilme.dispose();
				}else{
					igCadastrarFilme.getInformaLabel().setText("Os Campos em Vermelho s�o de Preenchimento Obrigatorio.");
				}
	}
	//Valida o nome do filme.
	private boolean validaNomeFilme(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getTituloField().getText()))
			//Verifica se o filme ja est� Cadastrado no Banco de Dados.
			if(FilmeDAO.pesquisarFilme(igCadastrarFilme.getTituloField().getText()) == null){
				return true;
			}else{
				igCadastrarFilme.getInforFilmeRepitido().setText("O Filme j� consta no seu acervo de filmes.");
				return false;
			}
		else{
			return false;
		}
	
	}
	
	//Valida o Nome do Pais.
	private boolean validaNomePais(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getPaisField().getText())){
			return true;
		}else{
			return false;
		}
	}
	
	//Valida se o usuario forneceu a dura��o correta.
	private boolean validaDuracao(){
		if(!Validador.validaCampoVazio(igCadastrarFilme.getDuracaoField().getText()))
			if(Validador.validaNumeroInteiro(igCadastrarFilme.getDuracaoField().getText())){
				return true;
			}else{
				return false;
			}
		else{
			return false;
		}
	}
	
	//Verifica se o usuario forneceu os Diretores.
	private boolean validaNomesDiretor(){
		if(igCadastrarFilme.getDiretorArea().getModel().getSize()>=1)
			return true;
		else return false;
	}
	
	//Verifica se o usuario forneceu os autores.
	private boolean validaNomesAutor(){
		if(igCadastrarFilme.getAutorArea().getModel().getSize()>=1)
			return true;
		else return false;
	}
	
	//Verifica se o usuario forneceu os atores.
	private boolean validaNomesAtor(){
		if(igCadastrarFilme.getAtorArea().getModel().getSize()>=1)
			return true;
		else return false;
	}
	
	private boolean validaCadastroEAvisaUsuario(boolean...bs){
		if(!bs[0])
			igCadastrarFilme.getTituloField().setBorder(new LineBorder(Color.RED));
		else
			igCadastrarFilme.getTituloField().setBorder(new LineBorder(Color.WHITE));
		if(!bs[1])
			igCadastrarFilme.getPaisField().setBorder(new LineBorder(Color.RED));
		else
			igCadastrarFilme.getPaisField().setBorder(new LineBorder(Color.WHITE));
		if(!bs[2])
			igCadastrarFilme.getDuracaoField().setBorder(new LineBorder(Color.RED));
		else
			igCadastrarFilme.getDuracaoField().setBorder(new LineBorder(Color.WHITE));
		if(!bs[3])
			igCadastrarFilme.getErroAtorlabel().setText("Voc� deve fornecer um ator.");
		else
			igCadastrarFilme.getErroAtorlabel().setText("");
		if(!bs[4])
			igCadastrarFilme.getErroAutorlabel().setText("Voc� deve fornecer um autor.");
		else
			igCadastrarFilme.getErroAutorlabel().setText("");
		if(!bs[5])
			igCadastrarFilme.getErroDiretorlabel().setText("Voc� deve fornecer um diretor.");
		else
			igCadastrarFilme.getErroDiretorlabel().setText("");
		return (bs[0] && bs[1] && bs[2] && bs[3] && bs[4] && bs[5]) ? true : false;
	}
	
	// Lembrar de Melhorar esse M�todo.
	private void cadastraFilme() {
		File poster = new File(igCadastrarFilme.getFotoField().getText());
		Filme filme = new Filme(0,Integer.parseInt(igCadastrarFilme.getDuracaoField().getText()),
				igCadastrarFilme.getjYearChooser().getValue(),
				(int)igCadastrarFilme.getClassificacaoIMDBspinner().getValue(), 
				(int)igCadastrarFilme.getCllassificacaoSpinner().getValue(), 
				igCadastrarFilme.getTituloField().getText(), 
				igCadastrarFilme.getSinopseEditorPane().getText(),
				igCadastrarFilme.getFaixaEtariaComboBox().getItemAt(igCadastrarFilme.getFaixaEtariaComboBox().getSelectedIndex()),
				tipoMidia(),
				igCadastrarFilme.getChooser().getCalendar(),
				poster);
		Artista artistas[] = obtemArtistas();
		Diretor diretores[] = obtemDiretores();
		Autor autores[] = obtemAutores();
		Genero genero = GeneroDAO.pesquisaGenero(igCadastrarFilme.getGeneroComboBox().getItemAt((
				igCadastrarFilme.getGeneroComboBox().getSelectedIndex())));
		if(genero == null)
			genero = new Genero(0, igCadastrarFilme.getGeneroComboBox().getItemAt((
				igCadastrarFilme.getGeneroComboBox().getSelectedIndex())));
		Pais pais = PaisDAO.pesquisaPais(igCadastrarFilme.getPaisField().getText());
		if(pais == null)
			pais = new Pais(0, igCadastrarFilme.getPaisField().getText());
		
		CadastroControle.cadastro(filme, artistas, diretores, autores, genero, pais);
	}
	
	//Retorna um Array de Objetos de Artistas.
	private Artista[] obtemArtistas(){
		String nomes[] = FuncaoAuxiliar.obtemPalavras(igCadastrarFilme.getAtorArea());
		LinkedList<Artista> artistas = new LinkedList<>();
		
		for(String nome : nomes)
			artistas.add(new Artista(0, nome));
		
		return artistas.toArray(new Artista[0]);
	}
	
	//Retorna um Array de Objetos de Autor.
	private Autor[] obtemAutores(){
		String nomes[] = FuncaoAuxiliar.obtemPalavras(igCadastrarFilme.getAutorArea());
		LinkedList<Autor> autores = new LinkedList<>();
		
		for(String nome : nomes)
			autores.add(new Autor(0, nome));
		
		return autores.toArray(new Autor[0]);
	}
	
	//Retorna um Array de Objetos de Diretores.
	private Diretor[] obtemDiretores(){
		String nomes[] = FuncaoAuxiliar.obtemPalavras(igCadastrarFilme.getDiretorArea());
		LinkedList<Diretor> diretores = new LinkedList<>();
		
		for(String nome : nomes)
			diretores.add(new Diretor(0, nome));
		
		return diretores.toArray(new Diretor[0]);
	}
	
	//Retorna o tipo de Midia selecionado Pelo Usuario.
	private String tipoMidia(){
		if(igCadastrarFilme.getDvdRadio().isSelected())
			return "DVD";
		else
			if(igCadastrarFilme.getBluRayRadio().isSelected())
				return "Blu-Ray";
			else
				return "Arquivo Digital";
	}
}
