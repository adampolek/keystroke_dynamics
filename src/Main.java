import GUI.Viewer;
import operations.Classification;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Viewer viewer = new Viewer();

        Classification classification = new Classification();

        classification.load("slawko");
        System.out.println(Arrays.toString(classification.featuresVectorListist.get(0).getFeaturesArray()));
        System.out.println(Arrays.toString(classification.featuresVectorListist.get(1).getFeaturesArray()));
        System.out.println(Arrays.toString(classification.featuresVectorListist.get(2).getFeaturesArray()));

    }
}
