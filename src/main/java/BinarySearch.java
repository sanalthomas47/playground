/**
 * Iterative binary search on a sorted integer array.
 * Returns the index of the target element, or -1 if not found.
 * Time complexity: O(log n). Space complexity: O(1).
 */
public class BinarySearch {

    /**
     * Searches for {@code target} in the sorted array {@code input}.
     *
     * @param input  sorted integer array
     * @param target value to find
     * @return index of {@code target}, or -1 if absent
     */
    public int search(int[] input, int target){

        int left = 0;
        int right = input.length - 1;

        while(left <= right){

            int mid = (left + right) /2;

            if(input[mid] == target){
                return mid;
            }else if(input[mid] < target){
                left = mid+1;
            } else if(input[mid] > target){
                right = mid-1;
            }
        }
        return -1;
    }

    public static void main(String[] args){

        BinarySearch binarySearch = new BinarySearch();

        int[] array = new int[]{Integer.MAX_VALUE,-45,-33,-26,-10,-4,1,3,4,6,9,11,45,56,61,77,92,111,325,666,667,680,747,750,900, Integer.MAX_VALUE};

        System.out.println("Length: "+ array.length);
        System.out.println("Position "+binarySearch.search(array, 61));

        System.out.println("Position "+binarySearch.search(array, 747));

        System.out.println("Position "+binarySearch.search(array, 900));
        System.out.println("Position "+binarySearch.search(array, Integer.MAX_VALUE));
    }

}
