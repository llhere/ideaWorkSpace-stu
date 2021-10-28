import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {


        List<String> successBatchTemps = new ArrayList<String>();
        successBatchTemps.add("1");
        successBatchTemps.add("2");
        successBatchTemps.add("3");
        successBatchTemps.add("4");
        successBatchTemps.add("5");


        List<String> successDealResults = new ArrayList<String>();
        successDealResults.add("1");
        successDealResults.add("2");
        successDealResults.add("3");



        List<Object> c = new ArrayList<Object>();
        c.add("1");

        System.out.println(successBatchTemps.size());
        successBatchTemps.removeAll(c);
        System.out.println(successBatchTemps.size());

    }

}
