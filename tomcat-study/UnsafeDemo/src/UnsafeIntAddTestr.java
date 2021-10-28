import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeIntAddTestr {

    public static void main(String[] args) throws InterruptedException {
        UnsafeAddInteger ui = new UnsafeAddInteger();

        for (int i=0;i<6;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int y=0;y<10000000;y++){
                        ui.add();
                    }
                }
            }).start();
        }

        Thread.sleep(6000L);
        System.out.println(ui.i);

        new AtomicInteger();

    }

}
