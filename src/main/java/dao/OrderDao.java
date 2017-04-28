package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Order;

public class OrderDao extends Data_Built {
	public PreparedStatement cps = null;
	String create_order_table = "create table if not exists orderInfo("
			+ "orderId int AUTO_INCREMENT primary key, seatId int, sId int, uId int, "
			+ "orderPrice TEXT, buyDate DATE, "
			+ "status TEXT, "
			+ "foreign key(sId) references scheduleInfo(sId), "
			+ "foreign key(seatId) references seatInfo(seatId), "
			+ "foreign key(uId) references userInfo(uId))";

	String add_order = "insert into orderInfo(seatId, sId, uId, orderPrice, buyDate, status) values (?, ?, ?, ?, ?, ?)";
	
	String delete_order = "delete from orderInfo where orderId = ?";
	
	String query_order_by_uId = "select * from orderInfo where uId = ?";
	
	String query_order_by_oId = "select * from orderInfo where orderId = ?";
	
	String change_status = "update orderInfo set status=? where orderId=?";
	
	public OrderDao() {
		openCon();
		try {
			cps = con.prepareStatement(create_order_table);
			cps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//添加订单
	public int addOrder(Order order){
		openCon();
		int orderId = 0;
		try {
			ps= con.prepareStatement(add_order);
			ps.setInt(1, order.getSeatId());
			ps.setInt(2, order.getsId());
			ps.setInt(3, order.getuId());
			ps.setString(4, order.getOrderPrice());
			ps.setDate(5, order.getBuyDate());
			ps.setString(6, order.getStatus());
			int num = ps.executeUpdate();
			if(num>0){
				ps = con.prepareStatement("select @@IDENTITY");
				rs = ps.executeQuery();
				while (rs.next())
					orderId = rs.getInt("@@IDENTITY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closePs();
			this.closeCon();
		}
		return orderId;//返回最新添加的订单号
	}

	//取消订单
	public boolean DeleteOrder(int orderId) {
		openCon();
		boolean bool=false;
		try {
			ps= con.prepareStatement(delete_order);
			ps.setInt(1,orderId);
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
		
	//获取特定用户所有订单
	public List<Order> GetOrderbyUid(int uId) {
		openCon();
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			ps=con.prepareStatement(query_order_by_uId);
			ps.setInt(1, uId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setoId(rs.getInt("oId"));
				order.setSeatId(rs.getInt("seatId"));
				order.setsId(rs.getInt("sId"));
				order.setuId(uId);
				order.setOrderPrice(rs.getString("orderPrice"));
				order.setBuyDate(rs.getDate("buyDate"));
				order.setStatus(rs.getString("status"));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return orders;
	}
	//获取某一订单
	public Order GetOrderbyOid(int oId) {
		openCon();
		Order order = new Order();
		try {
			ps=con.prepareStatement(query_order_by_oId);
			ps.setInt(1, oId);
			rs = ps.executeQuery();
			while (rs.next()) {
				order.setoId(rs.getInt("orderId"));
				order.setSeatId(rs.getInt("seatId"));
				order.setsId(rs.getInt("sId"));
				order.setuId(rs.getInt("uId"));
				order.setOrderPrice(rs.getString("orderPrice"));
				order.setBuyDate(rs.getDate("buyDate"));
				order.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return order;
	}

	//更改订单状态
	public boolean UpdateStatus(String new_status, int oId) {
		openCon();
		boolean bool=false;
		try {
			ps=con.prepareStatement(change_status);
			ps.setString(1, new_status);
			ps.setInt(2, oId);
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
}
