
import java.io.*;

/**
 * Created by 江婷婷 on 2017/7/31.
 */
public class IOTest {

    public static void main(String[] args) throws IOException {
        Test1();
        Test2();
        Test3();
        Test4();
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
            while ((hasRead = fileInputStream.read(bbuf)) > 0) {
                System.out.println(new String(bbuf, 0, hasRead));//读中文会乱码
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //fileInputStream.close();//可以手动关闭资源
    }

    static void Test3() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
                PrintStream printStream = new PrintStream(fileOutputStream)) {
            printStream.println("处理流");
            printStream.println(new IOTest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void Test4() {
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                PushbackReader pushbackInputStream = new PushbackReader(new FileReader("test.txt"), 64)) {

            char[] buf = new char[32];
            String lastContent = "";
            int hasRead;
            while ((hasRead = pushbackInputStream.read(buf)) > 0) {
                String string = new String(buf, 0, hasRead);//把读取内容转换成字符串
                int targetIndex = 0;
                if ((targetIndex = (lastContent + string).indexOf("IOTest")) > 0) {
                    pushbackInputStream.unread((lastContent + string).toCharArray());
                    if (targetIndex > 32) {
                        buf = new char[targetIndex];
                    }
                    pushbackInputStream.read(buf, 0, targetIndex);//读取目标字符串之前内容
                    System.out.println(new String(buf, 0, targetIndex));
                    System.exit(0);
                } else {
                    System.out.println(lastContent);
                    lastContent = string;
                }
            }


            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("exit")) {
                    System.exit(1);
                }
                System.out.println("输出内容：" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}