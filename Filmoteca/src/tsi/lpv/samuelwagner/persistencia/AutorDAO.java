package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tsi.lpv.samuelwagner.tipo.Autor;

/**A classe <code>AutorDAO</code> implementa as opera��es necess�rias para manipula��o dos dados na tabela autor
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class AutorDAO {
	private static final String INSERT_AUTOR = "INSERT INTO autor (nome) VALUES (?);";
	private static final String SELECT_AUTOR_CODIDO = "SELECT * FROM autor WHERE codigo_autor = ?";
	private static final String SELECT_AUTOR_NOME = "SELECT * FROM autor WHERE nome = ?";
	
	/** Cadastra <code>Autor</code> no Banco de Dados.
	 * @param autor <code>Autor</code>.
	 */
	public static void cadastraAutor(Autor autor){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_AUTOR);
			stmt.setString(1,autor.getNome());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//CadastraAutor
	
	/**Pesquisa a o autor apartir do codigo fornecido.
	 * @param nome <code>Int</code>.
	 * @return um <code>Autor</code>.
	 */
	public static Autor pesquisaAutor(String nome){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_AUTOR_NOME);
			stmt.setString(1, nome);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				Autor autor = new Autor();
				autor.setCodigo(resultSet.getInt(1));
				autor.setNome(resultSet.getString(2));
				stmt.close();
				return autor;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**Pesquisa a o autor apartir do codigo fornecido.
	 * @param codigoAutor <code>Int</code>.
	 * @return um <code>Autor</code>.
	 */
	public static Autor pesquisaAutor(int codigoAutor){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_AUTOR_CODIDO);
			stmt.setInt(1, codigoAutor);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				Autor autor = new Autor();
				autor.setCodigo(resultSet.getInt(1));
				autor.setNome(resultSet.getString(2));
				stmt.close();
				return autor;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Obt�m o ultimo c�digo cadastrado para um autor na tabela.
	 * @return <code>int</code>
	 */
	public static int obterUltimoCodigo() {
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT currval('autor_codigo_autor_seq'::regclass);");
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				int codigo = resultSet.getInt(1);
				stmt.close();
				return codigo;
			}else{
				stmt.close();
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
