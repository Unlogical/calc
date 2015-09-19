package calc.operations;

import java.util.ArrayList;

/**
 * Created by alexandra on 9/18/15.
 */
public class SqrSum implements Operation {
    @Override
    public int perform(ArrayList<Integer> numbers) {
        int sqrSum = 0;
        for(Integer number : numbers){
            sqrSum+=number*number;
        }
        return sqrSum;
    }
}
