package tsi.lpv.filmoteca.trataeventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import tsi.lpv.filmoteca.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.filmoteca.gui.IgCadastraGenero;
import tsi.lpv.filmoteca.gui.IgCadastrarFilme;
import tsi.lpv.filmoteca.modelo.Genero;
import tsi.lpv.filmoteca.persistencia.GeneroDAO;

/**Classe Respons�vel por tratar do Eventos da Classe <code>TratadorEventoCadastraGenero</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class TratadorEventoCadastraGenero implements ActionListener{
	private IgCadastrarFilme igCadastrarFilme;
	private IgCadastraGenero igCadastraGenero;
	
	/**Construtor Sobregarregado.
	 * @param igCadastrarFilme <code>IgCadastraFilme</code>.
	 * @param igCadastraGenero <code>IgCadastraGenero</code>.
	 */
	public TratadorEventoCadastraGenero(IgCadastrarFilme igCadastrarFilme,IgCadastraGenero igCadastraGenero) {
		this.igCadastrarFilme = igCadastrarFilme;
		this.igCadastraGenero = igCadastraGenero;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == igCadastraGenero.getAdicionarButton())
			cadastraGenero();
		else
			igCadastraGenero.dispose();
	}
	
	private void cadastraGenero(){
		if(!igCadastraGenero.getAreaDescricao().getText().equals("")){
			if(GeneroDAO.pesquisaGenero(igCadastraGenero.getAreaDescricao().getText()) == null){
				GeneroDAO.cadastrarGenero(new Genero(0, igCadastraGenero.getAreaDescricao().getText()));
				FuncaoAuxiliar.exibirMensagem(igCadastraGenero, "Genero Cadastrado.", "Cadastra Genero.");
				igCadastrarFilme.getGeneroComboBox().setModel(new DefaultComboBoxModel<String>(GeneroDAO.obtemNomesGenero()));
				igCadastraGenero.dispose();
				return;
			}
			FuncaoAuxiliar.exibirMensagemErro(igCadastraGenero, "Genero j� Cadastrado.", "Cadastra Genero.");
			return;
		}
		FuncaoAuxiliar.exibirMensagemErro(igCadastraGenero, "Campo Descri��o N�o Pode ser Vazio.", "Cadastra Genero.");
	}
}
