// 나중에 폴더 옮기고 package 다시설정!!!
package MVC;
/*
 * Author : 박성현
 * resent update : 11-21
 *
 * note
 * uid -> mid로 수정
 *
 */
public class ReplyVO {
    private int rid;
    private String mid;
    private int pid;
    private String body;
    private int replyGood;
    private int replyBad;

    public ReplyVO(int rid, String mid, int pid, String body, int replyGood, int replyBad) {
        this.rid = rid;
        this.mid = mid;
        this.pid = pid;
        this.body = body;
        this.replyGood = replyGood;
        this.replyBad = replyBad;
    }

    public int getRid() { return rid; }
    public String getMid() { return mid; }
    public int getPid() { return pid; }
    public String getBody() { return body; }
    public int getReplyGood() { return replyGood; }
    public int getReplyBad() { return replyBad; }
}
