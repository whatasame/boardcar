// 나중에 폴더 옮기고 package 다시설정!!!
package MVC;
/*
 * Author : 박성현
 * resent update : 11-21
 *
 * note
 * Document와 달리 uploadPost 인자 추가
 * DB table 설정으로 인한 (auto-incresement) insert문 변경 (11-20)
 *
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PostDAO {
    private Connection conn;
    private Statement stmt;
    PostDAO(String DBUrl){
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
    int uploadPost(PostVO post){
		/*upload시 pid는 생성하지 않아도 됩니다!(sequence 사용)*/
    	try {
    		PreparedStatement insertQuery = conn.prepareStatement(
        			"Insert INTO POST(PID, MID, POST_TITLE, POST_BODY, POST_GOOD, POST_BAD, TYPE) VALUES(PCOUNTER.NEXTVAL,?,?,?,?,?,?)");
        	insertQuery.setString(1, post.getTitle()); //Title
			insertQuery.setString(2, post.getMid()); // Mid
        	insertQuery.setString(3, post.getBody());	//Body
        	insertQuery.setInt(4, post.getPostGood());	//good
        	insertQuery.setInt(5, post.getPostBad());	//bad
        	insertQuery.setString(6, post.getPostType());
			return insertQuery.executeUpdate();
			} catch (SQLException e) {
				System.out.println("uploadPost Error : "+e);
				return -1;
		}
    }
    int editPost(int pid, String body ){
    	/*글 수정 case 사용*/
    	String editQuery = "Update POST SET POST_BODY = "+ body + "where pid ="+pid;
    	try {
			return stmt.executeUpdate(editQuery);
		} catch (SQLException e) {
			System.out.println("editPost Error : "+e);
			return -1;
		}
    }
    int deletePost(int pid){
    	String deleteQuery = "Delete FROM POST WHERE PID ="+pid;
    	try {
			return stmt.executeUpdate(deleteQuery);
		} catch (SQLException e) {
			System.out.println("deletePost Error : "+e);
			return -1;
		}
    }
    List<PostVO> searchPost(String title, String postType){
    	String searchQuery = "Select PID from POST where POST_TITLE =" + title +"and TYPE ="+postType;
    	try {
			ResultSet rs = stmt.executeQuery(searchQuery);
			List<PostVO> listPostVO = new ArrayList<PostVO>();
			while(rs.next())
			{
				PostVO post = new PostVO(rs.getInt("PID"),rs.getString("POST_TITLE"), rs.getString("POST_BODY"),
						rs.getString("MID"), rs.getString("POST_TYPE") , rs.getInt("POST_GOOD"),
						rs.getInt("POST_BAD"));
				listPostVO.add(post);
			}
			return listPostVO;
		} catch (SQLException e) {
			System.out.println("searchPost Error: "+e );
			return null;
		}
    }
    void sortPost(List<PostVO> listPostVO){
		/* 인자인 PostVO PID기준으로 리스트를 정렬시킨다.
		* 내림차순으로(높은 PID가 가장 앞 index) 리스트 생성*/
		listPostVO.sort(new Comparator<PostVO>(){
			public int compare(PostVO o1, PostVO o2) {
				if(o1.getPid() > o2.getPid()) return -1;
				else if(o1.getPid() < o2.getPid()) return 1;
				else return 0;
			}
		});
	}


}
