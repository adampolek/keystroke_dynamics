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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
