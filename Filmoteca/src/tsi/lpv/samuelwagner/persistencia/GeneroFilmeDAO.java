package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Classe Responsavel das Opera��es do Banco para obter as rela��es entre o Genero e seu Filme.
 * @author Samuel
 * @author Wagner
 */
public class GeneroFilmeDAO {
	private final String INSERT_GENERO_FILME = "INSERT INTO generofilme(codigo_filme,codigo_genero) VALUES(?,?);";
	private final String SELECT_GENEROF_FILME = "SELECT codigo_filme FROM generofilme WHERE codigo_genero = ?";
	private final String SELECT_GENEROF_GENERO = "SELECT codigo_genero FROM generofilme WHERE codigo_filme = ?";
	
	/** Associa o filme ao seu genero.
	 * @param codigoFilme <code>int</code> c�digo do filme.
	 * @param codigoGenero <code>int</code> c�digo do genero.
	 */
	public void cadastraGeneroFilme(int codigoFilme,int codigoGenero){
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_GENERO_FILME);
			stmt.setInt(1, codigoFilme);
			stmt.setInt(2, codigoGenero);
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Recupera os codigos do filme em rela��o ao seu genero.
	 * @param codigoGenero <code>int</code> c�digo do genero. 
	 * @return um <code>List</code> com os c�digos dos Filmes.
	 */
	public List<Integer> pesquisGeneroFilme(int codigoGenero){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_GENEROF_FILME);
			stmt.setInt(1, codigoGenero);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(!resultSet.next()){
				stmt.close();
				resultSet.close();
				return null;
			}
			
			List<Integer> listCodigosFilme = new ArrayList<>();
			
			while(resultSet.next())	listCodigosFilme.add(resultSet.getInt(1));
			
			stmt.close();
			resultSet.close();
			
			return listCodigosFilme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**Recupera o codigo dos genero referente ao filme informado.
	 * @param codigoFilme <code>int</code> c�digo do filme. 
	 * @return um <code>int</code> com o c�digo do genero.
	 */
	public int pesquisElencoArtista(int codigoFilme){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_GENEROF_GENERO);
			stmt.setInt(1, codigoFilme);
			
			ResultSet resultSet = stmt.executeQuery();
			int codigoGenero;
			
			if(resultSet.next())
				codigoGenero = resultSet.getInt(1);
			else
				codigoGenero = -1;

			stmt.close();
			resultSet.close();
			
			return codigoGenero;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
