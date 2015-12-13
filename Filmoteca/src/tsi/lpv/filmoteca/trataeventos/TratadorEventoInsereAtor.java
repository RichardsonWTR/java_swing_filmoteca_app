package tsi.lpv.filmoteca.trataeventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

import tsi.lpv.filmoteca.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.filmoteca.funcaoauxiliar.Validador;
import tsi.lpv.filmoteca.gui.IgCadastrarFilme;

/**  Classe <code>TratadorEventoInsereAtor</code> responsavel por tratar os eventos 
 *  da classe <code>IgCadastrarFilme</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public class TratadorEventoInsereAtor implements ActionListener {
private IgCadastrarFilme igCadastrarFilme;
	
	/**Construtor sobrecarregado da classe <code>TratadorEventoInsereAtor</code>.
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
			if(igCadastrarFilme.getAtorArea().getModel().getSize() >= 1)
				//Verifica se o ator se encontra caso encontra informa ao usuario que ele est� la.
				if(Validador.procuraIgualdede(igCadastrarFilme.getAtorField().getText(), FuncaoAuxiliar.obtemPalavras(
						igCadastrarFilme.getAtorArea()))){
					FuncaoAuxiliar.exibirMensagemErro(igCadastrarFilme, "Ator j� Informado.", "Cadastra Filme.");
					igCadastrarFilme.getAtorField().setText("");
					return;
				}
			//Adiciona o diretor ao atorArea.
			try{
				DefaultListModel<String> listModel = (DefaultListModel<String>)igCadastrarFilme.getAtorArea().getModel();
				listModel.addElement(igCadastrarFilme.getAtorField().getText());
				igCadastrarFilme.getAtorField().setText("");
			}catch(ClassCastException e){
				e.printStackTrace();
			}
		}
	}
	
	//Limpa o ultimo Ator cadastrado.
		private void limparAtor() {
			if(igCadastrarFilme.getAtorArea().getModel().getSize() >= 1){
				try{
					DefaultListModel<String> listModel = (DefaultListModel<String>)igCadastrarFilme.getAtorArea().getModel();
					listModel.remove(listModel.getSize() - 1);
				}catch(ClassCastException e){
					e.printStackTrace();
				}
			}
		}

}
