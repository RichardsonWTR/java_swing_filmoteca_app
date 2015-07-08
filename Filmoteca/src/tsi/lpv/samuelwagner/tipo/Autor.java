package tsi.lpv.samuelwagner.tipo;

/**
 * A classe <code>Autor</code> possui as informa��es b�sicas dos autores do filme.
 * 
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class Autor extends Dados {
	/**
	 * Construtor default da classe <code>Autor</code>.
	 */
	public Autor() {
		super();
	}
	
	/**
	 * Construtor sobrecarregado da classe <code>Autor</code>.
	 * @param codigo <code>int</code> do Autor.
	 * @param nome  <code>String</code> nome do Autor.
	 */
	public Autor(int codigo, String nome) {
		super(codigo,nome);
	}
}//class Autor 
