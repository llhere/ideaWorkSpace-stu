package Web12306;

/**
 * 实现Runnable接口模拟抢票
 */
public class Web12306 implements Runnable{
    private int num = 3;

    @Override
    public void run() {

        while(true){
            if(num < 1) break;
            System.out.println(Thread.currentThread().getName() + --num);

        }

    }

    public static void main(String[] args) throws InterruptedException {

        //创建真实角色
        Web12306 web = new Web12306();

        //创建代理角色
        Thread thread1 = new Thread(web,"甲 ");
        Thread thread2 = new Thread(web,"乙 ");
        Thread thread3 = new Thread(web,"丙 ");

        //调用stat方法
        thread1.start();
        thread2.start();
        thread3.start();

    }

}
