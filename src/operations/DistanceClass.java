package operations;

public class DistanceClass {

    float distance;
    private String name;

    float getDistance() {
        return distance;
    }

    void setDistance(float distance) {
        this.distance = distance;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "<"+name+" "+distance+">";
    }
}
