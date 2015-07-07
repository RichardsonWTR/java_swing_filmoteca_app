package tsi.lpv.samuelwagner.app;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tsi.lpv.samuelwagner.gui.IgEsperaInicial;
import tsi.lpv.samuelwagner.gui.IgFilmoteca;
import tsi.lpv.samuelwagner.persistencia.ConnectionFactory;

/**Classe <code>FilmotecaApp</code> responsavel por inicar a execu��o do Aplicativo Darth Movie.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class FilmotecaApp {

	/**
	 * M�todo main, respons�vel pela inicializa��o do aplicativo de Darth Movie.
	 * @param args <code>String</code> argumentos.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		IgFilmoteca filmoteca = new IgFilmoteca();
		new ThreadIniciaAplicativo(new IgEsperaInicial(),filmoteca).start();
		
		//Encerra a conex�o com o banco de dados.
		ConnectionFactory.closeConnection();
	}//testa

	//Classe Responsavel por Gerar o pequeno Atraso para abrir a conex�o com o banco.
	private static class ThreadIniciaAplicativo extends Thread{
		private IgEsperaInicial igEspera;
		private IgFilmoteca igFilmoteca;
		
		//Construtor default da classe ThreadIniciaAplicativo
		private ThreadIniciaAplicativo(IgEsperaInicial igEspera, IgFilmoteca igFilmoteca) {
			this.igEspera = igEspera;
			this.igFilmoteca = igFilmoteca;
		}

		@Override
		public void run() {
			//Loop para fazer o ProgressBar avan�ar.
			for(int i = 0; i<= 100; i++){
				try {
					;;//Seta o valor do ProgressBar
					igEspera.getProgressBar().setValue(i);
					//Provaca o Atraso.
					sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Fecha a Janela igEspera
			igEspera.dispose();
			//Ativa a janela IgFilmoteca.
			igFilmoteca.setVisible(true);
		}
		
	}
	
}
