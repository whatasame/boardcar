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
import Community.BoardInfo;

/**
 * @todo Register와 SelectCar에서 CarMap 작동될 시 Class Diagram 작성
 */
public class CarMap {
    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    private Context context;
    HashMap<String, Integer> carMap;
    public CarMap(Context context) {
        this.context = context;
        carMap = new HashMap<>();
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
                carMap.put(json.getString("NAME"),json.getInt("CID"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public int getCidByName(String name){
        return carMap.get(name);
    }
}
