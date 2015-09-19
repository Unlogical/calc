package calc.operations;

import java.util.ArrayList;

/**
 * Created by alexandra on 9/18/15.
 */
public class Multiplication implements Operation {
    @Override
    public int perform(ArrayList<Integer> numbers) {
        int mult = 1;
        for(Integer number : numbers){
            mult = number*mult;
        }
        return mult;
    }
}
