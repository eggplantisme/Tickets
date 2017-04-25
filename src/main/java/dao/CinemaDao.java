package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Cinema;

public class CinemaDao extends Data_Built{
	
	public PreparedStatement cps = null;
	String create_cinema_table = "create table if not exists cinemaInfo(cId int AUTO_INCREMENT primary key, cinemaName TEXT, cinemaLocation TEXT, cinemaPhoneNumber TEXT)";
	String add_cinema = "insert into cinemaInfo(cinemaName, cinemaLocation, cinemaPhoneNumber) values (?, ?, ?)";
	String update_cinemaInfo = "update cinemaInfo set cinemaName=?, cinemaLocation=?, cinemaPhoneNumber=? where cId=?";
	String delete_cinema = "delete from cinemaInfo where cId = ?";
	
	String query_all_cinema = "select * from cinemaInfo";
	String query_cinema = "select * from cinemaInfo where cId = ?";
	
	
	public CinemaDao() {
		openCon();
		try {
			cps = con.prepareStatement(create_cinema_table);
			cps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//添加影院
	public boolean addCinema(Cinema cinema){
		openCon();
		boolean bool=false;
		try {
			ps= con.prepareStatement(add_cinema);
			ps.setString(1, cinema.getCinemaName());
			ps.setString(2, cinema.getCinemaLocation());
			ps.setString(3, cinema.getCinemaPhoneNumber());
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
	//更改影院
	public boolean UpdateCinema(Cinema cinema, int cId) {
		openCon();
		boolean bool=false;
		try {
			ps=con.prepareStatement(update_cinemaInfo);
			ps.setString(1, cinema.getCinemaName());
			ps.setString(2, cinema.getCinemaLocation());
			ps.setString(3, cinema.getCinemaPhoneNumber());
			ps.setInt(4, cId);
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
	//注销影院
	public boolean DeleteCinema(int cId) {
		openCon();
		boolean bool=false;
		try {
			ps= con.prepareStatement(delete_cinema);
			ps.setInt(1,cId);
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
	//获取所有影院
	public List<Cinema> Getcinemas() {
		openCon();
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		try {
			ps=con.prepareStatement(query_all_cinema);
			rs = ps.executeQuery();
			while (rs.next()) {
				Cinema cinema = new Cinema();
				cinema.setcId(rs.getInt("cId"));
				cinema.setCinemaName(rs.getString("cinemaName"));
				cinema.setCinemaLocation(rs.getString("cinemaLocation"));
				cinema.setCinemaPhoneNumber(rs.getString("cinemaPhoneNumber"));
				cinemas.add(cinema);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return cinemas;
	}

	//获取指定Id的影院
	public Cinema GetCinema(int cId) {
		openCon();
		Cinema cinema = new Cinema();
		try {
			ps=con.prepareStatement(query_cinema);
			ps.setInt(1, cId);
			rs = ps.executeQuery();
			while(rs.next()) {
				cinema.setcId(cId);
				cinema.setCinemaName(rs.getString("cinemaName"));
				cinema.setCinemaLocation(rs.getString("cinemaLocation"));
				cinema.setCinemaPhoneNumber(rs.getString("cinemaPhoneNumber"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return cinema;
		
	}
}
