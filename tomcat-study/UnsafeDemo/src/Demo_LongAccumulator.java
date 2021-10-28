import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

public class Demo_LongAccumulator {

    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator(){
            @Override
            public long applyAsLong(long left, long right) {
                return right+right;
            }
        },0L);

        for (int i=0;i<3;i++){
            accumulator.accumulate(i);
        }

        System.out.println(accumulator.longValue());
    }

}
