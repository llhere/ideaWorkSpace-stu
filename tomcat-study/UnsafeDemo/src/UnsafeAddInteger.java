import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAddInteger {

    private static Unsafe unsafe = null;

    volatile int i = 0;

    private static Long valueOfSet;

    static {
        try {
            //获取Unsafe对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            //获取变量在内存中的值（偏移量,long值）
            Field fieldi = UnsafeAddInteger.class.getDeclaredField("i");
            valueOfSet = unsafe.objectFieldOffset(fieldi);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void add(){
        //自旋操作
        for (;;){
            //unsafe对象用偏移量获取内存中的值
            int current = unsafe.getIntVolatile(this, valueOfSet);
            //对值进行CAS +1操作
            if (unsafe.compareAndSwapInt(this,valueOfSet,current,current+1)){
                break;
            }
        }
    }

}
