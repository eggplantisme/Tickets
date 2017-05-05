package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Movie;

public class MovieDao  extends Data_Built {
	public PreparedStatement cps = null;
	
	
	String create_movie_table = "create table if not exists movieInfo(mId int AUTO_INCREMENT primary key, movieName TEXT, movieActors TEXT, "
			+ "movieDescription TEXT, Now_ReceivedMoney int, On_time DATE, End_time DATE, Poster TEXT, Trailer TEXT, movieIntro TEXT, movieDirector TEXT, movieStyle TEXT, movieSpan TEXT)";
	String add_movie = "insert into movieInfo(movieName, movieActors, "
			+ "movieDescription, Now_ReceivedMoney, On_time, End_time, Poster, Trailer, movieIntro, movieDirector, movieStyle, movieSpan) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	String query_movie_byId = "select * from movieInfo where mId=?";
	String query_all_movie = "select * from movieInfo";
	
	String delete_movie = "delete from movieInfo where mId = ?";
	
	String update_movieInfo = "update movieInfo set movieName=?, movieActors=?, movieDescription=?, Now_ReceivedMoney=?, On_time=?, End_time=?, movieIntro=?, "
			+ "movieDirector=?, movieStyle=?, movieSpan=? where mId=?";
	
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
	public int AddMovie(Movie movie) {
		openCon();
		int mId = 0;
		try {
			ps= con.prepareStatement(add_movie);
			ps.setString(1, movie.getMovie_name());
			ps.setString(2, movie.getMain_actors());
			ps.setString(3, movie.getMovie_description());
			ps.setInt(4, 0);//新添加的电影票房为0;
			ps.setDate(5, movie.getOn_time());
			ps.setDate(6, movie.getEnd_time());
			ps.setString(7,  "无用，被固定格式代替");
			ps.setString(8,  "无用，被固定格式代替");
			ps.setString(9, movie.getMovie_intro());
			ps.setString(10,  movie.getMovie_director());
			ps.setString(11, movie.getMovie_style());
			ps.setString(12, movie.getMovie_span());
			int num = ps.executeUpdate();
			if(num>0){
				ps = con.prepareStatement("select @@IDENTITY");
				rs = ps.executeQuery();
				while (rs.next())
					mId = rs.getInt("@@IDENTITY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closePs();
			this.closeCon();
		}
		return mId;
	}
	//注销电影
		public boolean DeleteMovie(int mId) {
			openCon();
			boolean bool=false;
			try {
				ps= con.prepareStatement(delete_movie);
				ps.setInt(1,mId);
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
		openCon();
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
		openCon();
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
	
	//更改movie
		public boolean UpdateMovie(Movie movie, int mId) {
			openCon();
			boolean bool=false;
			try {
				ps=con.prepareStatement(update_movieInfo);
				ps.setString(1, movie.getMovie_name());
				ps.setString(2, movie.getMain_actors());
				ps.setString(3, movie.getMovie_description());
				ps.setInt(4, 0);
				ps.setDate(5, movie.getOn_time());
				ps.setDate(6, movie.getEnd_time());
				ps.setString(7,  movie.getMovie_intro());
				ps.setString(8,  movie.getMovie_director());
				ps.setString(9, movie.getMovie_style());
				ps.setString(10,  movie.getMovie_span());
				ps.setInt(11, mId);
				int num = ps.executeUpdate();
				if (num > 0) {
					bool = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeRs();
				this.closePs();
				this.closeCon();
			}
			return bool;
		}
}
