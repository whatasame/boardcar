package LoginPackage;

import org.json.JSONException;
import org.json.JSONObject;

public class MemberInfo {
    private String MID = null;
    private String NAME = null;
    private String PASSWORD = null;
    private String EMAIL = null;
    private int CID = 0;  // 초기화값
    MemberInfo(JSONObject jsonObject){
        try {
            this.MID = jsonObject.getString("MID");
            this.NAME = jsonObject.getString("NAME");
            this.PASSWORD = jsonObject.getString("PASSWORD");
            this.EMAIL = jsonObject.getString("EMAIL");
            this.CID = jsonObject.getInt("CID");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getMID() {
        return MID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public int getCID() {
        return CID;
    }

}
