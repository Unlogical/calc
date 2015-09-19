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
import java.util.concurrent.*;

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
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for(File file : files){
            if(file.getName().matches("in_\\d+")){
                Future<Integer> x = executorService.submit(work(file, ops));
                results.add(x);
            }
        }
        int result = 0;
        for(Future<Integer> res : results){
            try {
                result += res.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        System.out.println(result);

        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream("resources/out"))){
            printWriter.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Callable<Integer> work(File file, Map<Integer,Operation> ops){
        return new Perform(file, ops);
    }


    static class Perform implements Callable<Integer>{

        private final File file;
        private Integer res;
        private final Map<Integer, Operation> ops;

        public Perform(File file, Map<Integer, Operation> ops){
            this.ops = ops;
            this.file = file;
            this.res = res;
        }

        @Override
        public Integer call() {
            int result = 0;
            try (Scanner scanner = new Scanner(new FileInputStream(file))) {
                int action = scanner.nextInt();
                ArrayList<Integer> numbers = new ArrayList<>();
                while (scanner.hasNext()) {
                    numbers.add(scanner.nextInt());
                }

                System.out.println(action);
                System.out.println(numbers);
                result = ops.get(action).perform(numbers);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(result);
            return result;
        }
    }


}
