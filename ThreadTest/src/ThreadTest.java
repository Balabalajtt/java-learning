
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by 江婷婷 on 2017/7/27.
 */
public class ThreadTest extends Thread {
    private static int i;
    @Override
    public void run() {
        for (; i < 1000; i++) {
            System.out.println("这是" + this.getName() + "线程" + "i" + i);
        }
    }
    public static void main(String arg[]) throws Exception {
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

        FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>)()->{
            int i = 0;
            for(i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + i + "FutureTask");
            }
            return i;
        });
        for(int i = 0; i < 100 ; i++) {
            System.out.println(Thread.currentThread().getName() + "循环变量i的值" + i);
            if(i==10) {
                new Thread(task, "有返回值的线程").start();
            }



        }

        joinTest.joint();


        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(5);
        new BlockingQueueTest(bq).start();
        new BlockingQueueTest(bq).start();
        new BlockingQueueTest(bq).start();
        new Customer(bq).start();
    }
}
class RunableThreadTest implements Runnable {

    private int i;
    @Override
    public void run() {
        for(; i<100; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

