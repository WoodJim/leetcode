package stringQua;

import tree.TreeNode;

import java.util.*;

public class QuaStrings {
    public static int lengthOfLongestSubString(String s) {
        if (s == null) {
            return 0;
        }
        int length = s.length();
        if (length == 1) {
            return 1;
        }

        //TODO:extraInfo
        HashMap<Character,Integer> indexMap = new HashMap<>();
        int result = 0;
        int left = 0;
        for(int index =0; index < length;index++) {
            Character curChar = s.charAt(index);
            if (indexMap.containsKey(curChar)) {
                left = Math.max(left,indexMap.get(curChar) + 1);
            }
            indexMap.put(curChar,index);
            result = Math.max(result,index - left + 1);
        }
        return result;
    }

    //回文串
    //长度为n的字符串会生成2n-1组回文中心
    public static  int countSubStrings(String s) {
       int length = s.length();
       int result = 0;
       //遍历出所有可能的回文的中心
       for(int index = 0;index < 2 *length -1;++index) {
           int left = index / 2;
           int right = left + index %2;
           while (left > 0 && right < length && s.charAt(left) == s.charAt(right)) {
               ++left;
               --right;
               result++;
           }
       }

       return result;

    }

    //最长回文子串
    public static String longestPalindrome(String s) {
        int length = s.length();
        String result = "";
        //遍历出所有可能的回文的中心
        for (int index = 0; index < 2 * length - 1; ++index) {
            int left = index / 2;
            int right = left + index % 2;
            while (left > 0 && right < length && s.charAt(left) == s.charAt(right)) {
                String tmp = s.substring(left, right + 1);
                if (tmp.length() > result.length()) {
                    result = tmp;
                }
                ++left;
                --right;
            }
        }

        return result;
    }

    // 求回文子串的个数
    public static int countSubStrings2(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        boolean[][] dp = new boolean[length][length];
        int result = 0;
        for (int i = length - 1; i >= 0 ; i--) {
            for (int j = i; j < length;j++) {
                if (chars[i] == chars[j]) {
                    if (j - i <= 1) {
                        result++;
                        dp[i][j] = true;
                    } else if (dp[i+1][j-1]) {
                        result++;
                        dp[i][j] = true;
                    }

                }
            }
        }
        return result;
    }


    //两个字符串相加
    public static String addString(String num1,String num2) {
        int first = num1.length() - 1;
        int second = num2.length() - 1;
        int add = 0;
        StringBuilder result = new StringBuilder();
        while (first >=0 || second >=0 || add != 0) {
            int x = first > 0 ? num1.charAt(first) - '0' : 0;
            int y = second > 0 ? num2.charAt(second) - '0' : 0;
            int tmp = x + y + add;
            result.append(tmp / 10);
            add = tmp % 10;
            first--;
            second--;
        }
        return result.reverse().toString();
    }

    //第一个出现的字符;
    public static char firstUniChar(String s) {
        Map<Character,Integer> frequency = new HashMap<>();
        int length = s.length();
        for(int index = 0;index < length;index++) {
            char tmpChar = s.charAt(index);
            int count = frequency.getOrDefault(tmpChar,0) + 1;
            frequency.put(tmpChar,count);
        }

        for (int index = 0; index < length;index++) {
            char tmpChar = s.charAt(index);
            int count = frequency.getOrDefault(tmpChar,0);
            if (count == 1) {
                return tmpChar;
            }
        }

        return ' ';
    }

    //字符串相乘
    public static String multiply(String num1,String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int lengthA = num1.length();
        int lengthB = num2.length();

        int[] ansArr = new int[lengthB + lengthA];
        //计算每一步的乘法结果
        for(int index = lengthA -1 ;index >=0;index--) {
            int x = num1.charAt(index) - '0';
            for (int j = lengthB -1; j >= 0; j--) {
                int y = num2.charAt(index) - '0';
                ansArr[index + j + 1] += x *y;
            }
        }
        //整合乘法结果
        for (int index = lengthA + lengthB - 1; index >= 0; index--) {
            ansArr[index - 1]+=ansArr[index] /10;
            ansArr[index] = ansArr[index] % 10;
        }

        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer result = new StringBuffer();
        while (index < lengthA + lengthB) {
            result.append(ansArr[index]);
            index++;
        }

        return  result.toString();
    }

    //路径简化 TODO:要重新理解一下
    public static String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }

        StringBuffer buffer = new StringBuffer();
        if (stack.isEmpty()) {
            buffer.append("/");
        } else {
            while (!stack.isEmpty()) {
                buffer.append('/');
                buffer.append(stack.pollLast());
            }
        }

        return buffer.toString();
    }

    //动态规则 与0,1背包问题 TODO:
    //dp[i]表示字符串s的前 s[0..i-1]是否能被空格拆分成若干个字典中出现的单词
    //
    public static boolean wordBreak(String s, List<String>wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() +1];
        dp[0] = true;
        int length = s.length();
        for(int i = 1;i < length;i++) {
            for(int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static String reverseWords(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return  String.join(" ",wordList);
    }

    /**
     * 不包括重复字符的最长子串的长度
     * @param s 给定字符串s
     * @return 最长子串的长度
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        // 滑动窗口,存储当前已经
        Set<Character> originChar = new HashSet<>();
        int length = s.length();
        int rightIndex = -1;
        int tmpMax = 0;
        for (int index = 0; index < length; index++) {
            if (index != 0) {
                originChar.remove(s.charAt(index -1));
            }
            // 不断向右移动，走到找到一个有重复的字符为止
            while (rightIndex + 1 < length && !originChar.contains(s.charAt(rightIndex + 1))) {
                originChar.add(s.charAt(rightIndex + 1));
                ++rightIndex;
            }
            // 判断当前哪个是最长无重复的子串
            tmpMax = Math.max(tmpMax,rightIndex - index + 1);
        }

        return  tmpMax;

    }

    public List<String> restoreIpAddresses(String s) {
        int length = s.length();
        List<String> result = new ArrayList<>();
        if (length < 4 || length > 12) {
            return result;
        }

        Deque<String> path = new LinkedList<>();
        int splitTimes = 0;
        dfs(s,length,splitTimes,0,path,result);
        return result;

    }

    private void dfs(String s,int length,int split,int begin,Deque<String> path,List<String> result) {
        if (begin == length) {
            if (split == 4) {
                result.add(String.join(".",path));
            }
            return;
        }
        //
        if (length - begin < (4- split) || length - begin > 3 *(4 - split)) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (begin + i >= length) {
                break;
            }
            int ipSegment = judgeIfIpSegment(s,begin,begin + i);
            if (ipSegment != -1) {
                path.addLast(ipSegment + "");
                dfs(s,length,split + 1,begin + i + 1,path,result);
                path.removeLast();
            }
        }
    }

    public static int df(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return  df(n -1) + df (n - 2);
    }

    private int judgeIfIpSegment(String s,int left,int right) {
        int length = right - left + 1;
        if (length > 1 && s.charAt(left) == '0') {
            return -1;
        }

        int res = 0;
        for (int i = left; i <=right;i++) {
            res = res * 10 + s.charAt(i) - '0';
        }
        if (res > 255) {
            return -1;
        }
        return res;
    }

}
