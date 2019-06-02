package operations;

public class Metrics {


    public float euklides(int[] xArray, int [] yArray){

        int sum = 0;
        for (int i=0; i<xArray.length;i++){
            sum+=Math.pow(xArray[i]-yArray[i],2);
        }
        float result = (float) Math.sqrt(sum);

        return result;
    }

    public float manhattan(int[] xArray, int [] yArray){

        int sum = 0;
        for (int i=0; i<xArray.length;i++){
            sum+=Math.abs(xArray[i]-yArray[i]);
        }

        return sum;
    }

    public float czebyszew(int[] xArray, int [] yArray){

        int temp = 0;
        int result = 0;
        for (int i=0; i<xArray.length;i++){
            temp =Math.abs(xArray[i]-yArray[i]);
            if (temp > result)
                result = temp;
        }

        return result;
    }
}
