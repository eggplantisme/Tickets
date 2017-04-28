package bean;

import java.sql.Date;

public class Order {
	private int oId;
	private int seatId;
	private int sId;
	private int uId;
	private String orderPrice;
	private Date buyDate;
	private String status;
	/**
	 * @return the oId
	 */
	public int getoId() {
		return oId;
	}
	/**
	 * @param oId the oId to set
	 */
	public void setoId(int oId) {
		this.oId = oId;
	}
	/**
	 * @return the seatId
	 */
	public int getSeatId() {
		return seatId;
	}
	/**
	 * @param seatId the seatId to set
	 */
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	/**
	 * @return the sId
	 */
	public int getsId() {
		return sId;
	}
	/**
	 * @param sId the sId to set
	 */
	public void setsId(int sId) {
		this.sId = sId;
	}
	/**
	 * @return the uId
	 */
	public int getuId() {
		return uId;
	}
	/**
	 * @param uId the uId to set
	 */
	public void setuId(int uId) {
		this.uId = uId;
	}
	/**
	 * @return the orderPrice
	 */
	public String getOrderPrice() {
		return orderPrice;
	}
	/**
	 * @param orderPrice the orderPrice to set
	 */
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	/**
	 * @return the buyDate
	 */
	public Date getBuyDate() {
		return buyDate;
	}
	/**
	 * @param buyDate the buyDate to set
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
