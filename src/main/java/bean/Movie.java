package bean;

import java.sql.Date;

public class Movie {
	private int id;
	private String movie_name;
	private String main_actors;
	private String movie_description;
	
	private String movie_intro;
	private String movie_director;
	private String movie_style;
	private String movie_span;
	
	
	private int Now_ReceivedMoney;//Æ±·¿
	private Date On_time;
	private Date End_time;
	
	private String Poster;
	
	private String Trailer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMain_actors() {
		return main_actors;
	}
	public void setMain_actors(String main_actors) {
		this.main_actors = main_actors;
	}
	public String getMovie_description() {
		return movie_description;
	}
	public void setMovie_description(String movie_description) {
		this.movie_description = movie_description;
	}
	public int getNow_ReceivedMoney() {
		return Now_ReceivedMoney;
	}
	public void setNow_ReceivedMoney(int now_ReceivedMoney) {
		Now_ReceivedMoney = now_ReceivedMoney;
	}
	
	public Date getOn_time() {
		return On_time;
	}
	public void setOn_time(Date on_time) {
		On_time = on_time;
	}
	public Date getEnd_time() {
		return End_time;
	}
	public void setEnd_time(Date end_time) {
		End_time = end_time;
	}
	public String getTrailer() {
		return Trailer;
	}
	public void setTrailer(String trailer) {
		Trailer = trailer;
	}
	public String getPoster() {
		return Poster;
	}
	public void setPoster(String poster) {
		Poster = poster;
	}
	public String getMovie_intro() {
		return movie_intro;
	}
	public void setMovie_intro(String movie_intro) {
		this.movie_intro = movie_intro;
	}
	public String getMovie_director() {
		return movie_director;
	}
	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}
	public String getMovie_style() {
		return movie_style;
	}
	public void setMovie_style(String movie_style) {
		this.movie_style = movie_style;
	}
	public String getMovie_span() {
		return movie_span;
	}
	public void setMovie_span(String movie_span) {
		this.movie_span = movie_span;
	}
}
