package calc;

import calc.operations.Multiplication;
import calc.operations.Operation;
import calc.operations.SqrSum;
import calc.operations.Sum;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by alexandra on 9/18/15.
 */
public class Main {

    public static void main(String[] args) {
        String path = "resources";
        File directory = new File(path);
        if(!directory.exists()){
            System.out.printf("Directory %s does not exists", path);
            return;
        }
        if(!directory.isDirectory()){
            System.out.printf("File %s is not a directory", path);
            return;
        }
        Map<Integer,Operation> ops = new HashMap<>();
        ops.put(1, new Sum());
        ops.put(2, new Multiplication());
        ops.put(3, new SqrSum());


        File[] files = directory.listFiles();
        int result = 0;
        for(File file : files){
            if(file.getName().matches("in_\\d+")){
                result += work(file, ops);
            }

        }
        System.out.println(result);

        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream("resources/out"))){
            printWriter.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int work(File file, Map<Integer,Operation> ops){
        try(Scanner scanner = new Scanner(new FileInputStream(file))){
            int action = scanner.nextInt();
            System.out.println(action);
            ArrayList<Integer> numbers = new ArrayList<>();

            while(scanner.hasNext()){
                numbers.add(scanner.nextInt());
            }

            int result = ops.get(action).perform(numbers);

            System.out.println(numbers);
            System.out.println(result);
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
