package calc;

import java.io.File;

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
        File[] files = directory.listFiles();
        for(File file : files){
            if(file.getName().matches("in_\\d+")){
                work(file);
            }

        }
    }

    public static void work(File file){

    }

}
