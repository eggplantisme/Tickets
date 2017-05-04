package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Schedule;

public class ScheduleDao extends Data_Built{
	public PreparedStatement cps = null;
	String create_schedule_table = "create table if not exists scheduleInfo("
			+ "sId int AUTO_INCREMENT primary key, mId int, cId int, Price TEXT, hallName TEXT, "
			+ "startDate DATE, startTime TIME, "
			+ "foreign key(mId) references movieInfo(mId), "
			+ "foreign key(cId) references cinemaInfo(cId))";
	
	String add_schedule = "insert into scheduleInfo(mId, cId, Price, hallName, startDate, startTime) values (?, ?, ?, ?, ?, ?)";
	
	String delete_schedule = "delete from scheduleInfo where sId = ?";
	
	String update_scheduleInfo = "update scheduleInfo set mId=?, cId=?, Price=?, hallName=?, startDate=?, startTime=? where sId=?";
	
	String query_all_schedule = "select * from scheduleInfo";
	String query_schedule = "select * from scheduleInfo where sId = ?";
	
	public ScheduleDao() {
		openCon();
		try {
			@SuppressWarnings("unused")
			CinemaDao cinemaDao = new CinemaDao();//为了第一次创建schedule时没有cinema而产生尴尬
			cps = con.prepareStatement(create_schedule_table);
			cps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//添加安排
	public boolean addSchedule(Schedule schedule){
		openCon();
		boolean bool=false;
		try {
			ps= con.prepareStatement(add_schedule);
			ps.setInt(1, schedule.getmId());
			ps.setInt(2, schedule.getcId());
			ps.setString(3, schedule.getPrice());
			ps.setString(4,  schedule.getHallName());
			ps.setDate(5,  schedule.getStartDate());
			ps.setTime(6, schedule.getStartTime());
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

	//注销安排
	public boolean DeleteSchedule(int sId) {
		openCon();
		boolean bool=false;
		try {
			ps= con.prepareStatement(delete_schedule);
			ps.setInt(1,sId);
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
	
	//更改安排
	public boolean UpdateSchedule(Schedule schedule, int sId) {
		openCon();
		boolean bool=false;
		try {
			ps=con.prepareStatement(update_scheduleInfo);
			ps.setInt(1, schedule.getmId());
			ps.setInt(2, schedule.getcId());
			ps.setString(3, schedule.getPrice());
			ps.setString(4,  schedule.getHallName());
			ps.setDate(5, schedule.getStartDate());
			ps.setTime(6,  schedule.getStartTime());
			ps.setInt(7, sId);
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
	
	//获取所有安排
	public List<Schedule> Getschedules() {
		openCon();
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		try {
			ps=con.prepareStatement(query_all_schedule);
			rs = ps.executeQuery();
			while (rs.next()) {
				Schedule schedule = new Schedule();
				schedule.setsId(rs.getInt("sId"));
				schedule.setmId(rs.getInt("mId"));
				schedule.setcId(rs.getInt("cId"));
				schedule.setPrice(rs.getString("Price"));
				schedule.setHallName(rs.getString("hallName"));
				schedule.setStartDate(rs.getDate("startDate"));
				schedule.setStartTime(rs.getTime("startTime"));
				schedules.add(schedule);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return schedules;
	}

	//获取指定Id的安排
	public Schedule GetSchedule(int sId) {
		openCon();
		Schedule schedule = new Schedule();
		try {
			ps=con.prepareStatement(query_schedule);
			ps.setInt(1, sId);
			rs = ps.executeQuery();
			while(rs.next()) {
				schedule.setsId(rs.getInt("sId"));
				schedule.setmId(rs.getInt("mId"));
				schedule.setcId(rs.getInt("cId"));
				schedule.setPrice(rs.getString("Price"));
				schedule.setHallName(rs.getString("hallName"));
				schedule.setStartDate(rs.getDate("startDate"));
				schedule.setStartTime(rs.getTime("startTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return schedule;
	}
	
	
	String query_schedule_by_mId = "select * from scheduleInfo where mId = ?";
	//获取所有安排
		public List<Schedule> GetschedulesByMid(int mId) {
			openCon();
			ArrayList<Schedule> schedules = new ArrayList<Schedule>();
			try {
				ps=con.prepareStatement(query_schedule_by_mId);
				ps.setInt(1, mId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Schedule schedule = new Schedule();
					schedule.setsId(rs.getInt("sId"));
					schedule.setmId(rs.getInt("mId"));
					schedule.setcId(rs.getInt("cId"));
					schedule.setPrice(rs.getString("Price"));
					schedule.setHallName(rs.getString("hallName"));
					schedule.setStartDate(rs.getDate("startDate"));
					schedule.setStartTime(rs.getTime("startTime"));
					schedules.add(schedule);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeRs();
				this.closePs();
				this.closeCon();
			}
			return schedules;
		}
	
}
