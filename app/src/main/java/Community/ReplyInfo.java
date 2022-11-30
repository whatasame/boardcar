package Community;

import org.json.JSONException;
import org.json.JSONObject;

public class ReplyInfo {
    private int RID;
    private String MID;
    private int PID;
    private String BODY;

    public ReplyInfo(JSONObject jsonObject) {
        try {
            this.RID = jsonObject.getInt("RID");
            this.MID = jsonObject.getString("MID");
            this.PID = jsonObject.getInt("PID");
            this.BODY = jsonObject.getString("BODY");
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
}
