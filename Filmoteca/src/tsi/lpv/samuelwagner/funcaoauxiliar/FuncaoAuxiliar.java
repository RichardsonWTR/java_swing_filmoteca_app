package tsi.lpv.samuelwagner.funcaoauxiliar;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Component;
import java.text.Collator;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JList;
/**
 * A classe <code>FuncaoAuxiliar</code>
 * @author Samuel
 * @author Wagner
 */
public class FuncaoAuxiliar {
	private static String nomeMeses[] = {"Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho","Julho",
			"Agosto","Setembro","Outubro","Novembro","Desembro"};
	
	/** Converte um <code>Calendar</code> para <code>String</code>.
	 * @param data <code>Calendar</code>.
	 * @param completa <code>boolean</code>.
	 * @return um <code>String</code> formatada.
	 */
	public static String coverteDataParaString(Calendar data, boolean completa) {
		if(completa)
			return String.format("%02d/%02d/%04d", data.get(Calendar.DAY_OF_MONTH),
												   data.get(Calendar.MONTH)+1,
												   data.get(Calendar.YEAR));
		else
			return String.format("%02d/%04d", data.get(Calendar.MONTH)+1,
					   						  data.get(Calendar.YEAR));

	}//converteDataParaString()
	
	/**
	 * Converte a uma data em <code>String</code> para <code>Calendar</code>.
	 * @param data <code>String</code> com a data a ser convertida.
	 * @return um <code>Calendar</code> com a data ja convertida.
	 */
	public static Calendar converteDataParaCalendar(String data) {
		Calendar dataPesquisa = Calendar.getInstance();
		String aux[] = data.split(" ");
		
		int mes;
		//Obt�m o indice da data.
		for(mes = 0; mes < nomeMeses.length; mes++) if(aux[0].equalsIgnoreCase(nomeMeses[mes])) break;		
		dataPesquisa.set(Integer.parseInt(aux[1]), mes, Calendar.DAY_OF_MONTH);
		
		return dataPesquisa;
	}
	
	/**Cria uma mensagem para o usuario.
	 * @param componente <code>Component</code>, localidade de exibi��o.
	 * @param mensagem <code>String</code>, 
	 * @param titulo <code>String</code> da mensagem.
	 */
	public static void exibirMensagem(Component componente, String mensagem, String titulo) {
		showMessageDialog(componente, mensagem, titulo, INFORMATION_MESSAGE);
	}//exibirMensagem
	
	/**Cria uma mensagem de Erro para o usuario.
	 * @param componente <code>Component</code>, localidade de exibi��o.
	 * @param mensagem <code>String</code>, 
	 * @param titulo <code>String</code> da mensagem.
	 */
	public static void exibirMensagemErro(Component componente, String mensagem, String titulo) {
		showMessageDialog(componente, mensagem, titulo, ERROR_MESSAGE);
	}//exibirMensagemErro
	
	/**
	 * Compara duas String e verifica se s�o iguais. Caso seja retorna true. Ignora acentua��es.
	 * @param textoA <code>String</code> a ser comparada.
	 * @param textoB <code>String</code> a ser comparada.
	 * @return um <code>true</code> caso sejam iguais ou um <code>false</code> caso sejam diferentes.
	 */
	public static boolean comparaString(String textoA, String textoB) {
		 Collator collator = Collator.getInstance (new Locale("pt", "BR"));  
		 collator.setStrength(Collator.PRIMARY); // importante!  
		 return (collator.compare(textoA,textoB))== 0 ? true : false;
	}//comparaString()
	
	/**Obt�m palavras de um texto.
	 * @param texto <code>String</code> a ser obtidos as palavras.
	 * @return um array de <code>String</code> com as palavras.
	 */
	public static String[] obtemPalavras(JList<String> texto) {
		String nomes[] = new String[texto.getModel().getSize()];
		
		for(int indice = 0; indice < texto.getModel().getSize(); indice++)
			nomes[indice] =  texto.getModel().getElementAt(indice);
		return nomes;
	}
	
	/**Junta um n�mero de palavras de um array de <code>String</code>.
	 * @param textos <code>String</code> array a ser unido.
	 * @param numero <code>int</code> o n�mero de palavras a ser unidas.
	 * @return um <code>String</code> com as palavras unidas.
	 */
	public static String juntaPalavra(String[] textos, int numero) {
		String texto = "";
		for(int indice = 0; indice < numero;indice++)
			texto += textos[indice] + "\n";
		return texto;
	}
}//class FuncaoAuxiliar
