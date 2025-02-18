package array;

import list.ListNode;

import java.util.*;

public class ArrayQua {

    /**
     * https://leetcode.com/problems/median-of-two-sorted-arrays/
     * <p>
     * eg:
     * nums1 = [1, 3]
     * nums2 = [2]
     * <p>
     * The median is 2.0
     * <p>
     * <p>
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * <p>
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
        int midianIndex = (length1 + length2) / 2;
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
            } else if (s1Index < length1) {
                second = nums1[s1Index++];
            } else {
                second = nums2[s2Index++];
            }
            startIndex++;

        }
        return (length1 + length2) % 2 == 0 ? (first + second) / 2.0 : second;
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
            return num[length / 2];
        }

        int index = length / 2;
        return (num[index] + num[index - 1]) / 2.0;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;

        while (left <= right && top <= bottom) {
            // 左到右
            for (int colum = left; colum <= right; colum++) {
                order.add(matrix[top][colum]);
            }
            // 上到下
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }

            if (left < right && top < bottom) {
                // 右到左
                for (int colum = right - 1; colum > left; colum--) {
                    order.add(matrix[colum][bottom]);
                }

                for (int row = bottom - 1; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }

        return order;
    }

    //TODO:. 数组中的第K个最大元素
    public static int findKthLargest(int[] sums, int k) {
        if (sums == null || sums.length < k) {
            return -1;
        }
        int length = sums.length;
        int objectIndex = length - k;

        int left = 0;
        int right = length - 1;
        while (left <= right) {
            // 利用快速排序
            int curIndex = partition(sums, left, right);
            // 如果刚好最终的位置就是要找的index，则直接返回
            if (curIndex == objectIndex) {
                return sums[curIndex];
            } else if (curIndex < objectIndex) {
                left = curIndex + 1;
            } else {
                right = curIndex - 1;
            }
        }

        return -1;


    }

    private static void swap(int[] sums, int first, int second) {
        int tmp = sums[first];
        sums[first] = sums[second];
        sums[second] = tmp;
    }

    /// 快速排序
    private static int partition(int[] sums, int left, int right) {
        int tmpIndex = left + right / 2;
        // 交换左右两边的值
        swap(sums, left, tmpIndex);
        // 最左边的为参考值
        int privot = sums[left];
        // 左区间
        int first = left + 1;
        // 右区间
        int second = right;
        while (first <= second) {
            // 从前向后遍历找到第一个比目标值大的
            while (sums[first] < privot) {
                first++;
            }
            // 从后向有遍历找到第一个比目标值小的
            while (sums[second] > privot) {
                second--;
            }
            // 如果左边的index 比右边的index小，则交换两个的值
            if (first < second) {
                swap(sums, first, second);
            }
            first++;
            second--;
        }
        return second;
    }

    ///找峰值
    public static int findPeakElement(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int resultIndex = 0;
        int length = nums.length;
        for (int index = 0; index < length; index++) {
            if (nums[index] > nums[resultIndex]) {
                resultIndex = index;
            }
        }
        return resultIndex;
    }

    //TODO:排序数组里找对应的值
    public static int[] searchRange(int[] nums, int target) {
        int leftIndex = binarySearch(nums, target, true);
        int rightIndex = binarySearch(nums, target, false);
        if (leftIndex <= rightIndex && rightIndex < nums.length && nums[leftIndex] == target && nums[rightIndex] == target) {
            return new int[]{leftIndex, rightIndex};
        }
        return new int[]{-1, -1};
    }

    public static int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0;
        int right = nums.length - 1;
        int result = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    //反转整形值
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }

        return rev;

    }

    //链表右移
    public static ListNode rotateRight(ListNode head, int k) {
        if (k <= 0) {
            return head;
        }
        if (head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode tmpNode = head;
        while (tmpNode.next != null) {
            n++;
            tmpNode = tmpNode.next;
        }

        int add = n - k % n;
        if (add == n) {
            return head;
        }
        tmpNode.next = head;

        while (add-- > 0) {
            tmpNode = tmpNode.next;
        }
        ListNode result = tmpNode.next;
        tmpNode.next = null;
        return result;


    }

    public static int maxSubArray(int[] nums) {
        int max = 0;
        if (nums == null || nums.length <= 0) {
            return max;
        }
        int tmpMax = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (tmpMax + nums[i] > 0) {
                tmpMax = tmpMax + nums[i];
            } else {
                tmpMax = 0;
                continue;
            }
            if (tmpMax > max) {
                max = tmpMax;
            }
        }
        return max;
    }

    /// 三数之和
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;

    }

    public static int maxProfit(int prices[]) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int x = 0;
        Integer[] result = new Integer[(right + 1) * (bottom + 1)];
        while (true) {
            for (int i = left; i <= right; i++) {
                result[x++] = matrix[top][i]; // left to right;
            }
            if (++top > bottom) {
                break;
            }
            for (int j = top; j <= bottom; j++) {
                result[x++] = matrix[j][right]; // top to bottom
            }
            if (left > --right) {
                break;
            }
            for (int i = right; i >= left; i++) {
                result[x++] = matrix[bottom][i]; // right to left
            }
            for (int i = bottom; i >= top; i++) {
                result[x++] = matrix[i][left];
            }
            if (++left > right) {
                break;
            }

        }
        return Arrays.asList(result);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> outPut = new LinkedList<>();

        backTrack(nums, outPut, result);
        return result;
    }

    private static void backTrack(int[] nums, LinkedList<Integer> outPut,
                                  List<List<Integer>> res) {
        if (outPut.size() == nums.length) {
            res.add(new ArrayList<>(outPut));
            return;
        }

        for (int i = 0; i < nums.length;i++) {
            if(outPut.contains(nums[i])) {
                continue;
            }
            outPut.add(nums[i] );
            backTrack(nums,outPut,res);
            outPut.removeLast();
        }
    }

    // 最长上升子序列
    public static int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int length = nums.length;;
        if (length <= 1) {
            return length;
        }
        int[] dp = new int[length];
        int result = 0;
        Arrays.fill(dp,1);
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            result = Math.max(result,dp[i]);
        }
        return result;
    }

//    public static int[] sortArray(int[] nums) {
//
//    }


}
