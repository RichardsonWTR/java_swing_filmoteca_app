package tsi.lpv.samuelwagner.tipo;
/**
 * A classe abstrata <code>Dados</code> possui os atributos e m�todos que s�o comuns as outras classes
 * da filmoteca. Ela � definida para que as demais opera��es do aplicativo sejam executadas de forma
 * polim�rfica.
 * 
 * @author Samuel
 * @author Wagner
 */
public /*abstract*/ class Dados {
	private int codigo;
	private String nome;
	
	/**
	 * Construtor default da classe abstrata dados.
	 */
	public Dados() {}
	
	/**
	 * Obt�m um <code>int</code> contendo o c�digo inserido.
	 * @return codigo <code>int</code>.
	 */
	public int getCodigo() {
		return codigo;
	}//getCodigo()
	
	/**
	 * Atribui um o c�digo.
	 * @param codigo <code>int</code> com o c�digo.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}//setCodigo()
	
	/**
	 * Obt�m um <code>String</code> com o nome.
	 * @return nome <code>String</code>
	 */
	public String getNome() {
		return nome;
	}//getNome()
	
	/**
	 * Atribui o nome.
	 * @param nome <code>String</code> com o nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}//setNome()
}//abstract class Dados
