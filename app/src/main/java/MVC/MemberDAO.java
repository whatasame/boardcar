// 나중에 폴더 옮기고 package 다시설정!!!
package MVC;
/*
 * Author : 박성현
 * resent update : 11-21
 *
 * note
 * 문서에 없는 UserDAO 작성
 * USERVO, USERDAO -> MEMBERVO, MEMBERDAO로 변경
 *
 * 이윤상 2차 수정일 : 11-21
 * 수정 내용
 * 1. MemberDAO 생성자 public으로 수정
 * 2. getConnection으로 연결할 때 DBUrl 이외에 DBId, DBPw 추가 완료
 *
 */
import java.sql.*;

public class MemberDAO {
    private Connection conn;
    private Statement stmt;
    public MemberDAO(String DBUrl, String DBId, String DBPw){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // driver loading
            conn = DriverManager.getConnection(DBUrl, DBId, DBPw); // db connect + 나중에 DB id, password 입력?
            stmt = conn.createStatement();
        }
        catch(ClassNotFoundException e){
            System.out.println("DB드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("MemberDAO 생성자에러 :"+ e);
        }
    }
    public int uploadMember(MemberVO member){
        try {
            PreparedStatement insertQuery = conn.prepareStatement(
                    "Insert INTO MEMBER(MID, PASSWORD, MNAME, EMAIL, IS_ADMIN, CID) VALUES(?,?,?,?,?,?)");
            insertQuery.setString(1, member.getMid());
            insertQuery.setString(2, member.getPassword());
            insertQuery.setString(3, member.getMname());
            insertQuery.setString(4, member.getEmail());
            insertQuery.setInt(5, 0); //회원가입은 유저만
            insertQuery.setInt(6, member.getCid());
            return insertQuery.executeUpdate();
        } catch (SQLException e) {
            System.out.println("uploadMember Error : "+e);
            return -1;
        }
    }
    public int editMemberCname(String mid, String cname){
        /* usecase 차량 변경에 사용
        * mid의 Cname 이름으로 cid를 변경 */
        String editQuery = "update Member set cid = (select cid from car where cname = " + cname +" ) where mid = " + mid;
        try {
            return stmt.executeUpdate(editQuery);
        } catch (SQLException e) {
            System.out.println("editMemberCID Error : "+e);
            return -1;
        }
    }
    public int editMemberPassword(String mid, String password){
        /*Password 변경시 사용*/
        String editQuery = "Update MEMBER Set password = "+password+" where mid =" + mid;
        try {
            return stmt.executeUpdate(editQuery);
        } catch (SQLException e) {
            System.out.println("editMemberPassword Error :"+e);
            return -1;
        }
    }
    public MemberVO getMemberById(String mid){
        /* mid를 인자로 사용하여 MemberVO 리턴
        * 사용처 : 비번찾기, 로그인 */
        String sql = ("Select * from MEMBER Where MID =" + mid);
        try {
            ResultSet rset = stmt.executeQuery(sql);
            rset.next();
            MemberVO member = new MemberVO(rset.getString("MID"),rset.getString("PASSWORD"),
                    rset.getString("MNAME"), rset.getString("EMAIL"),
                    rset.getInt("IS_ADMIN"), rset.getInt("CID"));
            return member;
        }catch (SQLException e) {
            System.out.println("getMemberByID Error" + e);
            return null;
        }
    }
    public MemberVO getMemberByEmail(String email){
        /* email을 인자로 사용하여 MemberVO 리턴
        * 사용처 : ID찾기, 비밀번호찾기 */
        String sql = ("Select * from MEMBER Where EMAIL =" + email);
        try {
            ResultSet rset = stmt.executeQuery(sql);
            rset.next();
            MemberVO member = new MemberVO(rset.getString("MID"), rset.getString("PASSWORD"),
                    rset.getString("MNAME"), rset.getString("EMAIL"),
                    rset.getInt("IS_ADMIN"),rset.getInt("CID") );
            return member;
        }catch (SQLException e) {
            System.out.println("getMemberByEmail Error" + e);
            return null;
        }
    }
}
