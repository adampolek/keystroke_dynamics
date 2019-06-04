package operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Classification {

    private Metrics metrics = new Metrics();

    private List<FeaturesVector> featuresVectorList = new ArrayList<>();
    private List<DistanceClass> distanceClassListManhattan = new ArrayList<>();
    private List<DistanceClass> distanceClassListEuklides = new ArrayList<>();
    private List<DistanceClass> distanceClassListCzebyszew = new ArrayList<>();
    private List<Person> manhattan_person_count = new ArrayList<>();
    private List<Person> euclides_person_count = new ArrayList<>();
    private List<Person> czebyszew_person_count = new ArrayList<>();
    private int[][] quality;


    public Classification() {
        quality=new int[3][16];
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

    public void classifyAll(){
        for (int i=0;i<featuresVectorList.size();i++){
            knn(i);
        }
    }

    public void knn(int number){

            clearLists();
            calculateDistance(number);
            distanceClassListManhattan.sort(Comparator.comparing(DistanceClass::getDistance));
            //distanceClassListManhattan.forEach(dc -> System.out.println(dc.toString()));

            for (int k=1;k<16;k++) {
                initPersonList(manhattan_person_count);
                addToList(manhattan_person_count, distanceClassListManhattan, k);
                manhattan_person_count.sort(Comparator.comparing(Person::getCount).reversed().thenComparing(Person::getDistance));
                System.out.println("Manhattan for k = "+k+" vector number "+number+ " Should be "+featuresVectorList.get(number).getName());
                System.out.println("is "+manhattan_person_count.get(0).getName());
                if (manhattan_person_count.get(0).getName().equals(featuresVectorList.get(number).getName()))
                    quality[0][k]++;
            }

                distanceClassListEuklides.sort(Comparator.comparing(DistanceClass::getDistance));
                //distanceClassListEuklides.forEach(dc -> System.out.println(dc.toString()));

        for (int k=1;k<16;k++) {
            initPersonList(euclides_person_count);
            addToList(euclides_person_count, distanceClassListEuklides, k);
            euclides_person_count.sort(Comparator.comparing(Person::getCount).reversed().thenComparing(Person::getDistance));
            System.out.println("Euclides for k = "+k+" vector number "+number+ " Should be "+featuresVectorList.get(number).getName());
            System.out.println("is "+euclides_person_count.get(0).getName());
            if (euclides_person_count.get(0).getName().equals(featuresVectorList.get(number).getName()))
                quality[1][k]++;
        }

            distanceClassListCzebyszew.sort(Comparator.comparing(DistanceClass::getDistance));
            //distanceClassListCzebyszew.forEach(dc -> System.out.println(dc.toString()));

        for (int k=1;k<16;k++) {
            initPersonList(czebyszew_person_count);
            addToList(czebyszew_person_count, distanceClassListCzebyszew, k);
            czebyszew_person_count.sort(Comparator.comparing(Person::getCount).reversed().thenComparing(Person::getDistance));
            System.out.println("Czebyszew for k = "+k+" vector number "+number+ " Should be "+featuresVectorList.get(number).getName());
            System.out.println("is "+czebyszew_person_count.get(0).getName());
            if (czebyszew_person_count.get(0).getName().equals(featuresVectorList.get(number).getName()))
                quality[2][k]++;
        }
        }

        private void calculateDistance(int number){



            for (int i=0;i<featuresVectorList.size();i++) {
                if (i!=number) {
                    float distance_manhattan = metrics.manhattan(featuresVectorList.get(number).featuresArray, featuresVectorList.get(i).featuresArray);
                    //System.out.println("distance manhattan  " + distance_manhattan);
                    DistanceClass dc = new DistanceClass();
                    dc.setDistance(distance_manhattan);
                    dc.setName(featuresVectorList.get(i).name);
                    distanceClassListManhattan.add(dc);


                    float distance_euclides = metrics.euklides(featuresVectorList.get(number).featuresArray, featuresVectorList.get(i).featuresArray);
                    //System.out.println("distance euclides  " + distance_euclides);
                    DistanceClass dc2 = new DistanceClass();
                    dc2.setDistance(distance_euclides);
                    dc2.setName(featuresVectorList.get(i).name);
                    distanceClassListEuklides.add(dc2);

                    float distance_czebyszew = metrics.czebyszew(featuresVectorList.get(number).featuresArray, featuresVectorList.get(i).featuresArray);
                    //System.out.println("distance czebyszew  " + distance_czebyszew);
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
        private void initPersonList(List<Person> list){
            list.clear();

            list.add(new Person("slawko",0,0));
            list.add(new Person("adam",0,0));
            list.add(new Person("exo",0,0));
            list.add(new Person("Andzik",0,0));
            list.add(new Person("Mazak",0,0));
            list.add(new Person("Pfajer",0,0));
            list.add(new Person("Macion",0,0));
            list.add(new Person("Zuzia",0,0));
            list.add(new Person("damian",0,0));
            list.add(new Person("przemek",0,0));
        }

        public void printQuality(){
            System.out.println();
            System.out.println("Quality for Manhattan ");
            for (int i=1;i<16;i++){
                System.out.print(quality[0][i]+" ");
            }
            System.out.println();
            System.out.println("Quality for Euclides ");
            for (int j=1;j<16;j++){
                System.out.print(quality[1][j]+" ");
            }
            System.out.println();
            System.out.println("Quality for Czebyszew ");
            for (int k=1;k<16;k++){
                System.out.print(quality[2][k]+" ");
            }
        }
}
