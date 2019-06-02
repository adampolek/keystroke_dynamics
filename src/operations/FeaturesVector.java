package operations;

public class FeaturesVector {

    String name;
    int[] featuresArray = new int[49];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getFeaturesArray() {
        return featuresArray;
    }

    public void setFeaturesArray(int[] featuresArray) {
        this.featuresArray = featuresArray;
    }
}
