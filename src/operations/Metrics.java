package operations;

class Metrics {


    float euklides(int[] xArray, int[] yArray){

        int sum = 0;
        for (int i=0; i<xArray.length;i++){
            sum+=Math.pow(xArray[i]-yArray[i],2);
        }

        return (float) Math.sqrt(sum);
    }

    float manhattan(int[] xArray, int[] yArray){

        int sum = 0;
        for (int i=0; i<xArray.length;i++){
            sum+=Math.abs(xArray[i]-yArray[i]);
        }

        return sum;
    }

    float czebyszew(int[] xArray, int[] yArray){

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
