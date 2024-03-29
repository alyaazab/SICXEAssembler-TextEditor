package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SourceFile {

    public static ArrayList<String> readSourceProgramFromFile(File file){
        ArrayList<String> sourceProgramArray = new ArrayList<>();

        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null){
                sourceProgramArray.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceProgramArray;
    }

}