import GUI.Viewer;
import operations.Classification;

public class Main {

    public static void main(String[] args) {

        Viewer viewer = new Viewer();

        Classification classification = new Classification();

        classification.load("slawko");
        classification.load("adam");
        classification.load("exo");
        classification.load("Andzik");
        classification.load("Mazak");
        classification.load("Pfajer");
        classification.load("Macion");
        classification.load("Zuzia");
        classification.load("damian");
        classification.load("przemek");

        classification.classifyAll();
        classification.printQuality();

    }
}
