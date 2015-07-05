package tsi.lpv.samuelwagner.tipo;

/**
 * A classe <code>Artista</code> possui as informa��es b�sicas do artista do elenco do filme.
 * 
 * @author Samuel
 * @author Wagner
 */
public class Artista extends Dados {
	
	/**
	 * Construtor default da classe <code>Artista</code>.
	 */
	public Artista() {
		super();
	}
	
	/**
	 * Construtor sobrecarregado da classe <code>Artista</code>.
	 */
	public Artista(int codigo, String nome) {
		super(codigo,nome);
	}
	
}//class Artista
