package dao;

import java.sql.*;
import bean.*;

public class UserDao extends Data_Built{
	String sql;
	public PreparedStatement cps = null;
	
	String create_user_table = "create table if not exists userInfo(uId int AUTO_INCREMENT primary key, userName varchar(1000), passWord varchar(1000), PhoneNumber varchar(1000), UserLocation varchar(1000))";
	String add_user = "insert into userInfo(userName, passWord, PhoneNumber, UserLocation) values (?, ?, ?, ?)";
	String query_user_byName = "select * from userInfo where userName=?";
	String query_user_byPhone = "select * from userInfo where PhoneNumber=?";
	
	String update_userInfo = "update userInfo set userName=?, PhoneNumber=?, UserLocation=? where uId=?";
	String update_userName = "update userInfo set userName=? where userName=?";
	String update_userPassword = "update userInfo set passWord=? where uId=?";
	
	String delete_user = "delete from userInfo where uId = ?";
	public UserDao() {//与数据库进行连接并创建用户表
		openCon();
		try {
			cps = con.prepareStatement(create_user_table);
			cps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//添加用户
	public int addUser(User user){
		openCon();
		int uId = 0;
		try {
			ps= con.prepareStatement(add_user);
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3, user.getPhoneNumber());
			ps.setString(4, user.getUserLocation());
			int num = ps.executeUpdate();
			if(num>0){
				ps = con.prepareStatement("select @@IDENTITY");
				rs = ps.executeQuery();
				while (rs.next())
					uId = rs.getInt("@@IDENTITY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closePs();
			this.closeCon();
		}
		return uId;
	}
	//查询用户
	public boolean queryUserbyName(String name){
		openCon();
		boolean bool=false;
		try {
			ps=con.prepareStatement(query_user_byName);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				bool=true;
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
		public User GetUserbyName(String name){
			openCon();
			User user = new User();
			try {
				ps=con.prepareStatement(query_user_byName);
				ps.setString(1, name);
				rs = ps.executeQuery();
				while(rs.next()){
					user.setUserId(rs.getInt("uId"));
					user.setUsername(rs.getString("userName"));
					user.setPassword(rs.getString("passWord"));
					user.setPhoneNumber(rs.getString("PhoneNumber"));
					user.setUserLocation(rs.getString("UserLocation"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeRs();
				this.closePs();
				this.closeCon();
			}
			return user;
		}
	public boolean queryUserbyPhone(User bean){
		openCon();
		boolean bool=false;
		try {
			ps=con.prepareStatement(query_user_byPhone);
			ps.setString(1, bean.getPhoneNumber());
			rs = ps.executeQuery();
			while(rs.next()){
				bool=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return bool;
	}
	//更改用户
	public boolean UpdateUser(User user, int userId) {
		openCon();
		boolean bool=false;
		try {
			ps=con.prepareStatement(update_userInfo);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPhoneNumber());
			ps.setString(3, user.getUserLocation());
			ps.setInt(4, userId);
			int num = ps.executeUpdate();
			if (num > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return bool;
	}
	public boolean UpdateUserName(String name, String ori_name) {
		openCon();
		boolean bool=false;
		try {
			ps=con.prepareStatement(update_userName);
			ps.setString(1, name);
			ps.setString(2, ori_name);
			int num = ps.executeUpdate();
			if (num > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return bool;
	}
	public boolean UpdateUserpassword(String _password, int userId) {
		openCon();
		boolean bool=false;
		try {
			ps=con.prepareStatement(update_userPassword);
			ps.setString(1, _password);
			ps.setInt(2, userId);
			int num = ps.executeUpdate();
			if (num > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return bool;
	}
	//注销用户
	public boolean DeleteUser(int userId) {
		openCon();
		boolean bool=false;
		try {
			ps= con.prepareStatement(delete_user);
			ps.setInt(1,userId);
			int num = ps.executeUpdate();
			if(num>0){
				bool=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closePs();
			this.closeCon();
		}
		return bool;
	}
}
