package tsi.lpv.filmoteca.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A classe <code>ConnectionFactory</code> obt�m uma conex�o com o banco de dados do aplicativo Filmoteca.
 *  
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class ConnectionFactory {
	private static Connection connection; //Conex�o a ser gerada.
	private static final String driverBD = "jdbc:postgresql:", //endere�o do banco	
								nomeDB = "filmoteca", //nomeBD
								user = "aluno", //Usu�rio
								password = "aluno"; //Senha 
	
	/**
	 * O m�todo <code>getConnection</code> verifica se existe uma conex�o v�lida ao banco, caso n�o houver, cria
	 * uma nova conex�o e retorna ao m�todo que requisitou uma conex�o. As senhas est�o encapsuladas dentro da classe
	 * para evitar que o usu�rio possa modific�-la.
	 * @return <code>Connection</code>
	 */
	public static Connection getConnection() {
		if(connection != null) return connection;
		else
			try {
				connection = DriverManager.getConnection(driverBD + nomeDB, user, password);
				return connection;
			} catch (SQLException e) {
				System.out.println("Erro ao conectar ao banco de dados");
				throw new RuntimeException();
			}
	}//getConnection
	
	/**
	 * Encerra a conex�o com o banco caso esta esteja aperta.
	 */
	public static void closeConnection() {
		try {
			if(connection != null) connection.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}//closeConnection
}//class SGDB
