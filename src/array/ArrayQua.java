package array;

public class ArrayQua {

    /**
     * https://leetcode.com/problems/median-of-two-sorted-arrays/
     *
     * eg:
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * The median is 2.0
     *
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * The median is (2 + 3)/2 = 2.5
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length <= 0) {
            return findMedianSortedArrays(nums2);
        }

        if (nums2 == null || nums2.length <= 0) {
            return findMedianSortedArrays(nums1);
        }
        int length1 = nums1.length;
        int length2 = nums2.length;
        int midianIndex = (length1 + length2) /2;
        int first = 0;
        int second = 0;

        int startIndex = 0;
        int s1Index = 0;
        int s2Index = 0;
        while (startIndex <= midianIndex) {
            first = second;
            if (s1Index < length1 && s2Index < length2) {
                if (nums1[s1Index] < nums2[s2Index]) {
                    second = nums1[s1Index++];
                } else {
                    second = nums2[s2Index++];
                }
            } else  if (s1Index < length1){
                second = nums1[s1Index++];
            } else {
                second = nums2[s2Index++];
            }
            startIndex++;

        }
        return (length1 + length2) % 2 == 0 ? (first + second)/2.0 : second ;
    }

    private static double findMedianSortedArrays(int[] num) {
        if (num == null || num.length <= 0) {
            return 0;
        }
        int length = num.length;
        if (length == 1) {
            return num[0];
        }
        if (length % 2 != 0) {
            return num[length /2];
        }

        int index = length /2;
        return (num[index] + num[index -1])/2.0;
    }
}
