package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.funcaoauxiliar.MetodosConversaoBanco;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

/** Classe <code>TratadorEventoInserirImagem</code> responsavel por tratar os eventos 
 *  da classe <code>IgCadastrarFilme</code>.
 * @author Samuel
 * @author Wanger
 */
public class TratadorEventoInserirImagem implements ActionListener {
	private IgCadastrarFilme igCadastraFilme;
	
	/**Construtor Sobrecarregado da classe <code>TratadorEventoInsereAtor</code>.
	 * @param igCadastrarFilme referencia da Classe <code>IgCadastrarFilme</code>.
	 */
	public TratadorEventoInserirImagem(IgCadastrarFilme igCadastrarFilme) {
		this.igCadastraFilme = igCadastrarFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(igCadastraFilme.getInserirFotoButton() == event.getSource())
			obtemCaminhoFoto();
		else
			if(igCadastraFilme.getLimparFotoButton() == event.getSource()){
				igCadastraFilme.getPosterLabel().removeAll();
				igCadastraFilme.getFotoField().setText("");
				igCadastraFilme.getPosterLabel().revalidate();
				igCadastraFilme.getPosterLabel().repaint();
			}
				
	}
	
	private void obtemCaminhoFoto(){
		JFileChooser dialogoAbrir = new JFileChooser();
		  
		  /*
		   * Define as extens�es que ser�o usadas pelo JFileChooser para filtrar os tipos de arquivos 
		   * que ser�o exibidos na janela.
		   */
		  FileNameExtensionFilter extensaoFoto = new FileNameExtensionFilter("Arquivo (*.png)", "png","jpg");
		  dialogoAbrir.setFileFilter(extensaoFoto);
		  
		  // Indica que o usu�rio poder� selecionar apenas nomes de arquivos. 
		  dialogoAbrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		  // Define o t�tulo do di�logo. 
		  dialogoAbrir.setDialogTitle("Obter P�ster");
		
		  // Exibe o di�logo.
		  int opcao = dialogoAbrir.showOpenDialog(igCadastraFilme);
		
		  // Verifica se o usu�rio cancelou a opera��o (clicou no bot�o Cancelar).
		  if (opcao == JFileChooser.CANCEL_OPTION)
			  return;
		  else
			  // Obt�m o nome do arquivo selecionado pelo usu�rio no di�logo.
			  if (opcao == JFileChooser.APPROVE_OPTION){ 
				  String caminhoImagem = dialogoAbrir.getSelectedFile().getPath();
					 File file = new File(caminhoImagem);
					 
						byte[] fileNovo = MetodosConversaoBanco.preparaImagemParaBanco(MetodosConversaoBanco.redimensionarImagem(file,115, 195
								 ,obtemExtensao(file.getName())));
						 if(fileNovo != null){
							 ImageIcon icon = new ImageIcon(fileNovo);
							 igCadastraFilme.getPosterLabel().setIcon(icon);
							 igCadastraFilme.getFotoField().setText(file.getAbsolutePath());
							 igCadastraFilme.getPosterLabel().repaint();
						 }else{
							 FuncaoAuxiliar.exibirMensagemErro(igCadastraFilme, "Imagem N�o suportada.", "Inserir Imagem");
						 }
			}else 
				  return;
		} // dialogoAbrirArquivo()
	
	//Obtem a extens�o da Imagem.
	private String obtemExtensao(String nome){
		StringTokenizer tokenizer = new StringTokenizer(nome,".");
		tokenizer.nextToken();
		return "."+tokenizer.nextToken();
	}
}
