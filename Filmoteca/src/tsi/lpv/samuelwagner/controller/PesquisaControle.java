package tsi.lpv.samuelwagner.controller;

import java.util.Iterator;
import java.util.List;

import tsi.lpv.samuelwagner.persistencia.ArtistaDAO;
import tsi.lpv.samuelwagner.persistencia.AutorDAO;
import tsi.lpv.samuelwagner.persistencia.DiretorDAO;
import tsi.lpv.samuelwagner.persistencia.DiretorFilmeDAO;
import tsi.lpv.samuelwagner.persistencia.ElencoDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroDAO;
import tsi.lpv.samuelwagner.persistencia.GeneroFilmeDAO;

/**
 * A classe <code>PesquisaControle</code> possui os m�todos respons�veis por realizar as pesquisas nos
 * banco de dados.
 * @author Wagner Almeida
 * @author Samuel Gon�alves
 */
public class PesquisaControle {
	
	/**
	 * Obt�m uma representa�o de em <code>String</code> com todo o elenco do filme.
	 * @param codigoFilme <code>int</code> com o c�digo filme que se deseja obter o elento.
	 * @return <code>String</code> com o elenco cadastrado.
	 */
	public static String obterElenco(int codigoFilme){
		//Obt�m o elenco do filme
		List<Integer> elenco = ElencoDAO.pesquisElencoArtista(codigoFilme);
		
		if(elenco == null) return "";
		
		String atores = "";
		
		//Obt�m os nomes do elenco.
		Iterator<Integer> itElenco = elenco.iterator();
		while(itElenco.hasNext()){
			Integer codigo = itElenco.next();
			atores += ArtistaDAO.pesquisaArtista(codigo).getNome() + ", ";
		}
		return atores;
	}
	
	/**
	 * Obt�m uma representa�o de em <code>String</code> com todos os diretores do filme.
	 * @param codigoFilme <code>int</code> com o c�digo filme que se deseja obter os diretores.
	 * @return <code>String</code> com os diretores cadastrados.
	 */
	public static String obterDiretores(int codigoFilme){
		List<Integer> diretores = DiretorFilmeDAO.obterDiretorFilme(codigoFilme);
		
		if(diretores == null) return "";
		
		String diretor = "";
		
		Iterator<Integer> itDiretores = diretores.iterator();
		while(itDiretores.hasNext()){
			Integer codigo = itDiretores.next();
			diretor += DiretorDAO.pesquisaDiretor(codigo).getNome() + ", ";
		}
		return diretor;
	}//obterDiretores
	
	/**
	 * Obt�m uma representa�o de em <code>String</code> com todos os autores do filme.
	 * @param codigoFilme <code>int</code> com o c�digo filme que se deseja obter os autores.
	 * @return <code>String</code> com os autores cadastrados.
	 */
	public static String obterAutores(int codigoFilme){
		List<Integer> autores = DiretorFilmeDAO.obterDiretorFilme(codigoFilme);
		
		String autor = "";
		
		if(autores == null) return "";
		
		Iterator<Integer> itAutores = autores.iterator();
		while(itAutores.hasNext()){
			Integer codigo = itAutores.next();
			autor += AutorDAO.pesquisaAutor(codigo).getNome() + ", ";
		}
		return autor;
	}//obterAutores
	
	/**
	 * Obt�m uma representa�o de em <code>String</code> com os gen�ros do filme.
	 * @param codigoFilme <code>int</code> com o c�digo filme que se deseja obter os gen�ros.
	 * @return <code>String</code> com o g�nero cadastrado.
	 */
	public static String obterGeneros(int codigoFilme){
		List<Integer> genero = GeneroFilmeDAO.pesquisaGeneroFilme(codigoFilme);
		String generos = "";
		
		if(genero == null) return "";
		
		Iterator<Integer> itGenero = genero.iterator();
		while(itGenero.hasNext()){
			Integer codigo = itGenero.next();
			generos += GeneroDAO.pesquisaGenero(codigo).getNome() + ", ";
		}
		System.out.println(generos);
		return generos;
	}//obterGeneros
}//classPesquisaControle
