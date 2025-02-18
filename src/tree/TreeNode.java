package tree;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }

        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }

    public boolean hasPathSumV2(TreeNode root,int sum) {
        return false;
    }

    //中序遍历
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> tmpQue = new LinkedList<>();
        while (root != null || !tmpQue.isEmpty()) {
            while (root != null) {
                tmpQue.push(root);
                root = root.left;
            }
            if (!tmpQue.isEmpty()) {
                root = tmpQue.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return  result;

    }

    // 路径和
    public static List<List<Integer>> pathSum(TreeNode root,int targetSum) {
        List<List<Integer>> result = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        dfs(root,targetSum,result,path);
        return result;

    }

    public static void dfs(TreeNode root,int targetSum,List<List<Integer>> result,Deque<Integer> path) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        targetSum-=root.val;
        if (root.left == null  && root.right == null && targetSum == 0) {
            result.add(new LinkedList<>(path));
        }
        dfs(root.left,targetSum,result,path);
        dfs(root.right,targetSum,result,path);
        path.pollLast();
    }

    ///TODO:其他的内容逻辑
//    public static TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q) {
//        if (root == null) {
//            return null;
//        }
//
//    }

//    public static int widthOfBinaryTree(TreeNode root) {
//        int res = 1;
//    }



}
