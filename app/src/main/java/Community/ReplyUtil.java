package Community;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class ReplyUtil {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    public Context context;

    /*
    * 1. pid를 통해 해당 게시글의 댓글을 List형태로 모두 불러온다
    * 2. pid를 통해 해당 게시글에 댓글을 추가한다.
    * 3. pid를 통해 해당 댓글을 수정한다.
    * 4. pid를 통해 해당 댓글을 삭제한다.
    *
    *
    *
    * */
}
