import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by 江婷婷 on 2017/8/5.
 */
public class GetPostTest {

    /**
     * 向指定的 URL 发送 GET 方式的请求
     * @param url 发送请求的 URL
     * @param param 请求参数 格式：name1 = value1 & name2 = value2
     * @return URL代表远程资源的响应
     */
    public static String sendGet (String url, String param) {

        String result = "";
        String urlName = url + "?" + param;

        try {
            URL realUrl = new URL(urlName);
            URLConnection urlConnection = realUrl.openConnection();

            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent"
                    , "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            urlConnection.connect();

            Map<String, List<String>> map = urlConnection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            try(
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream(), "utf-8")))
            {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += "\n" + line;
                }
            }

        } catch (IOException e) {
            System.out.println("发送GET请求发生异常" + e);
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) {
        String s = GetPostTest.sendGet("http://localhost:8888/abc/a.jsp", null);
        System.out.println(s);
    }
}
