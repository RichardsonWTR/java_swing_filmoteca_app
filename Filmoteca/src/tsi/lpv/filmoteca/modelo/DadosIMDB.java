package tsi.lpv.filmoteca.modelo;
/**
 * A classe <code>DadosIMDB</code> � respons�vel por encapsular os atributos que ser�o utilizados
 * para a busca dos dados atrav�s da api do IMDB. 
 * 
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class DadosIMDB {
	/*
	 * Atributos que armazenaram as informa��es do filme obtidas atrav�s da api. Os atributos est�o definidos usando letras
	 * ma�usculas, o que foge da conven��o de nomea��o adotada pelos desenvolvedores Java, que manda que atributos sejam nomeados
	 * come�ando com letras min�sculas. Os atributos est�o nomeados da forma erronea, pois a utiliza��o do api GSON necessita que 
	 * todos os atributos sejam nomeados de forma identica a chave de acesso do Json recebido, e este � retornado com a primeira letra
	 * escrita em mai�scula pela api do imdb.
	 */
	
	private String Title,
				   Year,
				   Rated,
				   Released,
				   Runtime,
				   Renre,
				   Rirector,
				   Writer,
				   Actors,
				   Plot,
				   Language,
				   Country,
				   Awards,
				   Poster,
				   Metascore,
				   ImdbRating,
				   ImdbVotes,
				   ImdbID,
				   Type;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getRated() {
		return Rated;
	}

	public void setRated(String rated) {
		Rated = rated;
	}

	public String getReleased() {
		return Released;
	}

	public void setReleased(String released) {
		Released = released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String runtime) {
		Runtime = runtime;
	}

	public String getRenre() {
		return Renre;
	}

	public void setRenre(String renre) {
		Renre = renre;
	}

	public String getRirector() {
		return Rirector;
	}

	public void setRirector(String rirector) {
		Rirector = rirector;
	}

	public String getWriter() {
		return Writer;
	}

	public void setWriter(String writer) {
		Writer = writer;
	}

	public String getActors() {
		return Actors;
	}

	public void setActors(String actors) {
		Actors = actors;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		Plot = plot;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getAwards() {
		return Awards;
	}

	public void setAwards(String awards) {
		Awards = awards;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		Poster = poster;
	}

	public String getMetascore() {
		return Metascore;
	}

	public void setMetascore(String metascore) {
		Metascore = metascore;
	}

	public String getImdbRating() {
		return ImdbRating;
	}

	public void setImdbRating(String imdbRating) {
		ImdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return ImdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		ImdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return ImdbID;
	}

	public void setImdbID(String imdbID) {
		ImdbID = imdbID;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	
}//class DadosIMDB
