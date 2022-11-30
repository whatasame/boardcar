package Community;

import org.json.JSONException;
import org.json.JSONObject;

public class ReplyInfo {
    private int RID;
    private String MID;
    private int PID;
    private String BODY;
    private int UPVOTE;
    private int DOWNVOTE;

    public ReplyInfo(JSONObject jsonObject) {
        try {
            this.MID = jsonObject.getString("MID");
            this.DOWNVOTE = jsonObject.getInt("DOWNVOTE");
            this.PID = jsonObject.getInt("PID");
            this.BODY = jsonObject.getString("BODY");
            this.UPVOTE = jsonObject.getInt("UPVOTE");
            this.DOWNVOTE = jsonObject.getInt("DOWNVOTE");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getBODY() {
        return BODY;
    }

    public void setBODY(String BODY) {
        this.BODY = BODY;
    }

    public int getUPVOTE() {
        return UPVOTE;
    }

    public void setUPVOTE(int UPVOTE) {
        this.UPVOTE = UPVOTE;
    }

    public int getDOWNVOTE() {
        return DOWNVOTE;
    }

    public void setDOWNVOTE(int DOWNVOTE) {
        this.DOWNVOTE = DOWNVOTE;
    }
}
