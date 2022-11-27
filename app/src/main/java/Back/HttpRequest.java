package Back;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    /* << HttpRequest Example >>
     * POST /path HTTP/1.1
     * Content-Type: text/html;charset=utf-8
     * Host: boardcar
     * Content-Length: 12
     *
     * Hello World!
     * */

    /* start-line */
    String method;
    String path;
    String version;

    /* headers */
    Map<String, String> headers;

    /* body */
    String body;

    /* Constructor */
    public HttpRequest(String method, String path, String version, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.headers = headers;

        this.body = body;
        putHeader("Content-Length", String.valueOf(body.getBytes(StandardCharsets.UTF_8).length));

    }
    public void putHeader(String header, String value){
        headers.put(header,value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        /* status-line */
        stringBuilder.append(method).append(" ").append(path).append(" ").append(version).append("\n");

        /* headers */
        headers.forEach((header, value) -> {
            stringBuilder.append(header).append(": ").append(value).append("\n");
        });
        stringBuilder.append("\n");

        /* body */
        stringBuilder.append(body);

        return stringBuilder.toString();
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}