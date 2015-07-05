package tsi.lpv.samuelwagner.funcaoauxiliar;

/** Essa Classe e reponsavel implementar m�todos que valida dados do programa.
 * @author Samuel e Wagner
 *
 */
public class Validador {
	
	/**
	 * Verifica se a <code>String</code> passada e um n�mero inteiro positivo.
	 * @param numero <code>String</code> que ser� usada para a valida��o.
	 * @return Retorna true <code>boolean</code> para n�mero inteiro e false <code>boolean</code>
	 * para outro tipo de caracter.
	 */
	public static boolean validaNumeroInteiro(String numero) {
		try {
			if(Integer.parseInt(numero) <= 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se a <code>String</code> passada e um n�mero real Positivo.
	 * @param numero <code>String</code> que ser� usada para a valida��o.
	 * @return Retorna true <code>boolean</code> para n�mero real e false <code>boolean</code>
	 * para outro tipo de caracter.
	 */
	public static boolean validaNumeroReal(String numero) {
		try {
			if(Double.parseDouble(numero) <= 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se a <code>String</code> passada esta vazia.
	 * @param texto <code>String</code> que ser� usada para a valida��o.
	 * @return Retorna true <code>boolean</code> para <code>String</code> vazia false <code>boolean</code>
	 * caso contrario.
	 */
	public static boolean validaCampoVazio(String texto) {
		if(texto.equals(""))
			return true;
		return false;
	}
	
	public static boolean procuraIgualdede(String texto, String[] textos){
		for(String palavra : textos)
			if(FuncaoAuxiliar.comparaString(texto, palavra))
				return true;
		return false;
	}
}
