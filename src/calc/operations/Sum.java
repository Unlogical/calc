package calc.operations;

import java.util.ArrayList;

/**
 * Created by alexandra on 9/18/15.
 */
public class Sum implements Operation{
    @Override
    public int perform(ArrayList<Integer> numbers) {
        int sum = 0;
        for(Integer number : numbers){
            sum+=number;
        }
        return sum;
    }
}
