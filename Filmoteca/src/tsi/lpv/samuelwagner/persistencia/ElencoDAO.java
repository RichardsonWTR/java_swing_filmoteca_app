package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**A classe <code>ElencoDAO</code> cont�m as opera��es de inser��o e pesquisa de dados na tabela de refer�ncia
 * de artista e filme.
 * @author Samuel
 * @author Wagner
 */
public class ElencoDAO {
	private static final String INSERT_ELENCO = "INSERT INTO elenco(codigo_filme,codigo_artista) VALUES(?,?);";
	private static final String SELECT_ELENCO_FILME = "SELECT codigo_filme FROM elenco WHERE codigo_artista = ?";
	private static final String SELECT_ELENCO_ARTISTA = "SELECT codigo_artista FROM elenco WHERE codigo_filme = ?";
	
	/** Associa o artista ao filme que participa.
	 * @param codigoFilme <code>int</code> c�digo do filme.
	 * @param codigoArtista <code>int</code> c�digo do artista.
	 */
	public static void cadastraElenco(int codigoFilme,int codigoArtista){
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_ELENCO);
			stmt.setInt(1, codigoFilme);
			stmt.setInt(2, codigoArtista);
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Recupera os codigos do filme em qual o artista trabalhou.
	 * @param codigoArtista <code>int</code> c�digo do artista. 
	 * @return um <code>List</code> com os c�digos dos Filmes.
	 */
	public static List<Integer> pesquisElencoFilme(int codigoArtista){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ELENCO_FILME);
			stmt.setInt(1, codigoArtista);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(!resultSet.next()){
				stmt.close();
				resultSet.close();
				return null;
			}
			
			List<Integer> listCodigosFilme = new ArrayList<>();
			do{
				listCodigosFilme.add(resultSet.getInt(1));
			}while(resultSet.next());
			
			stmt.close();
			resultSet.close();
			
			return listCodigosFilme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**Recupera os codigos dos artistas que tabalhou no filme informado..
	 * @param codigoFilme <code>int</code> c�digo do filme. 
	 * @return um <code>List</code> com os c�digos dos artistas.
	 */
	public static List<Integer> pesquisElencoArtista(int codigoFilme){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ELENCO_ARTISTA);
			stmt.setInt(1, codigoFilme);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(!resultSet.next()){
				stmt.close();
				return null;
			}
			
			List<Integer> listCodigosFilme = new ArrayList<>();
			do{
				listCodigosFilme.add(resultSet.getInt(1));
			}while(resultSet.next()); 
			
			stmt.close();
			resultSet.close();
			
			return listCodigosFilme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
