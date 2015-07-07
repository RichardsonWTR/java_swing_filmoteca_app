package tsi.lpv.samuelwagner.funcaoauxiliar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	 * Reconstr�i os dados em bytes recebidos para um diret�rio tempor�rio na pasta do arquivo.
	 * @param bytes <code>byte[]</code> com os dados a serem reconstru�dos.
	 * @param nome <code>String</code> com o nome do arquivo.
	 * @return <code>File</code> com o endere�o do arquivo reconstru�do.
	 */
	public static File reconstroiImagemDoBanco(byte[] bytes, String nome){
		File f = new File(DIR_TEMP + File.separator + nome);
		 
		try {
			if(bytes != null){
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(bytes);
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}//reconstroiImagemDoBanco()
	
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
			new File(DIR_TEMP + File.separator).mkdirs();
			//Salva a imagem em um arquivo.
			ImageIO.write(redimensionada, formato, new File(DIR_TEMP + File.separator + file.getName()));
			return new File(DIR_TEMP + File.separator + file.getName());
			
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
