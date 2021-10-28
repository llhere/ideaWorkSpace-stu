import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {

                lock.lock();

                System.out.println("进入等待");

                try {
                    condition.await();  //进入等待并释放锁


                    System.out.println("被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        }).start();


        //2秒后唤醒lock,打印"被唤醒"
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
