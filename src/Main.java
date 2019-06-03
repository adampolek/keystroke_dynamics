import GUI.Viewer;
import operations.Classification;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Viewer viewer = new Viewer();

        Classification classification = new Classification();

        classification.load("slawko");
        classification.load("adam");
        classification.load("exo");
        /*System.out.println(Arrays.toString(classification.featuresVectorList.get(0).getFeaturesArray()));
        System.out.println(Arrays.toString(classification.featuresVectorList.get(1).getFeaturesArray()));
        System.out.println(Arrays.toString(classification.featuresVectorList.get(2).getFeaturesArray()));*/

        classification.knn(5);

    }
}
