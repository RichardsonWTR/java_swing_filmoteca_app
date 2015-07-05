package tsi.lpv.samuelwagner.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Calendar;

import tsi.lpv.samuelwagner.funcaoauxiliar.MetodosConversaoBanco;
import tsi.lpv.samuelwagner.tipo.Filme;

/**
 * A classe <code>FilmeDAO</code> � respons�vel pelas informa��es de manipula��o de dados da tabela
 * filme.
 * @author Wagner Almeida
 * @author Samuel Gon�alves
 *
 */
public class FilmeDAO {
	private static final String INSERT_FILME = "INSERT INTO filme (titulo,duracao,ano,data_lancamento,sinopse,"
			+ "classificacao_etaria,classificacao_imdb,classificacao_pessoal,midia,poster) VALUES (?,?,?,?,?,?,?,?,?,?);";
	private static final String PESQUISA_FILME_TITULO = "SELECT * FROM filme WHERE titulo = ?;";
	private static final String PESQUISA_FILME_CODIGO = "SELECT * FROM filme WHERE codigo_filme = ?;";
	
	/**
	 * Cadastra um filme na tabela filme do banco de dados.
	 * @param filme
	 * @throws SQLException
	 * @throws SQLTimeoutException
	 */
	public static void cadastrarFilme(Filme filme) {
		//Obt�m uma conex�o com o banco.
		Connection conn = ConnectionFactory.getConnection();
		try {
			//Cria um objeto para fazer a inser��o dos dados.
			PreparedStatement stmt = conn.prepareStatement(INSERT_FILME);
			
			stmt.setString(1, filme.getTitulo());
			stmt.setInt(2, filme.getDuracao());
			stmt.setInt(3, filme.getAno());
			stmt.setDate(4, new Date(filme.getDataLancamento().getTimeInMillis()));
			stmt.setString(5, filme.getSinopse());
			stmt.setString(6, filme.getClassificacaoEtaria());
			stmt.setInt(7, filme.getClassificacaoIMDB());
			stmt.setInt(8, filme.getClassificacaoPessoal());
			stmt.setString(9, filme.getMidia());
			stmt.setBytes(10, MetodosConversaoBanco.preparaImagemParaBanco(filme.getPoster()));
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//cadastrarFilme
	
	/**
	 * Obt�m o ultimo c�digo cadastrado para um filme na tabela.
	 * @return <code>int</code>.
	 */
	public static int obterUltimoCodigo() {
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT currval('filme_codigo_filme_seq');");
			
			ResultSet resultSet = stmt.executeQuery();
			stmt.close();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}//obterUltimoCodigo
	
	/**
	 * Obt�m um filme atrav�s do nome especificado por par�metro.
	 * @param nomeFilme <code>String</code> nome do filme.
	 * @return <code>Filme</code> ou <code>null</code> caso o filme n�o seja encontrado.
	 */
	public static Filme pesquisarFilme(String nomeFilme){
		
		try {
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(PESQUISA_FILME_TITULO);
			stmt.setString(1, nomeFilme);
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			
			//Cria Objeto Filme.
			Filme filme= new Filme();
			filme.setCodigo(resultSet.getInt(1));
			filme.setTitulo(resultSet.getString(2));
			filme.setDuracao(resultSet.getInt(3));
			filme.setAno(resultSet.getInt(4));
			Calendar cal = Calendar.getInstance();
			cal.setTime(resultSet.getDate(5));
			filme.setDataLancamento(cal);
			filme.setSinopse(resultSet.getString(6));
			filme.setClassificacaoEtaria(resultSet.getString(7));
			filme.setClassificacaoIMDB(resultSet.getInt(8));
			filme.setClassificacaoPessoal(resultSet.getInt(9));
			filme.setMidia(resultSet.getString(10));
			filme.setPoster(MetodosConversaoBanco.reconstroiImagemDoBanco(resultSet.getBytes(11), filme.getTitulo()));
			stmt.close();
			return filme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}//pesquisarFilme()
	
	/**
	 * Obt�m um filme atrav�s do c�digo especificado por par�metro.
	 * 
	 * @param codigo <code>int</code> com o c�digo do filme.
	 * @return <code>Filme</code> ou <code>null</code> caso o filme n�o seja encontrado.
	 */
	public static Filme pesquisarFilme(int codigo){
		
		try {
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(PESQUISA_FILME_CODIGO);
			stmt.setInt(1, codigo);
			ResultSet resultSet = stmt.executeQuery();
			
			resultSet.next();
			
			//Cria Objeto Filme.
			Filme filme= new Filme();
			filme.setCodigo(resultSet.getInt(1));
			filme.setTitulo(resultSet.getString(2));
			filme.setDuracao(resultSet.getInt(3));
			filme.setAno(resultSet.getInt(4));
			Calendar cal = Calendar.getInstance();
			cal.setTime(resultSet.getDate(5));
			filme.setDataLancamento(cal);
			filme.setSinopse(resultSet.getString(6));
			filme.setClassificacaoEtaria(resultSet.getString(7));
			filme.setClassificacaoIMDB(resultSet.getInt(8));
			filme.setClassificacaoPessoal(resultSet.getInt(9));
			filme.setMidia(resultSet.getString(10));
			filme.setPoster(MetodosConversaoBanco.reconstroiImagemDoBanco(resultSet.getBytes(11), filme.getTitulo()));
			stmt.close();
			return filme;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}//pesquisarFilme()
}//FilmeDAO
