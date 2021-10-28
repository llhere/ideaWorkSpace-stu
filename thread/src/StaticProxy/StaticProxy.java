package StaticProxy;

/**
 * 静态代理 设计模式
 * 创建接口
 * 创建真实角色
 * 创建代理模式 + 真实角色的引用
 * 二者实现相同的接口
 */
public class StaticProxy{

    public static void main(String[] args) {

        //创建真实角色
        You you =  new You();

        //创建代理角色
        Commpany commpany = new Commpany(you);

        //执行任务
        commpany.Marry();

    }

}

//接口
interface Marrry{

    //结婚
    public abstract void Marry();
}

//真实角色 实现接口
class You implements Marrry{


    @Override
    public void Marry() {
        System.out.println("结婚");
    }
}

//代理角色 真实角色的引用
class Commpany implements Marrry{

    //创建真实角色
    You you;

    public Commpany(You you){
        this.you = you;
    }

    //结婚前
    public void after(){
        System.out.println("布置房间");
    }

    //结婚后
    public void before(){
        System.out.println("收拾房间");
    }

    @Override
    public void Marry() {
        //结婚前
        after();

        //结婚
        you.Marry();

        //结婚后
        before();
    }
}