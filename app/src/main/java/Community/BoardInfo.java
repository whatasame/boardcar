package Community;

public class BoardInfo {
    private String MID;
    private int DOWNVOTE;
    private String PDATE;
    private int PID;
    private String TITLE;
    private String BODY;
    private String TYPE;
    private int UPVOTE;

    public BoardInfo(String MID, int DOWNVOTE, String PDATE, int PID, String TITLE, String BODY,
                     String TYPE, int UPVOTE) {
        this.MID = MID;
        this.DOWNVOTE = DOWNVOTE;
        this.PDATE = PDATE;
        this.PID = PID;
        this.TITLE = TITLE;
        this.BODY = BODY;
        this.TYPE = TYPE;
        this.UPVOTE = UPVOTE;
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
    public String getTYPE(){
        return TYPE;
    }
    public int getUPVOTE() {
        return UPVOTE;
    }


}
