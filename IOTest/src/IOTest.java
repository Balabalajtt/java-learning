import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 江婷婷 on 2017/7/31.
 */
public class IOTest {

    public static void main (String[] args) throws IOException {
        Test1();
        Test2();
    }

    static void Test1() throws IOException {
        File file = new File("11.txt");
        System.out.println(file.getParent());//相对路径获取会出现错误
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsoluteFile().getParent());//转换成直接路径
        file.createNewFile();//创建真的文件
    //    file.deleteOnExit();//JVM退出后删除文件
    }

    static void Test2() {
        try (FileInputStream fileInputStream = new FileInputStream("11.txt"))//try语句关闭资源
        {
            byte[] bbuf = new byte[1024];
            int hasRead = 0;
            while ((hasRead = fileInputStream.read(bbuf))>0) {
                System.out.println(new String(bbuf,0,hasRead));//读中文会乱码
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //fileInputStream.close();//可以手动关闭资源
    }

}
