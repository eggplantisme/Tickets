package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Seat;

public class SeatDao extends Data_Built {
	public PreparedStatement cps = null;
	String create_seat_table = "create table if not exists seatInfo("
			+ "seatId int AUTO_INCREMENT primary key, sId int, seatRow int, seatColumn int, "
			+ "foreign key(sId) references scheduleInfo(sId))";
	
	String add_used_seat = "insert into seatInfo(sId, seatRow, seatColumn) values (?, ?, ?)";
	
	String delete_used_seat = "delete from seatInfo where sId = ?";
	
	String query_seat_by_sId = "select * from seatInfo where sId = ?";
	
	public SeatDao() {
		openCon();
		try {
			cps = con.prepareStatement(create_seat_table);
			cps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//添加使用了一个座位
	public int addUsedSeat(Seat seat){
		openCon();
		int seatId = 0;
		try {
			ps= con.prepareStatement(add_used_seat);
			ps.setInt(1, seat.getsId());
			ps.setInt(2, seat.getSeatRow());
			ps.setInt(3, seat.getSeatColumn());
			int num = ps.executeUpdate();
			if(num>0){
				ps = con.prepareStatement("select @@IDENTITY");
				rs = ps.executeQuery();
				while (rs.next())
					seatId = rs.getInt("@@IDENTITY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closePs();
			this.closeCon();
		}
		return seatId;
	}
	//取消座位
	public boolean DeleteUsedSeat(int seatId) {
		openCon();
		boolean bool=false;
		try {
			ps= con.prepareStatement(delete_used_seat);
			ps.setInt(1,seatId);
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

	//获取所有安排
	public List<Seat> GetUsedSeatsBySid(int sId) {
		openCon();
		ArrayList<Seat> seats = new ArrayList<Seat>();
		try {
			ps=con.prepareStatement(query_seat_by_sId);
			ps.setInt(1, sId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Seat seat = new Seat();
				seat.setSeatId(rs.getInt("seatId"));
				seat.setsId(sId);
				seat.setSeatRow(rs.getInt("seatRow"));
				seat.setSeatColumn(rs.getInt("seatColumn"));
				seats.add(seat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return seats;
	}
		
}
