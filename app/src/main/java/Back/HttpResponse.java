package Back;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpResponse {

    /* << HttpRequest Example >>
     * HTTP/1.1 200 OK
     * Content-Type: text/html;charset=utf-8
     * Server: boardcar-server
     * Content-Length: 8
     *
     * Welcome!
     * */

    /* status-line */
    String version;
    String statusCode;
    String statusText;

    /* headers */
    Map<String, String> headers;

    /* body */
    String body;

    /* Constructor */
    public HttpResponse(String version, String statusCode, String statusText, Map<String, String> headers, String body) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.headers = headers;
        this.body = body;

        if(!body.equals("")){
            this.putHeader("Content-Length", String.valueOf(body.getBytes(StandardCharsets.UTF_8).length));
        }
    }

    /* Static factory */
    public static HttpResponse ok(Map<String,String> headers, String body){
        return new HttpResponse("HTTP/1.1", "200", "OK", headers, body);
    }

    public static HttpResponse badRequest(Map<String,String> headers, String body){
        return new HttpResponse("HTTP/1.1", "400", "Bad Request", headers, body);
    }

    public static HttpResponse notFound(Map<String,String> headers, String body){
        return new HttpResponse("HTTP/1.1", "404", "Not Found", headers, body);
    }

    public void putHeader(String header, String value){
        headers.put(header,value);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        /* status-line */
        stringBuilder.append(version).append(" ").append(statusCode).append(" ").append(statusText).append("\n");

        /* headers */
        headers.forEach((header, value) -> {
            stringBuilder.append(header).append(": ").append(value).append("\n");
        });
        stringBuilder.append("\n");

        /* body */
        stringBuilder.append(body);

        return stringBuilder.toString();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}