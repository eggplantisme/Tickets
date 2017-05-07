package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Comment;

public class CommentDao extends Data_Built {
	private String create_comment_table = "create table if not exists commentInfo(commentId int AUTO_INCREMENT primary key, uName TEXT, mId int, commentText TEXT)";
	String add_comment = "insert into commentInfo(uName, mId, commentText) values (?, ?, ?)";
	String query_comments = "select * from commentInfo where mId = ?";
	String delete_comment = "delete from commentInfo where commentId = ?";
	
	public CommentDao() {
		openCon();
		try {
			ps = con.prepareStatement(create_comment_table);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closePs();
			this.closeCon();
		}
	}
	
	public int AddComment(Comment comment) {
		int cId = 0;
		openCon();
		try {
			ps= con.prepareStatement(add_comment);
			ps.setString(1, comment.getUserName());
			ps.setInt(2, comment.getmId());
			ps.setString(3, comment.getCommentText());
			int num = ps.executeUpdate();
			if(num>0){
				ps = con.prepareStatement("select @@IDENTITY");
				rs = ps.executeQuery();
				while (rs.next())
					cId = rs.getInt("@@IDENTITY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closePs();
			this.closeCon();
		}
		return cId;
	}
	public List<Comment> getAllComments(int mId) {
		openCon();
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			ps=con.prepareStatement(query_comments);
			ps.setInt(1, mId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt("commentId"));
				comment.setmId(rs.getInt("mId"));
				comment.setUserName(rs.getString("uName"));
				comment.setCommentText(rs.getString("commentText"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeRs();
			this.closePs();
			this.closeCon();
		}
		return comments;
	}
	public boolean DeleteComment(int commentId) {
		openCon();
		boolean bool=false;
		try {
			ps= con.prepareStatement(delete_comment);
			ps.setInt(1,commentId);
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
}
