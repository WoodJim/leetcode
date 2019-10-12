package array;

public class ArrayQuaTest {
    public static void findMedianSortedArrays(){
        int [] nums1 = {};
        int[] nums2 = {2,3};
        double midle = ArrayQua.findMedianSortedArrays(nums1,nums2);
        if (midle == 2.5) {
            System.out.println("findMedianSortedArrays success");
        } else {
            System.out.println("findMedianSortedArrays error");
        }
    }
}
