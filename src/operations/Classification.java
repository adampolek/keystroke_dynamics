package operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Classification {

    Metrics metrics = new Metrics();

    public List<FeaturesVector> featuresVectorList = new ArrayList<>();
    private List<DistanceClass> distanceClassListManhattan = new ArrayList<>();
    private List<DistanceClass> distanceClassListEuklides = new ArrayList<>();
    private List<DistanceClass> distanceClassListCzebyszew = new ArrayList<>();
    private List<Person> manhattan_person_count = new ArrayList<>();


    public Classification() {
        manhattan_person_count.add(new Person("slawko",0,0));
        manhattan_person_count.add(new Person("adam",0,0));
        manhattan_person_count.add(new Person("exo",0,0));
    }

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
            featuresVectorList.add(featuresVector);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void knn(int k){

        //for (int i=0;i<2;i++) {
            clearLists();
            calculateDistance(0);
            distanceClassListManhattan.sort(Comparator.comparing(DistanceClass::getDistance));
            distanceClassListManhattan.forEach(dc -> System.out.println(dc.toString()));
            addToList(manhattan_person_count,distanceClassListManhattan,k);
            manhattan_person_count.sort(Comparator.comparing(Person::getCount).reversed().thenComparing(Person::getDistance));
        System.out.println(manhattan_person_count.get(0).getName());
            System.out.println();


            distanceClassListEuklides.sort(Comparator.comparing(DistanceClass::getDistance));
            distanceClassListEuklides.forEach(dc -> System.out.println(dc.toString()));
            System.out.println();

            distanceClassListCzebyszew.sort(Comparator.comparing(DistanceClass::getDistance));
            distanceClassListCzebyszew.forEach(dc -> System.out.println(dc.toString()));
        //}
        }

        private void calculateDistance(int number){



            for (int i=0;i<featuresVectorList.size();i++) {
                if (number!=i) {
                    float distance_manhattan = metrics.manhattan(featuresVectorList.get(number).featuresArray, featuresVectorList.get(i).featuresArray);
                    System.out.println("distance manhattan  " + distance_manhattan);
                    DistanceClass dc = new DistanceClass();
                    dc.setDistance(distance_manhattan);
                    dc.setName(featuresVectorList.get(i).name);
                    distanceClassListManhattan.add(dc);


                    float distance_euclides = metrics.euklides(featuresVectorList.get(number).featuresArray, featuresVectorList.get(i).featuresArray);
                    System.out.println("distance euclides  " + distance_euclides);
                    DistanceClass dc2 = new DistanceClass();
                    dc2.setDistance(distance_euclides);
                    dc2.setName(featuresVectorList.get(i).name);
                    distanceClassListEuklides.add(dc2);

                    float distance_czebyszew = metrics.czebyszew(featuresVectorList.get(number).featuresArray, featuresVectorList.get(i).featuresArray);
                    System.out.println("distance czebyszew  " + distance_czebyszew);
                    DistanceClass dc3 = new DistanceClass();
                    dc3.setDistance(distance_czebyszew);
                    dc3.setName(featuresVectorList.get(i).name);
                    distanceClassListCzebyszew.add(dc3);

                    System.out.println();
                }
            }
        }
        private void clearLists(){
        distanceClassListCzebyszew.clear();
        distanceClassListEuklides.clear();
        distanceClassListManhattan.clear();
        }

        private void addToList(List<Person> personList, List<DistanceClass> dc,int k){

            for (int j=0;j<k;j++){

                for (int i = 0;i<personList.size();i++) {
                    if (personList.get(i).getName().equals(dc.get(j).getName())) {
                        personList.get(i).setCount(personList.get(i).getCount() + 1);
                        personList.get(i).setDistance(personList.get(i).getDistance() + dc.get(j).distance);
                        break;
                    }
                }
            }
        }
}
