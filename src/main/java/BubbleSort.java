import java.util.Arrays;

public class BubbleSort {

    public static void main (String... args){

        int[] array = new int[100];
        for(int i=0; i<array.length; i++){
            array[i] = (int)(Math.random()*1000000
            );
        }
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(bubbleSort(array)));
    }

    public static int[] bubbleSort(int[] array){

        for(int i=0; i<array.length; i++){
            for(int j=i; j<array.length; j++){
                if(array[i]> array[j]){
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }
}
