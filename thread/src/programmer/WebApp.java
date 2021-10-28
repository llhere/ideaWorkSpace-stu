package programmer;

/**
 * 推荐Runnable创建线程
 *      避免单线程的局限性
 *      便于共享资源
 */
public class WebApp{


    public static void main(String[] args) {
        //创建真实角色
        Proframmer porfamammer = new Proframmer();

        //创建代理角色 + 真实角色的引用
        Thread proxy = new Thread(porfamammer,"chen");

        //执行方法
        proxy.start();

        for (int i=0;i<10;i++){
            System.out.println("打了" + i + "行QQ");
        }

    }

}
