// 나중에 폴더 옮기고 package 다시설정!!!
package MVC;
/*
 * Author : 박성현
 * resent update : 11-21
 *
 * note
 * 게시글 하나의 데이터를 저장하는 클래스
 * uid -> mid 수정
 */
import java.util.List;

public class PostVO {
    private int pid; // pid
    private String title;	// 게시글 제목
    private String body;	// 게시글 내용
    private String mid;		// 게시글 작성자
    private String postType;// 게시글 종류
    // private List<ReplyVO> listReply;	// 게시글에 달린 댓글 리스트 이게 필요가 있을까..?
    private int postGood;	// 추천 수
    private int postBad;	// 비추천 수
    public PostVO(int pid, String title, String body, String mid, String postType, int postGood, int postBad) {
        this.pid = pid;
        this.title = title;
        this.body = body;
        this.mid = mid;
        this.postType = postType;
        this.postGood = postGood;
        this.postBad = postBad;
    }
    public int getPid() {return pid;}
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public String getMid() {
        return mid;
    }
    public String getPostType() {
        return postType;
    }
    public int getPostGood() {
        return postGood;
    }
    public int getPostBad() {
        return postBad;
    }


}
