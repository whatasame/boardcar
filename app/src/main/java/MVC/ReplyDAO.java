// 나중에 폴더 옮기고 package 다시설정!!!
package MVC;
/*
 * Author : 박성현
 * resent update : 11-21
 * note
 * document와 다르게 많이 수정됨. (ex :인자 추가 )
 * DB table 설정으로 인한 (auto-incresement) insert문 변경 (11-20)
 * oracle 환경변경으로 인하여 sequence로 변경
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReplyDAO {
    private Connection conn;
    private Statement stmt;
    ReplyDAO(String DBUrl){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // driver loading
            conn = DriverManager.getConnection(DBUrl); // db connect + 나중에 DB id, password 입력하는지??
            stmt = conn.createStatement();
        }
        catch(ClassNotFoundException e){
            System.out.println("DB드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러 :"+ e);
        }
    }
	List<ReplyVO> getReplyListByPid(int pid){
		// pid로 replylist를 찾아옴 -> 게시판 열람시 사용해야함!
		String query = "SELECT * FROM REPLY WHERE pid ="+pid;
		ArrayList<ReplyVO> replyList = new ArrayList<>();
		try {
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next())
			{
				replyList.add(new ReplyVO(rset.getInt("RID"), rset.getString("MID"), rset.getInt("PID"),
						rset.getString("REPLY_BODY"),rset.getInt("REPLY_GOOD"), rset.getInt("REPLY_BAD")));
			}
			return replyList;
		} catch (SQLException e) {
			System.out.println("getReplyListByPid Error :"+e);
			return null;
		}
	}
    int uploadReply(ReplyVO reply){
		/*upload시 rid는 생성하지 않아도 됩니다!(sequence 사용)*/
    	try {
    		PreparedStatement insertQuery = conn.prepareStatement(
        			"Insert INTO REPLY(RID, MID, PID, REPLY_BODY,REPLY_GOOD, REPLY_BAD) VALUES(RCOUNTER.NEXTVAL, ?,?,?,?,?)");
        	insertQuery.setString(1, reply.getMid());
        	insertQuery.setInt(2, reply.getPid());
			insertQuery.setString(3, reply.getBody());
        	insertQuery.setInt(4, reply.getReplyGood());
			insertQuery.setInt(5, reply.getReplyBad());
			return insertQuery.executeUpdate();
		} catch (SQLException e) {
			System.out.println("uploadReply Error : "+e);
			return -1;
		}
    }
    int editReply(int rid, String body){
		String editQuery = "Update REPLY Set REPLY_BODY ="+ body +"where RID =" + rid; //update SQL 문 나중에 추가할 것
    	try {
			return stmt.executeUpdate(editQuery);
		} catch (SQLException e) {
			System.out.println("editReply Error : "+e);
			return -1;
		}
    }
    int deleteReply(int rid){
    	//해당 rid를 삭제
    	String deleteQuery = "Delete From REPLY WHERE RID =" + rid;
    	try {
			return stmt.executeUpdate(deleteQuery);
		} catch (SQLException e) {
			System.out.println("deleteReply Error : "+e);
			return -1;
		}
    }

}
