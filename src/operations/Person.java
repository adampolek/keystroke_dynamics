package operations;

public class Person {

    private String name;
    private int count;
    private float distance;

    public Person(String name, int count, float distance) {
        this.name = name;
        this.count = count;
        this.distance = distance;
    }

    String getName() {
        return name;
    }

    int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }

    float getDistance() {
        return distance;
    }

    void setDistance(float distance) {
        this.distance = distance;
    }
}
