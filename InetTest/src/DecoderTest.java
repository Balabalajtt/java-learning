import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by 江婷婷 on 2017/8/3.
 */
public class DecoderTest {
    public static void main(String[] args) throws IOException {
        String keyWord = URLDecoder.decode("%E7%96%AF%E7%8B%82Java", "utf-8");
        System.out.println(keyWord);
        String urlStr = URLEncoder.encode("看看这个", "GBK");
        System.out.println(urlStr);
        String path = "https://developer.android.com/design/media/hero-material-design.png";
        String targetFile = "E:\\";
        DownUtil downUtil = new DownUtil(path, targetFile, 5);
        downUtil.download();
        new Thread(() -> {
            
        })
    }
}
