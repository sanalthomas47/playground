import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class Merge2 {

    public static int[] mergeArrays(int[] myArray, int[] alicesArray) {

        int[] array = new int[myArray.length+alicesArray.length];

        if(myArray.length == 0)
            return alicesArray;

        if(alicesArray.length == 0)
            return myArray;

        if(myArray.length < alicesArray.length){
            merge(alicesArray, myArray, array);
        }else{
            merge(myArray, alicesArray, array);
        }
        for(int i : array)
            System.out.print(i+" ");

        System.out.println();
        return array;
    }


    private static void merge(int[] large, int[] small, int[] finalArr){
        int counter = 0;
        int j=0;
        int i=0;
        while(counter < large.length + small.length){
            if(j < small.length && i < large.length){
                if(large[i] < small[j]){
                    finalArr[counter++] = large[i];
                    i++;
                }else{
                    finalArr[counter++] = small[j];
                    j++;
                }
            }else break;
        }
        while(j < small.length)
            finalArr[counter++] = small[j++];

        while(i < large.length)
            finalArr[counter++] = large[i++];
    }


    // tests

   /*@Test
    public void bothArraysAreEmptyTest() {
        final int[] myArray = {};
        final int[] alicesArray = {};
        final int[] expected = {};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void firstArrayIsEmptyTest() {
        final int[] myArray = {};
        final int[] alicesArray = {1, 2, 3};
        final int[] expected = {1, 2, 3};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void secondArrayIsEmptyTest() {
        final int[] myArray = {5, 6, 7};
        final int[] alicesArray = {};
        final int[] expected = {5, 6, 7};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }*/

    @Test
    public void bothArraysHaveSomeNumbersTest() {
        final int[] myArray = {2, 4, 6};
        final int[] alicesArray = {1, 3, 7};
        final int[] expected = {1, 2, 3, 4, 6, 7};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void arraysAreDifferentLengthsTest() {
        final int[] myArray = {2, 4, 6, 8};
        final int[] alicesArray = {1, 7};
        final int[] expected = {1, 2, 4, 6, 7, 8};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Merge2.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
