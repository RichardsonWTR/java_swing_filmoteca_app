package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.Validador;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

/**  Classe <code>TratadorEventoInsereAtor</code> responsavel por tratar os eventos 
 *  da classe <code>IgCadastrarFilme</code>.
 * @author Samuel 
 * @author Wagner
 *
 */
public class TratadorEventoInsereAtor implements ActionListener {
private IgCadastrarFilme igCadastrarFilme;
	
	/**Construtor Sobrecarregado da classe <code>TratadorEventoInsereAtor</code>.
	 * @param igCadastrarFilme referencia da Classe <code>IgCadastrarFilme</code>.
	 */
	public TratadorEventoInsereAtor(IgCadastrarFilme igCadastrarFilme) {
		this.igCadastrarFilme = igCadastrarFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//Verifica qual botao foi clicado pelo usuario.
		if(igCadastrarFilme.getInserirAtorButton() == event.getSource() || igCadastrarFilme.getAtorField() == event.getSource())
			insereAtor();
		else
			if(igCadastrarFilme.getLimparAtorButton() == event.getSource())
				limparAtor();
	}
	
	//Insere o autor a area de Texto quaso ele n�o esteja la.
	private void insereAtor() {
		/*Verifica se o campo atorField est� vazio caso esteja n�o ocorre nada, caso n�o 
		 * adiciona o ator caso ele n�o esteja na lista.
		 */
		if(!Validador.validaCampoVazio(igCadastrarFilme.getAtorField().getText())){
			//se o atorArea est� vazio quaso n�o esteja verifica se o ator se encotra l�.
			if(!igCadastrarFilme.getAtorArea().getText().equals(""))
				//Verifica se o ator se encontra caso encontra informa ao usuario que ele est� la.
				if(Validador.procuraIgualdede(igCadastrarFilme.getAtorField().getText(), FuncaoAuxiliar.obtemPalavras(
						igCadastrarFilme.getAtorArea().getText()))){
					FuncaoAuxiliar.exibirMensagemErro(igCadastrarFilme, "Ator j� Informado.", "Cadastra Filme.");
					igCadastrarFilme.getAtorField().setText("");
					return;
				}
			//Adiciona o diretor ao diretorArea.
			String texto = igCadastrarFilme.getAtorArea().getText();
			texto += igCadastrarFilme.getAtorField().getText() + "\n";
			igCadastrarFilme.getAtorArea().setText(texto);
			igCadastrarFilme.getAtorField().setText("");
		}
	}
	
	//Limpa o ultimo diretor cadastrado.
	private void limparAtor() {
		if(!igCadastrarFilme.getAtorArea().getText().equals("")){
			String[] texto = FuncaoAuxiliar.obtemPalavras(igCadastrarFilme.getAtorArea().getText());
			igCadastrarFilme.getAtorArea().setText(FuncaoAuxiliar.juntaPalavra(texto, texto.length-1));
		}
	}

}
