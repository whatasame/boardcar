package Back;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * @// TODO: 2022-11-30 API 추가 내용 최정규 선배님께 전송해서 API 받기 차이름
 */
public class SessionManager {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};

    public Context context;

    private String MID = null;
    private String NAME = null;
    private String EMAIL = null;
    private int CID = 0;  // 초기화값
    private boolean IS_ADMIN = false;
    private String CarName = null;
    public String session;

    public SharedPreferences sharedPreferences;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LOGIN_INFO", MODE_PRIVATE);
        session = sharedPreferences.getString("UUID", "");
    }

    /**
     * local에 있는 SharedPreferences에 세션Key를 이용하여 body를 리턴 및 myinfo설정
     * @return body {String}
     */
    public String getUserInfo() {
        HttpRequest infoRequest = new HttpRequest("GET", "/myInfo", version, headers, "");
        infoRequest.putHeader("Session-Key", session);
        HttpClient httpClient = new HttpClient(infoRequest, context);
        httpClient.start();

        try {
            httpClient.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpResponse infoResponse = httpClient.getHttpResponse();
        System.out.println("status code : " + infoResponse.getStatusCode());

        if (!infoResponse.getStatusCode().equals("200")) {
            //실패한 경우
            return null;
        } else {
            String body = infoResponse.getBody();
            try {
                JSONObject jsonObject = new JSONObject(body);
                this.MID = jsonObject.getString("MID");
                this.NAME = jsonObject.getString("NAME");
                this.EMAIL = jsonObject.getString("EMAIL");
                this.IS_ADMIN = jsonObject.getBoolean("IS_ADMIN");
                this.CID = jsonObject.getInt("CID");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            setCarName();
            return body;
        }
    }

    public String getMID() {
        return MID;
    }

    public String getNAME() {
        return NAME;
    }

    private void setEMAIL(String input) {
        String[] firstSplit = input.split(",");
        String[] secondSplit = firstSplit[4].split(":");
        EMAIL = secondSplit[1].replaceAll("\"", "");
    }
    public String getEMAIL(){
        return EMAIL;
    }


    public int getCID() {
        return CID;
    }
    private void setCarName() {
        Map<String, String> carHeaders = new HashMap<String, String>() {{
            put("Content-Type", "text/html;charset=utf-8");
        }};
        //API 사용해서 cid를 통해 CarName를 받아오기
        try {
            JSONObject carJsonObject = new JSONObject();
            carJsonObject.put("CID",getCID());
            HttpRequest carNameHttpRequest = new HttpRequest("PUT", "/getCarByCid",
                    version,carHeaders,carJsonObject.toString());
            HttpClient carNameHttpClient = new HttpClient(carNameHttpRequest, context);
            carNameHttpClient.start();
            carNameHttpClient.join();
            HttpResponse carNameHttpResponse = carNameHttpClient.getHttpResponse();
            JSONObject carNameJsonObject = new JSONObject(carNameHttpResponse.getBody());
            CarName = carNameJsonObject.getString("NAME");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getCarName(){
        return CarName;
    }



}
