package Community;
/*
 * update date : 11-29
 * 글의 정보 하나를 담고 있는 BoardInfo
 * 기능은 BoardVO라고 생각해서 사용하면 됩니다.
 */

import org.json.JSONException;
import org.json.JSONObject;

public class BoardInfo {
    private String MID;
    private int DOWNVOTE;
    private String PDATE;
    private int PID;
    private String TITLE;
    private String BODY;
    private String TYPE;
    private int UPVOTE;

    // JSONObject로 info 만들기
    public BoardInfo(JSONObject jsonObject) {
        try {
            this.MID = jsonObject.getString("MID");
            this.DOWNVOTE = jsonObject.getInt("DOWNVOTE");
            this.PDATE = jsonObject.getString("PDATE");
            this.PID = jsonObject.getInt("PID");
            this.TITLE = jsonObject.getString("TITLE");
            this.BODY = jsonObject.getString("BODY");
            this.TYPE = jsonObject.getString("TYPE");
            this.UPVOTE = jsonObject.getInt("UPVOTE");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMID() {
        return MID;
    }

    public int getDOWNVOTE() {
        return DOWNVOTE;
    }

    public String getPDATE() {
        return PDATE;
    }

    public int getPID() {
        return PID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getBODY() {
        return BODY;
    }

    public String getTYPE() {
        return TYPE;
    }

    public int getUPVOTE() {
        return UPVOTE;
    }


}
