import java.io.IOException;
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
        String path = "http://upload-images.jianshu.io/upload_images/1187237-1b4c0cd31fd0193f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";
        String targetFile = "e:/context.png";
        DownUtil downUtil = new DownUtil(path, targetFile, 5);
        downUtil.download();
        new Thread(() -> {
            while (downUtil.getCompleteRate() < 1) {
                System.out.println("已完成：" + downUtil.getCompleteRate());
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {}

            }

        }).start();
    }
}
