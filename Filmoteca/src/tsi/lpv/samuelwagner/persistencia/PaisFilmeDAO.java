package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisFilmeDAO {
	private static final String INSERIR_PAIS_FILME = "INSERT INTO paisfilme (codigo_filme,codigo_pais) VALUES (?,?);";
	private static final String OBTER_PAIS_FILME = "SELECT codigo_pais FROM paisfilme WHERE codigo_filme = ?;";
	private static final String OBTER_FILMES_PAIS = "SELECT codigo_filme FROM paisfilme WHERE codigo_pais = ?;";
	
	/**
	 * Cadastra os c�digos do filme e do pa�s na tabela de refer�ncia do banco.
	 * @param codigoFilme <code>int</code> c�digo do filme.
	 * @param codigoPais <code>int</code> c�digo do pa�s.
	 */
	public static void cadastrarPaisFilme(int codigoFilme, int codigoPais) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERIR_PAIS_FILME);
			stmt.setInt(1, codigoFilme);
			stmt.setInt(2, codigoPais);
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//cadastrarPaisFilme
	
	/**
	 * Obt�m o c�digo do pa�s em que o filme foi produzido.
	 * @param codigo <code>int</code> com o c�digo do filme que se deseja obter o pa�s em que ele foi produzido.
	 * @return <code>int</code> com o c�digo pa�s em que um determinado filme foi produzido.
	 */
	public static int obterPaisFilme(int codigo) {
		Connection conn = ConnectionFactory.getConnection();
		int codigoPais = 0;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(OBTER_PAIS_FILME);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()) codigoPais = resultSet.getInt(1);
			
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigoPais;
	}//obterFilmesDiretor()
	
	/**
	 * Obt�m a lista de todos os filmes produzidos em um determinado pa�s.
	 * @param codigo <code>int</code> com o c�digo do pa�s que se deseja obter os filmes.
	 * @return <code>List</code> com os c�digos dos filmes produzidos em um determinado pa�s.
	 */
	public static List<Integer> obterFilmesPais(int codigo) {
		Connection conn = ConnectionFactory.getConnection();
		List<Integer> codigos = new ArrayList<Integer>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(OBTER_FILMES_PAIS);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) codigos.add(resultSet.getInt(1));
			
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigos;
	}//obterFilmesPais()
}//class PaisFilmeDAO
