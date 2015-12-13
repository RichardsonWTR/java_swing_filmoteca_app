package tsi.lpv.filmoteca.funcaoauxiliar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
/**
 * A classe <code>MetodosConversaoBanco</code> possui os atributos e m�todos auxiliares respons�veis por
 * realizar as manipula��es das fun��es do banco de dados.
 * 
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public class MetodosConversaoBanco {
	private static final String DIR_TEMP = "tmp";
	
	/**
	 * Converte o objeto <code>File</code> recebido por par�metro para um array de bytes, para que seja
	 * salvo no banco de dados.
	 * @param file <code>File</code> com o arquivo a ser convetido.
	 * @return <code>byte[]</code> com os dados preparados para serem persistidos.
	 */
	public static byte[] preparaImagemParaBanco(File file){
		try {
			//Converte o objeto file em um array de bytes para poder ser inserido no banco.
			if(file.exists()){

				@SuppressWarnings("resource")
				InputStream imagem = new FileInputStream(file);
				byte[] bytes = new byte[(int)file.length()];
				int offset = 0, numRead = 0;
			
				while(offset < bytes.length && (numRead = imagem.read(bytes, offset, bytes.length - offset)) >= 0)
					offset += numRead;
			
				return bytes;
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}//preparaImagemParaBanco()
	
	/**
	 * Redimenciona uma imagem para um tamanho espec�fico para que possa ser salva no banco.
	 * @param file <code>File</code> com a imagem a ser redimensionada.
	 * @param width <code>int</code> com a largura da imagem.
	 * @param heigth <code>int</code> com a altura da imagem.
	 * @param formato <code>String</code>.
	 * @return <code>File</code> com a nova imagem a ser inserida ou <code>null</code> caso n�o seja
	 * 			poss�vel redimensionar.
	 */
	public static File redimensionarImagem(File file, int width, int heigth, String formato){
		try {
			//L� a imagem original para o buffer.
			BufferedImage original = ImageIO.read(file);
			
			//Cria uma imagem redimensionada com altura e largura espec�ficada.
			BufferedImage redimensionada = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
			
			//Redimensiona a imagem original na imagem redimensionada.
			redimensionada.getGraphics().drawImage(original, 0, 0, width, heigth, null);
			//Salva a imagem em um arquivo.
			ImageIO.write(redimensionada, formato, file);
			return file;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}//redimensionarImagem()
	
	/**
	 * Apaga o conte�do da pasta tempor�ria criada para redimensionar as imagens.
	 */
	public static void apagarPastaTemporaria(){
		File tmp = new File(DIR_TEMP);
		
		String arquivo[] = tmp.list();
	
		for(String arq : arquivo){
			new File(tmp + File.separator + arq).delete();
		}
	}//apagarPastaTemporaria()
}//MetodosConversaoBanco
