package tsi.lpv.samuelwagner.tipo;

/**
 * A classe <code>Genero</code> possui as informa��es b�sicas do g�nero do filme.
 * 
 * @author Samuel
 * @author Wagner
 */
public class Genero extends Dados {
	/**
	 * Construtor default da classe <code>Genero</code>.
	 */
	public Genero() {
		super();
	}
	
	/**
	 * Construtor sobrecarregado da classe <code>Genero</code>.
	 */
	public Genero(int codigo, String nome) {
		super(codigo,nome);
	}
}//class Genero
