package Back;


import android.content.Context;
import android.content.res.AssetManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class HttpClient extends Thread{

    private HttpResponse httpResponse;
    private HttpRequest httpRequest;
    private Context context;

    public HttpClient(HttpRequest httpRequest, Context context){
        this.context = context;
        this.httpRequest = httpRequest;
    }

    public String sessionKey = null;

    /**
     * HttpClient에 생성자 실행 후 실행
     * @return {boolean} true : 연결 원할 / false : 연결 불안정
     */
    public boolean httpTest() {
        /* GET TEST */
        String version = "HTTP/1.1";
        Map<String, String> headers = new HashMap<String, String>() {{
            put("Content-Type", "text/html;charset=utf-8");
        }};
        httpRequest = new HttpRequest("GET", "/httpTest", version, headers, "");


        try {
            start();
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("*********************************");
        System.out.println("httpTesting Result");
        System.out.println("Response Status : " + httpResponse.getStatusCode() + httpResponse.getStatusText());
        System.out.println(httpResponse.getBody());
        System.out.println("*********************************");
        if(httpResponse.getStatusCode().equals("200"))
            return true;
        else
            return false;
    }

    public HttpResponse sendHttpRequest(HttpRequest httpRequest, Context context) throws IOException {

        //http.properties 불러오기
        AssetManager assetManager = context.getAssets();


        // 서버 정보 가져오기
        Properties properties = new Properties();
        properties.load(assetManager.open("http.properties"));
        String SERVER_IP = properties.getProperty("SERVER_IP");
        final int SERVER_PORT = Integer.parseInt(properties.getProperty("SERVER_PORT"));

        // HTTP 통신
        try {
            // 클라이언트 소켓 열기
            Socket socket = new Socket(SERVER_IP, SERVER_PORT); // 서버 연결

            // 서버에 Request 보내기
            socket.getOutputStream().write(httpRequest.toString().getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();

            // 서버로부터 Response 받기
            HttpResponse httpResponse = responseBuilder(socket.getInputStream());

            // 클라이언트 소켓 닫기
            socket.close();
            return httpResponse;

        }catch (ConnectException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static HttpResponse responseBuilder(InputStream inputStream) throws IOException {

        // HTTP 요청 전체 읽기
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        while (!(inputLine = myReadLine(inputStream)).equals("")) {
            stringBuilder.append(inputLine).append("\n"); // sb : HTTP 요청 전체
        }

        // HTTP 요청 한 줄씩 처리
        String response = stringBuilder.toString();
        String[] responseArr = response.split("\n");

        // 헤더 - 요청 부분 parse
        String[] statusLine = responseArr[0].split(" ");
        String version = statusLine[0];
        String statusCode = statusLine[1];
        String statusText = statusLine[2];

        // 남은 헤더 parse
        Map<String, String> headers = new HashMap<>();
        for (int i = 1; i < responseArr.length; ++i) {
            if (responseArr[i].equals("")) { // 아무것도 없는 줄을 만난다 -> 헤더의 끝을 만남
                break;
            }

            // 헤더의 (키:값)을 해시맵에 저장
            String[] temp = responseArr[i].split(":");
            headers.put(temp[0].trim(), temp[1].trim());
        }

        // 바디 parse
        String body = null;
        int contentLength = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
        if (contentLength > 0) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < contentLength; i++) {
                byteArrayOutputStream.write(inputStream.read());
            }
            body = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        }

        return new HttpResponse(version ,statusCode, statusText, headers, body);
    }

    private static String myReadLine(InputStream inputStream) throws IOException {
        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
        while (true) {
            int b = inputStream.read();

            if (b == '\n' || b == '\0') {
                break;
            }

            tmp.write(b);
        }
        return new String(tmp.toByteArray(), StandardCharsets.UTF_8).trim();
    }

    public void run(){ //Thread 돌아가기 시작해유
        try {
            httpResponse = sendHttpRequest(httpRequest, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse getHttpResponse(){
        return httpResponse;
    }

}




















//class TestMethod {
//
//    private static String version = "HTTP/1.1";
//    private static Map<String, String> headers = new HashMap<String, String>(){{
//        put("Content-Type", "text/html;charset=utf-8");
//    }};
//
//    public static void getTest_httpTest() throws IOException {
//        /* GET TEST */
//        HttpRequest testRequest = new HttpRequest("GET", "/httpTest", version, headers, "");
//
//        HttpResponse testResponse = HttpClient.sendHttpRequest(testRequest);
//        System.out.println("Response Status : " + testResponse.getStatusCode());
//        System.out.println(testResponse.getBody());
//        System.out.println();
//    }
//
//    public static void postTest_login() throws IOException {
//        /* POST TEST */
//
//        // 로그인 정보 JSON 생성
//        JSONObject loginJson = new JSONObject();
//        loginJson.put("id", "testid");
//        loginJson.put("password", "testpw");
//        String loginInfo = loginJson.toString();
//`
//        HttpRequest loginRequest = new HttpRequest("POST", "/login", version, headers, loginInfo);
//
//        HttpResponse loginResponse = HttpClient.sendHttpRequest(loginRequest);
//        if (loginResponse.getStatusCode().equals("200")) { //Login Success!
//            HttpClient.sessionKey = loginResponse.getHeaders().get("Session-Key");
//        }
//
//        System.out.println("Response Status : " + loginResponse.getStatusCode());
//        System.out.println(loginResponse.getBody());
//        System.out.println("Session-Key is " + HttpClient.sessionKey);
//        System.out.println();
//    }
//
//    public static void getTest_member() throws IOException {
//        /* GET TEST - 멤버 테이블 전체 가져오기 */
//
//        HttpRequest memberListRequest = new HttpRequest("GET", "/member", version, headers, "");
//        memberListRequest.putHeader("Session-Key", HttpClient.sessionKey);
//
//        HttpResponse memberListResponse = HttpClient.sendHttpRequest(memberListRequest);
//
//        System.out.println("Response Status : " + memberListResponse.getStatusCode());
//        System.out.println(memberListResponse.getBody());
//        System.out.println();
//    }
//}
