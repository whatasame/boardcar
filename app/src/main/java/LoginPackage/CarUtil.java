package LoginPackage;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;

/**
 * @todo Register와 SelectCar에서 CarUtil 작동될 시 Class Diagram 작성
 */
public class CarUtil {
    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    private Context context;
    HashMap<String, Integer> CarUtil;
    public CarUtil(Context context) {
        this.context = context;
        CarUtil = new HashMap<>();
        HttpRequest httpRequest = new HttpRequest("GET","/getCarList",version,headers,"");
        HttpClient httpClient = new HttpClient(httpRequest, context);
        httpClient.start();
        try {
            httpClient.join();
            HttpResponse httpResponse = httpClient.getHttpResponse();
            if(!httpResponse.getStatusCode().equals("200")) // network error
            {
                System.out.println("*** CarList Error ***");
                System.out.println(httpResponse.getStatusCode()+httpResponse.getStatusText());
                System.out.println(httpResponse.getBody());
            }
            JSONArray jsonArray = new JSONArray(httpResponse.getBody());
            for (int i = 0; i < jsonArray.length(); i++) {
                // JSONArray -> JSONObject
                JSONObject json = new JSONObject(jsonArray.getString(i));
                CarUtil.put(json.getString("NAME"),json.getInt("CID"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public boolean updateMemberCar(String mid, int cid, String UUID){
        Map<String, String> updateHeaders = new HashMap<String, String>() {{
            put("Content-Type", "text/html;charset=utf-8");
        }};
        updateHeaders.put("Session-Key",UUID);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("MID",mid);
            jsonObject.put("NAME",cid);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            HttpRequest httpRequest = new HttpRequest("POST", "/changeCar",version,
                    updateHeaders,jsonObject.toString());
            HttpClient httpClient = new HttpClient(httpRequest,context);
            httpClient.start();
            httpClient.join();
            HttpResponse httpResponse = httpClient.getHttpResponse();
            if(httpResponse.getStatusCode().equals("200"))
                return true;
            else{
                System.out.println("*** updateMemberCar Error ***");
                System.out.println(httpResponse.getStatusCode()+" "+httpResponse.getStatusText());
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * @param name {String} 차량모델
     * @return {int} 차량모델의 CID
     */
    public int getCidByName(String name){
        return CarUtil.get(name);
    }
}
