package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.support.MethodOverride;

import com.mysql.fabric.xmlrpc.base.Array;

import bean.Movie;

public class MovieDao  extends Data_Built {
	public PreparedStatement cps = null;
	
	
	String create_movie_table = "create table if not exists movieInfo(mId int AUTO_INCREMENT primary key, movieName TEXT, movieActors TEXT, "
			+ "movieDescription TEXT, Now_ReceivedMoney int, On_time DATE, End_time DATE, Poster TEXT, Trailer TEXT, movieIntro TEXT, movieDirector TEXT, movieStyle TEXT, movieSpan TEXT)";
	String add_movie = "insert into movieInfo(movieName, movieActors, "
			+ "movieDescription, Now_ReceivedMoney, On_time, End_time, Poster, Trailer, movieIntro, movieDirector, movieStyle, movieSpan) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	String query_movie_byId = "select * from movieInfo where mId=?";
	String query_all_movie = "select * from movieInfo";
	
	public MovieDao() {
		openCon();
		try {
			cps = con.prepareStatement(create_movie_table);
			cps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public boolean AddMovie(Movie movie) {
		boolean bool=false;
		try {
			ps= con.prepareStatement(add_movie);
			ps.setString(1, movie.getMovie_name());
			ps.setString(2, movie.getMain_actors());
			ps.setString(3, movie.getMovie_description());
			ps.setInt(4, movie.getNow_ReceivedMoney());
			ps.setDate(5, movie.getOn_time());
			ps.setDate(6, movie.getEnd_time());
			ps.setString(7,  movie.getPoster());
			ps.setString(8,  movie.getTrailer());
			ps.setString(9, movie.getMovie_intro());
			ps.setString(10,  movie.getMovie_director());
			ps.setString(11, movie.getMovie_style());
			ps.setString(12, movie.getMovie_span());
			int num = ps.executeUpdate();
			if(num>0){
				bool=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closePs();
			this.closeCon();
		}
		return bool;
	}
	
	public Movie GetMovieFromID(int mid) {
		Movie movie = new Movie();
		try {
			ps=con.prepareStatement(query_movie_byId);
			ps.setInt(1, mid);
			rs = ps.executeQuery();
			while(rs.next()) {
				movie.setId(mid);
				movie.setMovie_name(rs.getString("movieName"));
				movie.setMain_actors(rs.getString("movieActors"));
				movie.setMovie_description(rs.getString("movieDescription"));
				movie.setNow_ReceivedMoney(rs.getInt("Now_ReceivedMoney"));
				movie.setOn_time(rs.getDate("On_time"));
				movie.setEnd_time(rs.getDate("End_time"));
				movie.setPoster(rs.getString("Poster"));
				movie.setTrailer(rs.getString("Trailer"));
				movie.setMovie_director(rs.getString("movieDirector"));
				movie.setMovie_intro(rs.getString("movieIntro"));
				movie.setMain_actors(rs.getString("movieDirector"));
				movie.setMovie_style(rs.getString("movieStyle"));
				movie.setMovie_span(rs.getString("movieSpan"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return movie;
	}
	
	public List<Movie> GetMovies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			ps=con.prepareStatement(query_all_movie);
			rs = ps.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getInt("mId"));
				movie.setMovie_name(rs.getString("movieName"));
				movie.setMain_actors(rs.getString("movieActors"));
				movie.setMovie_description(rs.getString("movieDescription"));
				movie.setNow_ReceivedMoney(rs.getInt("Now_ReceivedMoney"));
				movie.setOn_time(rs.getDate("On_time"));
				movie.setEnd_time(rs.getDate("End_time"));
				movie.setPoster(rs.getString("Poster"));
				movie.setTrailer(rs.getString("Trailer"));
				movie.setMovie_intro(rs.getString("movieIntro"));
				movie.setMain_actors(rs.getString("movieDirector"));
				movie.setMovie_style(rs.getString("movieStyle"));
				movie.setMovie_span(rs.getString("movieSpan"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return movies;
	}
}
