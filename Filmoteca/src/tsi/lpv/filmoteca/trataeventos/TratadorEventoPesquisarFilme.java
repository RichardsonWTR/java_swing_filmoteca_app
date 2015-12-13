package tsi.lpv.filmoteca.trataeventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import tsi.lpv.filmoteca.controller.PesquisaControle;
import tsi.lpv.filmoteca.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.filmoteca.gui.IgFilmoteca;
import tsi.lpv.filmoteca.gui.IgMensagem;
import tsi.lpv.filmoteca.gui.IgResultadoPesquisaFilme;
import tsi.lpv.filmoteca.modelo.Filme;
import tsi.lpv.filmoteca.persistencia.FilmeDAO;
import tsi.lpv.filmoteca.persistencia.PaisDAO;
import tsi.lpv.filmoteca.persistencia.PaisFilmeDAO;

/** Classe <code>TratadorEventoPesquisarFilme</code> respons�vel por tratar os eventos 
 *  da classe <code>IgFilmoteca</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida 
 */
public class TratadorEventoPesquisarFilme implements ActionListener {
	private IgFilmoteca igFilmoteca;
	private IgResultadoPesquisaFilme igResultadoPesquisaFilme;
	private String nomeFilme;
	
	/**Construtor Sobrecarregado da classe <code>TratadorEventoPesquisarFilme</code>.
	 * @param igFilmoteca <code>IgFilmoteca</code> referencia da Classe que relializou a chamada ao m�todo.
	 * @param nomeFilme <code>String</code> com o nome do filme.
	 */
	public TratadorEventoPesquisarFilme(IgFilmoteca igFilmoteca, String nomeFilme) {
		this.igFilmoteca = igFilmoteca;
		this.nomeFilme = nomeFilme;
	}

	//Tratador de eventos do clique do mouse.
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("eee");
		pesquisarFilme();
	}
	
	/**
	 * Pesquisa as informa��es do filme no banco, caso encontrado, carrega os dados para o painel de resultado 
	 * da pesquisa e retorna o resultado para o usu�rio.
	 */
	public void pesquisarFilme(){		
		//Verifica se o nome � vazio, caso seja, retorna cancela a pesquisa.
		if(nomeFilme == null || nomeFilme.equals("")){
			new IgMensagem(igFilmoteca, "Digite o nome do filme para pesquisar.");
			return;
		}
		
		//Pesquisa o filme no banco de dados.
		Filme filme = FilmeDAO.pesquisarFilme(nomeFilme);
		if(filme != null) exibeDadosFilme(filme);
		else new IgMensagem(igFilmoteca, "Filme n�o encontrado.");
	}//pesquisarFilme()
	
	/**
	 * Exibe todos os dados do filme cadastrado.
	 * @param filme <code>Filme</code> objeto com o filme a ser exibido.
	 */
	public void exibeDadosFilme(Filme filme){
		igResultadoPesquisaFilme = new IgResultadoPesquisaFilme(igFilmoteca, filme);
		
		//Configura o nome do filme na tela.
		igResultadoPesquisaFilme.getNomeFilme().setText(filme.getTitulo());
		
		//Configura o pa�s.
		int codPais = PaisFilmeDAO.obterPaisFilme(filme.getCodigo());
		if(codPais != 0) igResultadoPesquisaFilme.getPaisLabel().setText("Pa�s: " + PaisDAO.pesquisaPais(codPais).getNome());
		
		//Configura o ano.
		igResultadoPesquisaFilme.getAnoLabel().setText("Ano: " + Integer.toString(filme.getAno()));
		
		//Configura a dura��o.
		igResultadoPesquisaFilme.getDuracaoLabel().setText("Dura��o: " + Integer.toString(filme.getDuracao()) + " minutos");
		
		//Configura a classifica��o et�ria.
		igResultadoPesquisaFilme.getEtariaLabel().setText("Classifica��o et�ria: " + filme.getClassificacaoEtaria());
		
		//Configura o g�nero.
		String genero = PesquisaControle.obterGeneros(filme.getCodigo());
		if(genero != null && !genero.equals(""))igResultadoPesquisaFilme.getGeneroLabel().setText("G�nero: " + genero.substring(0, genero.length() - 2));
		
		//Configura a sinopse
		igResultadoPesquisaFilme.getSinopseTextArea().setText(filme.getSinopse());
		
		//Configura a m�dia
		igResultadoPesquisaFilme.getMidiaTextField().setText(filme.getMidia());
		
		//Configura a classifica��o IMDB e pessoal.
		igResultadoPesquisaFilme.getImdbTextField().setText(Integer.toString(filme.getClassificacaoIMDB()) + " estrelas");
		igResultadoPesquisaFilme.getPessoalTextField().setText(Integer.toString(filme.getClassificacaoPessoal()) + " estrelas");
		
		//Configura autor, diretor e elenco.
		String elenco = PesquisaControle.obterElenco(filme.getCodigo());
		if(elenco != null && !elenco.equals("")) igResultadoPesquisaFilme.getElencoTextArea().setText(elenco.substring(0, elenco.length() - 2));
		
		String autor = PesquisaControle.obterAutores(filme.getCodigo());
		if(autor != null && !autor.equals(""))igResultadoPesquisaFilme.getAutorTextField().setText(autor.substring(0, autor.length() - 2));
		
		String diretor = PesquisaControle.obterDiretores(filme.getCodigo());
		if(diretor != null && !diretor.equals("")) igResultadoPesquisaFilme.getDiretorTextField().setText(diretor.substring(0, diretor.length() - 2));
		
		//Configura a data
		igResultadoPesquisaFilme.getLancamentoTextField().setText(FuncaoAuxiliar.coverteDataParaString(filme.getDataLancamento(), true));
		
		//Configura a foto
		if(filme.getPoster() != null){
			ImageIcon icon = new ImageIcon(filme.getPoster());
			igResultadoPesquisaFilme.getFotoLabel().setIcon(icon);
		}
		
		//Exibe a tela para o usu�rio.
		igResultadoPesquisaFilme.setVisible(true);
	}//exibeDadosFilme()
	
}//class TratadorEventoPesquisarFilme