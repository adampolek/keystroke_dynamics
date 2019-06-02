package operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Classification {

    public List<FeaturesVector> featuresVectorListist = new ArrayList<>();

    public void load(String filename){
        loadFromFile(filename,"1.txt");
        loadFromFile(filename,"2.txt");
        loadFromFile(filename,"3.txt");
    }

    public void loadFromFile(String filename,String number){
        int[] array = new int[58];
        try {
            Scanner s = new Scanner(new File(filename+number));
            for (int i = 0; i < array.length; i++)
                array[i] = s.nextInt();

            FeaturesVector featuresVector =  new FeaturesVector();
            featuresVector.setName(filename);
            featuresVector.setFeaturesArray(array);
            featuresVectorListist.add(featuresVector);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
