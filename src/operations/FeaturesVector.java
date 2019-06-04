package operations;

public class FeaturesVector {

    String name;
    int[] featuresArray = new int[58];

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    void setFeaturesArray(int[] featuresArray) {
        this.featuresArray = featuresArray;
    }
}
