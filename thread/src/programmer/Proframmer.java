package programmer;


/**
 * 创建实现Runnable接口 + 重写run()  -- > 真实角色
 * 启动多线程 使用静态代理
 *
 *
 */
public class Proframmer implements Runnable{


    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("敲了" + i + "行代码");
        }
    }

}

