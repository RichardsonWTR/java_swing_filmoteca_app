package tsi.lpv.samuelwagner.controller;

import tsi.lpv.samuelwagner.persistencia.ArtistaDAO;
import tsi.lpv.samuelwagner.persistencia.AutorDAO;
import tsi.lpv.samuelwagner.persistencia.AutorFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.DiretorDAO;
import tsi.lpv.samuelwagner.persistencia.DiretorFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.ElencoDAO;
import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.PaisDAO;
import tsi.lpv.samuelwagner.persistencia.PaisFilmeDAO;
import tsi.lpv.samuelwagner.tipo.Artista;
import tsi.lpv.samuelwagner.tipo.Autor;
import tsi.lpv.samuelwagner.tipo.Diretor;
import tsi.lpv.samuelwagner.tipo.Filme;
import tsi.lpv.samuelwagner.tipo.Genero;
import tsi.lpv.samuelwagner.tipo.Pais;

/**Classe Resposavel por fazer a ponte entre a interface e o Banco de Dados.
 * @author Samuel
 * @author Wagner
 */
public class CadastroControle {
	
	/**M�todo que Cadastra os Dados do filme ao Banco de Dados.
	 * @param filme <code>Filme</code> contendo os dados do filme.
	 * @param artistas <code>Artita</code> array contendo os artista do filme.
	 * @param diretores <code>Diretor</code> array contendo os diretores do filme.
	 * @param autores <code>Autor</code> array contendo os autores do filme.
	 * @param genero <code>Genero</code> do filme.
	 * @param pais <code>Pais</code> do filme.
	 */
	public static void cadastro(Filme filme, Artista[] artistas,Diretor[] diretores, Autor[] autores, Genero genero,Pais pais) {
		int codigoFilme,codigoArtista,codigoDiretor,codigoAutor,codigoGenero,codigoPais;
		
		//Salva o filme e Obtem o codigo do filme.
		codigoFilme = cadastraFilme(filme);
		//Percorre o array de artista
		for(Artista artista : artistas){
			/*Verifica se o artista j� esta cadastrado, caso 
			 * Cadastrado obtem o codigo dele e salva na Tabela Elenco, se
			 * n�o salva no banco e obtem o arquivo e salva no elenco.
			 */
			Artista igual = ArtistaDAO.pesquisaArtista(artista.getNome());
			if(igual == null)
				codigoArtista = cadastraArtista(artista);
			else
				codigoArtista = igual.getCodigo();
			cadastraEleco(codigoFilme, codigoArtista);
		}
		
		//Percorre o array de diretores
		for(Diretor diretor : diretores){
			/*Verifica se o diretor j� esta cadastrado, caso 
			 * Cadastrado obtem o codigo dele e salva na Tabela diretorfilme, se
			 * n�o salva no banco e obtem o arquivo e salva no diretorfilme.
			 */
			Diretor igual = DiretorDAO.pesquisaDiretor(diretor.getNome());
			if(igual == null)
				codigoDiretor = cadastraDiretor(diretor);
			else
				codigoDiretor = igual.getCodigo();
			cadastraDiretorFilme(codigoFilme, codigoDiretor);
		}
		
		//Percorre o array de autores.
		for(Autor autor : autores){
			/*Verifica se o autor j� esta cadastrado, caso 
			 * Cadastrado obtem o codigo dele e salva na Tabela autorfilme, se
			 * n�o salva no banco e obtem o arquivo e salva no autorfilme.
			 */
			Autor igual = AutorDAO.pesquisaAutor(autor.getNome());
			if(igual == null)
				codigoAutor = cadastraAutor(autor);
			else
				codigoAutor = igual.getCodigo();
			cadastraAutorFilme(codigoFilme, codigoAutor);
		}
		
		//Verifica se o genero j� est� cadastrado.
		if(genero.getCodigo() == 0)
			codigoGenero = cadastraGenero(genero);
		else
			codigoGenero = genero.getCodigo();
		
		//Verifica se o genero j� est� cadastrado.
		if(pais.getCodigo() == 0)
			codigoPais = cadastraPais(pais);
		else
			codigoPais = pais.getCodigo();
		
		//Associa o genero e o pais ao filme.
		cadastraPaisFilme(codigoFilme, codigoPais);
		cadastraGeneroFilme(codigoFilme, codigoGenero);
	}
	
	//Grava o filme no Banco e retorna seu codigo.
	private static int cadastraFilme(Filme filme) {
		FilmeDAO.cadastrarFilme(filme);
		return FilmeDAO.obterUltimoCodigo();
	}
	
	//Grava o artista no Banco e retorna seu codigo.
	private static int cadastraArtista(Artista artista) {
		ArtistaDAO.cadastraArtista(artista);
		return ArtistaDAO.obterUltimoCodigo();
	}
	
	//Grava o diretor no Banco e retorna seu codigo.
	private static int cadastraDiretor(Diretor diretor) {
		DiretorDAO.cadastraDiretor(diretor);
		return DiretorDAO.obterUltimoCodigo();
	}
	
	//Grava o autor no Banco e retorna seu codigo.
	private static int cadastraAutor(Autor autor) {
		AutorDAO.cadastraAutor(autor);
		return AutorDAO.obterUltimoCodigo();
	}
	
	//Grava o genero no Banco e retorna seu codigo.
	private static int cadastraGenero(Genero genero) {
		GeneroDAO.cadastrarGenero(genero);
		return GeneroDAO.obterUltimoCodigo();
	}
	
	//Grava o pais no Banco e retorna seu codigo.
	private static int cadastraPais(Pais pais) {
		PaisDAO.cadastraPais(pais);
		return PaisDAO.obterUltimoCodigo();
	}
	
	//Cria rela��o entre o filme e o artista.
	private static void cadastraEleco(int codigoFilme, int codigoArtista){
		ElencoDAO.cadastraElenco(codigoFilme, codigoArtista);
	}
	
	//Cria rela��o entre o filme e o diretor.
	private static void cadastraDiretorFilme(int codigoFilme, int codigoDiretor){
		DiretorFilmeDAO.cadastrarDiretorFilme(codigoFilme, codigoDiretor);
	}
	
	//Cria rela��o entre o filme e o pais.
	private static void cadastraPaisFilme(int codigoFilme, int codigoPais){
		PaisFilmeDAO.cadastrarPaisFilme(codigoFilme, codigoPais);
	}
	
	//Cria rela��o entre o filme e o genero.
	private static void cadastraGeneroFilme(int codigoFilme, int codigoGenero){
		GeneroFilmeDAO.cadastraGeneroFilme(codigoFilme, codigoGenero);
	}
	
	//Cria rela��o entre o filme e o autor.
	private static void cadastraAutorFilme(int codigoFilme, int codigoAutor){
		AutorFilmeDAO.cadastrarAutorFilme(codigoFilme, codigoAutor);
	}
	
	public static void cadastraAutorAtorDiretor(Filme filme, Diretor[] diretores, Autor[] autores, Artista[] artistas ){
		int codigoArtista, codigoDiretor, codigoAutor, codigoFilme = filme.getCodigo();	
		//Percorre o array de artista
		for(Artista artista : artistas){
			/*Verifica se o artista j� esta cadastrado, caso 
			 * Cadastrado obtem o codigo dele e salva na Tabela Elenco, se
			 * n�o salva no banco e obtem o arquivo e salva no elenco.
			 */
			Artista igual = ArtistaDAO.pesquisaArtista(artista.getNome());
			if(igual == null)
				codigoArtista = cadastraArtista(artista);
			else
				codigoArtista = igual.getCodigo();
			cadastraEleco(codigoFilme, codigoArtista);
		}
		
		//Percorre o array de diretores
		for(Diretor diretor : diretores){
			/*Verifica se o diretor j� esta cadastrado, caso 
			 * Cadastrado obtem o codigo dele e salva na Tabela diretorfilme, se
			 * n�o salva no banco e obtem o arquivo e salva no diretorfilme.
			 */
			Diretor igual = DiretorDAO.pesquisaDiretor(diretor.getNome());
			if(igual == null)
				codigoDiretor = cadastraDiretor(diretor);
			else
				codigoDiretor = igual.getCodigo();
			cadastraDiretorFilme(codigoFilme, codigoDiretor);
		}
		
		//Percorre o array de autores.
		for(Autor autor : autores){
			/*Verifica se o autor j� esta cadastrado, caso 
			 * Cadastrado obtem o codigo dele e salva na Tabela autorfilme, se
			 * n�o salva no banco e obtem o arquivo e salva no autorfilme.
			 */
			Autor igual = AutorDAO.pesquisaAutor(autor.getNome());
			if(igual == null)
				codigoAutor = cadastraAutor(autor);
			else
				codigoAutor = igual.getCodigo();
			cadastraAutorFilme(codigoFilme, codigoAutor);
		}
	}
}
