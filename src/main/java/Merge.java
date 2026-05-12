public class Merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int counter = 0;
        for(int i=0; i< nums1.length-1; i++){
            if(counter > nums2.length-1)
                break;
            if (nums1[i] >= nums2[counter]) {
                updateNums(nums1, i);
                nums1[i] = nums2[counter];
                //i = i + counter;
                counter++;
            } else {
                continue;
            }
        }
        if(counter < nums2.length){
            for(int i=nums1.length-1-(nums2.length-1-counter); i< nums1.length; i++){
                nums1[i] = nums2[counter];
                counter++;
            }
        }
    }

    private void updateNums(int[] nums, int pos){

        int replace = nums[nums.length-1];
        for(int i=pos; i< nums.length; i++){
            int curVal = nums[i];
            nums[i] = replace;
            replace = curVal;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,3,4};

        new Merge().merge(nums1, 6, nums2, 3);
        System.out.println();
    }
}
