package dao;

import java.sql.*;
import bean.*;

public class UserDao extends Data_Built{
	String sql;
	public PreparedStatement cps = null;
	
	String create_user_table = "create table if not exists userInfo(uId int AUTO_INCREMENT primary key, userName varchar(1000), passWord varchar(1000))";
	public UserDao() {//与数据库进行连接并创建用户表
		openCon();
		try {
			cps = con.prepareStatement(create_user_table);
			cps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//添加用户
	public boolean addUser(User user){
		boolean bool=false;
		try {
			sql="insert into userInfo(userName, passWord) values (?, ?)";
			ps= con.prepareStatement(sql);
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
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
	public boolean queryUser(User bean){
		boolean bool=false;
		try {
			sql="select * from userInfo where userName=? and passWord=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, bean.getUsername());
			ps.setString(2,bean.getPassword());
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
}
