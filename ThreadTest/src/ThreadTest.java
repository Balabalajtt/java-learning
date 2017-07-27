import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by 江婷婷 on 2017/7/27.
 */
public class ThreadTest extends Thread {
    private static int i;
    @Override
    public void run() {
        for(; i<1000 ;i++) {
            System.out.println("这是" + this.getName() + "线程" + "i" + i);
        }
    }
    public static void main(String arg[]) {
        for(i = 0; i<1000 ;i++) {
            System.out.println("这是" + Thread.currentThread().getName() + "线程" + "i" + i);
            if(i == 1) {
                new ThreadTest().start();
                new ThreadTest().start();
            }
            if(i==10) {
                RunableThreadTest rtt = new RunableThreadTest();
                new Thread(rtt,"Third").start();
                new Thread(rtt,"Forth").start();//Runable创建 共享实例变量
            }

        }

        FutureTask<>

    }
}
class RunableThreadTest implements Runnable {

    private int i;
    @Override
    public void run() {
        for(; i<1000; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

class CallableTest implements Callable {

    @Override
    public Object call() throws Exception {
        return null;
    }
}