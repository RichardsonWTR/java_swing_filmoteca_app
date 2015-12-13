package tsi.lpv.filmoteca.modelo;

/**
 * A classe <code>Diretor</code> possui as informa��es b�sicas do diretor do filme.
 * 
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class Diretor extends Dados {
	
	/**
	 * Construtor default da classe <code>Diretor</code>.
	 */
	public Diretor() {
		super();
	}
	
	/**
	 * Construtor sobrecarregado da classe <code>Diretor</code>.
	 * @param codigo <code>int</code> do Diretor.
	 * @param nome  <code>String</code> nome do Diretor.
	 */
	public Diretor(int codigo, String nome) {
		super(codigo,nome);
	}
}//class Diretor
