package tsi.lpv.samuelwagner.tipo;

import java.io.File;
import java.util.Calendar;

/**
 * A classe <code>Filme</code> possui os atributos necess�rio para armazenar as informa��es b�sicas dos
 * filmes cadastrados no aplicativo Filmoteca.
 * 
 * @author Samuel
 * @author Wagner
 */
public class Filme {
	private int codigo,
				duracao,
				ano,
				classificacaoIMDB,
				classificacaoPessoal;
	private String titulo,
				   sinopse,
				   classificacaoEtaria,
				   midia;
	private Calendar dataLancamento;
	private File poster;
	
	/**
	 * Construtor default da classe Filme.
	 */
	public Filme() {}
	
	/**
	 * Obt�m um <code>int</code> contendo o c�digo do filme.
	 * @return codigo<code>int</code>
	 */
	public int getCodigo() {
		return codigo;
	}//getCodigo()

	/**
	 * Atribui o c�digo do filme.
	 * @param codigo <code>int</code> com o c�digo do filme.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}//setCodigo()

	/**
	 * Obt�m um <code>int</code> contento a dura��o do filme em minutos.
	 * @return duracao <code>int</code>
	 */
	public int getDuracao() {
		return duracao;
	}//getDuracao()
	
	/**
	 * Atribui a dura��o do filme.
	 * @param duracao <code>int</code> com a dura��o do filme em minutos.
	 */
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}//setCodigo()
	
	/**
	 * Obt�m um <code>int</code> com o ano do filme.
	 * @return <code>int</code>
	 */
	public int getAno() {
		return ano;
	}//getAno()

	/**
	 * Atribui o ano do filme.
	 * @param ano <code>int</code> com o ano do filme.
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}//setAno()

	/**
	 * Obt�m a classifica��o do filme segundo o IMDB.
	 * @return <code>int</code>
	 */
	public int getClassificacaoIMDB() {
		return classificacaoIMDB;
	}//getClassificacao()
	
	/**
	 * Atribui a classifica��o <code>int</code> segundo o IMDB.
	 * @param classificacaoIMDB
	 */
	public void setClassificacaoIMDB(int classificacaoIMDB) {
		this.classificacaoIMDB = classificacaoIMDB;
	}//setClassificacaoIMDB()
	
	/**
	 * Obt�m a classifica��o pessoal do filme.
	 * @return <code>int</code>.
	 */
	public int getClassificacaoPessoal() {
		return classificacaoPessoal;
	}//getClassificacaoPessoal()
	
	/**
	 * Atribui a classificacao pessoal ao filme.
	 * @param classificacaoPessoal <code>int</code> com a classificacao pessoal.
	 */
	public void setClassificacaoPessoal(int classificacaoPessoal) {
		this.classificacaoPessoal = classificacaoPessoal;
	}//setClassificacaoPessoal()
	
	/**
	 * Obt�m o t�tulo do filme.
	 * @return <code>String</code>.
	 */
	public String getTitulo() {
		return titulo;
	}//getTitulo()

	/**
	 * Atribui o t�tulo do filme.
	 * @param titulo <code>String</code> com o t�tulo do filme.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}//setTitulo()
	
	/**
	 * Obt�m a sinopse do filme.
	 * @return <code>String</code>.
	 */
	public String getSinopse() {
		return sinopse;
	}//getSinopse()
	
	/**
	 * Atribui a sinopse do filme.
	 * @param sinopse <code>String</code> com a sinopse do filme.
	 */
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}//setSinopse()
	
	/**
	 * Obt�m a classifica��o et�ria do filme.
	 * @return <code>String</code>
	 */
	public String getClassificacaoEtaria() {
		return classificacaoEtaria;
	}//getClassificacaoEtaria()
	
	/**
	 * Atribui a classifica��o et�ria do filme.
	 * @param classificacaoEtaria <code>String</code>.
	 */
	public void setClassificacaoEtaria(String classificacaoEtaria) {
		this.classificacaoEtaria = classificacaoEtaria;
	}//setClassificacaoEtaria()
	
	/**
	 * Obt�m a m�dia utilizada para armazenar o filme.
	 * @return <code>String</code>.
	 */
	public String getMidia() {
		return midia;
	}//getMidia()
	
	/**
	 * Atribui a m�dia utilizada para armazenar o filme.
	 * @param midia <code>String</code> com a tipo de m�dia utilizada para armazenar o filme.
	 */
	public void setMidia(String midia) {
		this.midia = midia;
	}//setMidia()
	
	/**
	 * Obt�m a data de lan�amento do filme.
	 * @return
	 */
	public Calendar getDataLancamento() {
		return dataLancamento;
	}//getDataLancamento()
	
	/**
	 * Atribui a data de lan�amento do filme.
	 * @param dataLancamento <code>Calendar</code> com a data de lan�amento do filme.
	 */
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}//setDataLancamento()
	
	/**
	 * Obt�m a imagem usada como p�ster do filme.
	 * @return <code>File</code>
	 */
	public File getPoster() {
		return poster;
	}//getPoster()
	
	/**
	 * Atribui a imagem a ser utilizada como p�ster do filme.
	 * @param poster
	 */
	public void setPoster(File poster) {
		this.poster = poster;
	}//setPoster()
}//class Filme
