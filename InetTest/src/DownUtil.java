import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 江婷婷 on 2017/8/3.
 */
public class DownUtil {
    private String path;//下载资源路径
    private String targetFile;//保存路径
    private int threadNum;//线程个数
    private DownThread[] threads;//线程对象
    private int fileSize;//文件总大小

    //多线程下载类构造器
    public DownUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        threads = new DownThread[threadNum];//初始线程数组
    }

    public void download() throws IOException {
        URL url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setConnectTimeout(5*1000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty(
                "Accept",
                "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                + ""
        );
        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
        httpURLConnection.setRequestProperty("Charset", "utf-8");
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");

        fileSize = httpURLConnection.getContentLength();
        httpURLConnection.disconnect();
        int currentPartSize = fileSize / threadNum + 1;

        //创建本地文件
        RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile, "rw");
        randomAccessFile.setLength(fileSize);
        randomAccessFile.close();

        //计算每个线程的开始位置，创建每个线程并开始
        for (int i = 0; i < threadNum; i++) {
            int startPos = i * currentPartSize;
            RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
            currentPart.seek(startPos);
            threads[i] = new DownThread(startPos, currentPartSize, currentPart);
            threads[i].start();
        }
    }

    //获取下载进度
    public double getCompleteRate() {
        int sumSize = 0;
        for(int i = 0; i < threadNum; i++) {
            sumSize += threads[i].length;
        }
        return sumSize * 1.0 / fileSize;
    }

    //下载线程
    private class DownThread extends Thread{
        private int startPos;                   //当前下载位置
        private int currentPartSize;            //负责下载的文件块大小
        private RandomAccessFile currentPart;   //当前线程需要下载的文件块
        public int length;                      //已下载的字节数

        public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setConnectTimeout(5*1000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty(
                        "Accept",
                        "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                                + ""
                );
                httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
                httpURLConnection.setRequestProperty("Charset", "utf-8");

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.skip(this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                while (length < currentPartSize && (hasRead = inputStream.read(buffer)) != -1) {
                    currentPart.write(buffer, 0, hasRead);
                    length += hasRead;
                }
                currentPart.close();
                inputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
