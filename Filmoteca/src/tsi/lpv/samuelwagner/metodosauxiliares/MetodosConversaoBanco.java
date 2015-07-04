package tsi.lpv.samuelwagner.metodosauxiliares;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
			@SuppressWarnings("resource")
			InputStream imagem = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];
			int offset = 0, numRead = 0;
			
			while(offset < bytes.length && (numRead = imagem.read(bytes, offset, bytes.length - offset)) >= 0)
				offset += numRead;
			
			return bytes;
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
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}//reconstroiImagemDoBanco()
}//MetodosConversaoBanco
