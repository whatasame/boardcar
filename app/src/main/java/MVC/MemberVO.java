// 나중에 폴더 옮기고 package 다시설정!!!
package MVC;
/*
 * Author : 박성현
 * resent update : 11-21
 *
 * note
 * 문서에 없는 UserVO 작성, oracle은 boolean이 없는 관계로 int형으로 변경,
 *
 */
public class MemberVO {
    private final String mid;
    private final String password;
    private final String mname;
    private final String email;
    private final int isAdmin;
    private final int cid;

    public MemberVO(String mid, String password, String mname, String email, int isAdmin, int cid) {
        this.mid = mid;
        this.password = password;
        this.mname = mname;
        this.email = email;
        this.isAdmin = isAdmin;
        this.cid = cid;
    }

    public String getMid() { return mid; }
    public String getPassword() { return password; }
    public String getMname() { return mname; }
    public String getEmail() { return email; }
    public int getIsAdmin(){ return isAdmin; }
    public int getCid(){ return cid; }
}
