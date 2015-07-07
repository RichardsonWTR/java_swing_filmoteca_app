package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe <code>DiretorFilmeDAO</code> cont�m as opera��es de inser��o e pesquisa de dados na tabela de refer�ncia
 * de diretor filme.
 * 
 * @author Wagner Almeida
 * @author Samuel Gon�alves
 */
public class DiretorFilmeDAO {
	private static final String INSERIR_DIRETOR_FILME = "INSERT INTO diretorfilme (codigo_filme,codigo_diretor) VALUES (?,?);";
	private static final String OBTER_FILMES_DIRETOR = "SELECT codigo_filme FROM diretorfilme WHERE codigo_diretor = ?;";
	private static final String OBTER_DIRETOR_FILME = "SELECT codigo_diretor FROM diretorfilme WHERE codigo_filme = ?;";
	
	/**
	 * Cadastra os c�digos do filme e do diretor na tabela de refer�ncia do banco.
	 * @param codigoFilme <code>int</code> c�digo do filme.
	 * @param codigoDiretor <code>int</code> c�digo do diretor.
	 */
	public static void cadastrarDiretorFilme(int codigoFilme, int codigoDiretor) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERIR_DIRETOR_FILME);
			stmt.setInt(1, codigoFilme);
			stmt.setInt(2, codigoDiretor);
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//cadastrarDiretorFilme
	
	/**
	 * Obt�m a lista de todos os filmes de um determinado diretor.
	 * @param codigo <code>int</code> com o c�digo do diretor que se deseja obter os filmes.
	 * @return <code>List</code> com os c�digos dos filmes que foram produzidos por um determinado diretor.
	 */
	public static List<Integer> obterFilmesDiretor(int codigo) {
		Connection conn = ConnectionFactory.getConnection();
		List<Integer> codigos = new ArrayList<Integer>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(OBTER_FILMES_DIRETOR);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next())
				do{
					codigos.add(resultSet.getInt(1));
				}while(resultSet.next()); 
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return codigos;
	}//obterFilmesDiretor()
	
	/**
	 * Obt�m a lista de todos os diretores de um determinado filme.
	 * @param codigo <code>int</code> com o c�digo do filme que se deseja obter o diretor.
	 * @return <code>List</code> com os c�digos dos diretores que fazem parte deste filme.
	 */
	public static List<Integer> obterDiretorFilme(int codigo) {
		Connection conn = ConnectionFactory.getConnection();
		List<Integer> codigos = new ArrayList<Integer>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(OBTER_DIRETOR_FILME);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next())
				do{
					codigos.add(resultSet.getInt(1));
				}while(resultSet.next()); 
			
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return codigos;
	}//obterFilmesDiretor()
}//class DiretorFilmeDAO
