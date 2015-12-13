package tsi.lpv.filmoteca.funcaoauxiliar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import tsi.lpv.filmoteca.modelo.DadosIMDB;

public class ObterDadosIMDB {
	private static DadosIMDB dadosIMDB;

	public static DadosIMDB getDadosIMDB() {
		return dadosIMDB;
	}

	public static void setDadosIMDB(DadosIMDB dadosIMDB) {
		ObterDadosIMDB.dadosIMDB = dadosIMDB;
	}
	
	public static DadosIMDB pesquisarDados(String nomeFilme) throws IOException{
		//Substitui o espa�o em branco da String por + para realizar a busca.
		String nomeValido = nomeFilme.replaceAll(" ", "+");
		
		//Atribui a url de conex�o para buscar o nome do filme.
		URL url = new URL("http://www.omdbapi.com/?t=" + nomeValido + "&y=&plot=short&r=json");
		
		//Realiza a conex�o e define um tempo de espera para que a solicita��o seja atendida.
		URLConnection con = url.openConnection(/*proxy*/);
		con.setConnectTimeout(120000);
		
		//Obt�m a resposta recebida.
		BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		//Cria um objeto String para ler o conte�do de uma linha, e um StringBuilder para adicionar o conte�do lido.
		String line;
		StringBuilder source = new StringBuilder();
		
		//Realiza a leitura do conte�do e, ao t�rmino, encerra o objeto BufferedReader que cont�m os dados.
		while((line = input.readLine()) != null) source.append(line);
		input.close();
		
		try {
			Gson gson = new Gson();
			System.out.println(source.toString());
			dadosIMDB = gson.fromJson(source.toString(), DadosIMDB.class);
			return dadosIMDB;
		} catch (JsonParseException e) {
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException{
		String filme = "batman";
		
		DadosIMDB dados = pesquisarDados(filme);
		
		if(dados != null){
			System.out.println(dados.getActors());
		}
	}
}
