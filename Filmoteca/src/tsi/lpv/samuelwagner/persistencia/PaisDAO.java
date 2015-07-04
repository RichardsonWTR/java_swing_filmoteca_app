package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tsi.lpv.samuelwagner.tipo.Pais;

/**Classe Responsavel das Opera��es do Banco para a Classe <code>Pais</code>.
 * @author Samuel
 * @author Wagner
 */
public class PaisDAO {
	private final String INSERT_PAIS = "INSERT INTO artista (nome) VALUES (?);";
	private final String SELECT_PAIS_CODIGO = "SELECT * FROM pais WHERE codigo_pais = ?;";
	private final String SELECT_PAIS_NOME = "SELECT * FROM pais WHERE nome = ?;";
	
	/**Cadastra <code>Pais</code> no Banco de Dados.
	 * @param pais <code>Pais</code>.
	 */
	public void cadastraPais(Pais pais) {
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(INSERT_PAIS);
			stmt.setString(1, pais.getNome());
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**Pesquisa a o pais apartir do codigo fornecido.
	 * @param codigoPais <code>Int</code>.
	 * @return um <code>Pais</code>.
	 */
	public Pais pesquisaPais(int codigoPais){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_PAIS_CODIGO);
			stmt.setInt(1, codigoPais);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				Pais pais = new Pais();
				pais.setCodigo(resultSet.getInt(1));
				pais.setNome(resultSet.getString(2));
				stmt.close();
				return pais;
			}else{
				stmt.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**Pesquisa a o pais apartir do codigo fornecido.
	 * @param nome <code>Int</code>.
	 * @return um <code>Pais</code>.
	 */
	public Pais pesquisaPais(String nome){
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_PAIS_NOME);
			stmt.setString(1, nome);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				Pais pais = new Pais();
				pais.setCodigo(resultSet.getInt(1));
				pais.setNome(resultSet.getString(2));
				stmt.close();
				return pais;
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
	 * Obt�m o ultimo c�digo cadastrado para um pais na tabela.
	 * @return <code>int</code>
	 */
	public int obterUltimoCodigo() {
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT currval('pais_codigo_pais_seq'::regclass);");
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				stmt.close();
				return resultSet.getInt(1);
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
