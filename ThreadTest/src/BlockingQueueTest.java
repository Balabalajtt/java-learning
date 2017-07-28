import java.util.concurrent.BlockingQueue;

/**
 * Created by 江婷婷 on 2017/7/28.
 */
public class BlockingQueueTest extends Thread {
    private BlockingQueue<String> bq;
    public BlockingQueueTest(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        String[] arr = {"第一个产品","第二个产品","第三个产品","第四个产品"};
        for(int i = 0; i < 10; i++) {
            System.out.println(getName() + "开始生产产品");
            try {
                Thread.sleep(200);
                bq.put(arr[i%4]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "生产完成" + bq);
        }
    }
}

class Customer extends Thread {
    private BlockingQueue<String> bq;
    public Customer(BlockingQueue<String> bq) {
        this.bq = bq;
    }
    @Override
    public void run() {
        while(true) {
            System.out.println(getName() + "开始消费产品");
            try {
                Thread.sleep(200);
                bq.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("消费完成" + bq);
        }
    }
}
