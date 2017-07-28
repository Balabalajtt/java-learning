/**
 * Created by 江婷婷 on 2017/7/28.
 */
public class joinTest extends Thread {
    public joinTest(String s) {
        super(s);
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }

    public static void joint() throws Exception {
        new joinTest("第一个子线程").start();
        for(int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + i);
            if(i==20) {
                joinTest jt = new joinTest("第二个子线程");
                jt.start();
                jt.join();
            }
        }
    }
}
