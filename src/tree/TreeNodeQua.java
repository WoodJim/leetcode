package tree;

import java.util.*;

public class TreeNodeQua {

    /// 之字形
    public List<List<Integer>> zigLevelOrder(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        if (root != null) {
            deque.add(root);
        }
        while (!deque.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();
            //从左向右打印
            for (int i = deque.size(); i >0; i--) {
                TreeNode node = deque.removeFirst();
                tmp.add(node.val);
                //先左后右加入下层结点
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
            result.add(tmp);
            if (deque.isEmpty()) {
                break;
            }
            // 从右向左打印
            tmp = new LinkedList<>();
            for (int i = deque.size(); i > 0; i++) {
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                // 先右后左加入下层结点
                if (node.right != null) {
                    deque.addFirst(node.right);
                }
                if (node.left != null) {
                    deque.addFirst(node.left);
                }
            }
        }

        return result;
    }


    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                // 左子树的最右节点
                TreeNode tmp = root.left;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                // 将右子树挂到左子树的右边
                tmp.right = root.right;
                // 右子树 指向原来的左子树
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }


    // 最近公共祖先

    public static TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left != null && right != null) {
            return root;
        }
        if (right == null) {
            return left;
        }

        return right;
    }

    //二叉树右视图
    public static List<Integer> rightSideView(TreeNode root) {
        Map<Integer,Integer> rightmostValueAtDepth = new HashMap<>();
        int maxDepth = 1;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.add(root);
        depthQueue.add(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();
            if (node != null) {
                maxDepth = Math.max(maxDepth,depth);
                rightmostValueAtDepth.put(depth,node.val);
            }
            if (node.left != null) {
                nodeQueue.add(node.left);
                depthQueue.add(depth + 1);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                depthQueue.add(depth + 1);
            }

        }
        List<Integer> right = new ArrayList<>();
        for (int depth = 0; depth <= maxDepth; depth++) {
            right.add(rightmostValueAtDepth.get(depth));
        }

        return right;
    }

//    public static TreeNode coverTreeToList(TreeNode root) {
//        if (root == null ) {
//            return root;
//        }
//        Queue<TreeNode> listNode = prePathTree(root);
//        TreeNode result = null;
//        TreeNode pre = null;
//        // 对节点的node值进行处理
//        while (!listNode.isEmpty()) {
//            TreeNode tmp = listNode.poll();
//            if (result == null) {
//                result = tmp;
//                result.left = null;
//                pre = result;
//            } else {
//                pre.right = tmp;
//                tmp.left = null;
//                pre = tmp;
//            }
//
//        }
//        return result;
//    }
//
//    // 先序遍历
//    public static Queue<TreeNode> prePathTree(TreeNode root) {
//        Queue<TreeNode> listNode = new ArrayDeque<>();
//        if (root == null) {
//            return listNode;
//        }
//        listNode.add(root);
//        Queue<TreeNode> leftNode = prePathTree(root.left);
//        if (!leftNode.isEmpty()) {
//            listNode.addAll(leftNode);
//        }
//        Queue<TreeNode> rightNode = prePathTree(root.right);
//        if (!rightNode.isEmpty()) {
//            listNode.addAll(rightNode);
//        }
//
//        return listNode;
//    }
//
//    // 90度旋转
    public static int[][] translateArray(int [][] matrix) {
        if (matrix == null) {
            return matrix;
        }
        int n = matrix.length - 1;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < (n + 2)/ 2;j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n  - j][i];
                matrix[n - j][i] = matrix[n  - i][n  - j];
                matrix[n - i][n - j] = matrix[j][n - i];
                matrix[j][n  - i] = tmp;

            }
        }
//        int tmpLength = length - j;
//        for (j = 0; j < tmpLength; j++) {
//            for (int i = j; i < tmpLength; i++) {
//                int tmp = object[i][tmpLength];
//                object[i][tmpLength] = object[j][i];
//                object[j][i] = object[tmpLength][i];
//                object[tmpLength][tmpLength - i] = tmp;
//            }
//            tmpLength = tmpLength - 1;
//        }
        return matrix;
    }
}
