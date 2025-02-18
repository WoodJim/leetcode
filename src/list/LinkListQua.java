package list;

import java.util.PriorityQueue;

public class LinkListQua {


    /**
     * https://leetcode.com/problems/swap-nodes-in-pairs/
     * 24 Swap Nodes in Pairs
     *
     * Given 1->2->3->4,
     * you should return the list as 2->1->4->3.
     * @param head
     * @return
     */
    public static ListNode swapParis(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode nextNode = head.next;
        if (nextNode == null) {
            return head;
        }

        ListNode nextNextNode = nextNode.next;
        head = swapTwoNode(null, head, nextNode);

        ListNode preNode = null;
        if (head != null) {
            preNode = head.next;
        }
        while (nextNextNode != null) {
            ListNode first = nextNextNode;
            ListNode second = nextNextNode.next;
            if (second != null) {
                nextNextNode = second.next;
            } else {
                nextNextNode = null;
            }
            ListNode tmp = swapTwoNode(preNode, first, second);
            if (tmp != null) {
                preNode = tmp.next;
            } else {
                preNode = null;
            }

        }

        return head;
    }

    private static ListNode swapTwoNode(ListNode pre, ListNode node, ListNode next) {
        if (next == null) {
            return node;
        }
        ListNode nextTmp = next.next;
        next.next = node;
        node.next = nextTmp;
        if (pre != null) {
            pre.next = next;
        }
        return next;
    }

    /**
     * https://leetcode.com/problems/merge-k-sorted-lists/
     * 23. Merge k Sorted Lists 合并K个链接
     * Input:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * Output: 1->1->2->3->4->4->5->6
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length <= 0) {
            return null;
        }

        int length = lists.length;
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;
        boolean isUpdate = false;
        boolean isBreak;
        while (true) {
            isBreak = true;
            for (int i = 0; i < length; i++) {
                ListNode node = lists[i];
                if (node == null) {
                    continue;
                }
                isBreak = false;
                int value = node.val;
                if (value < minValue) {
                    minValue = value;
                    minIndex = i;
                    isUpdate = true;
                }
            }

            if (isBreak) {
                break;
            }

            if (!isUpdate) {
                continue;
            }

            tmp.next = lists[minIndex];
            ListNode tmpNode = lists[minIndex].next;
            tmp = tmp.next;
            tmp.next = null;
            lists[minIndex] = tmpNode;
            minValue = Integer.MAX_VALUE;

            isUpdate = false;

        }
        return head.next;
    }


    /**
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     *
     * Given linked list: 1->2->3->4->5, and n = 2.
     *
     * return  1->2->3->5.
     * @param head
     * @param n
     * @return
     */
    public static  ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <=0) {
            return head;
        }
        ListNode quickTmp = head;
        int index = 0;
        while (index < n && quickTmp != null) {
            quickTmp = quickTmp.next;
            index++;
        }

        //remove the head node;
        if (quickTmp == null) {
            ListNode tmp = head.next;
            head.next = null;
            return tmp;
        }
        ListNode slowTmp = head;
        while (quickTmp.next != null) {
            slowTmp = slowTmp.next;
            quickTmp = quickTmp.next;
        }

        slowTmp.next = slowTmp.next.next;

        return head;
    }


    /**
     * 反转链表
     *  206 Reverse Linked List
     *  eg input head=[1,2,3,4,5]
     *  outpug = [5.4.3.2,1]
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return  head;
        }
        ListNode first = head;
        ListNode second;
        ListNode pre = null;
        while (first != null) {
            second = first.next;
            first.next = pre;
            pre = first;
            first = second;
        }

        return  pre;
    }

    /**
     * 25  Reverse Nodes in k-Group
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return  head;
        }
        ListNode first = head;
        int count = 0;
        while (first != null && count != k) {
            first = first.next;
            count++;
        }
        if (count == k) {
            first = reverseKGroup(first,k);
            while (count-- > 0) {
                ListNode tmp = head.next;
                head.next = first;
                first = head;
                head = tmp;
            }
        }
        return first;
    }

    public static ListNode reverseKGroup2(ListNode head,int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        int index = 0;
        ListNode tmpHead = new ListNode(-1);
        tmpHead.next = head;
        ListNode begin = tmpHead;
        while (head != null) {
            index++;
            if (index % k == 0) {
                begin = reverse(begin,head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return  tmpHead.next;
    }

    public static ListNode reverse(ListNode start,ListNode end) {
        //TODO:reverseList;
        // 记录下头节点下一个节点
        ListNode curNode = start.next;
        ListNode firstNode,nextNode;
        ListNode pre = start;
        firstNode = curNode;
        while (curNode != end) {
            nextNode = curNode.next;
            curNode.next = pre;
            pre = curNode;
            curNode = nextNode;
        }
        start.next = pre;
        firstNode.next = curNode;

        return firstNode;
    }


    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        while (n % 3 == 0) {
            n = n / 3;
        }
        if (n == 1) {
            return true;
        }
        return false;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return  head;
        }
        ListNode result = head;
        ListNode tmp = head;
        while (tmp.next != null && tmp.next.next != null) {
            if (tmp.next.val != tmp.next.next.val) {
                tmp = tmp.next;
                continue;
            }
            int value = tmp.next.val;
            while (tmp.next != null && tmp.next.val == value) {
                tmp.next = tmp.next.next;
            }

        }

        return result;
    }

    // 链表相交的内容
    public static ListNode getIntersectionNode(ListNode nodeA,ListNode nodeB) {
        if (nodeA == null || nodeB == null) {
            return null;
        }
        ListNode tmpA = nodeA;
        ListNode tmpB = nodeB;
        int lengthA = 0;
        int lengthB = 0;
        while (tmpA != null) {
            lengthA++;
            tmpA = tmpA.next;
        }
        while (tmpB != null) {
            lengthB++;
            tmpB = tmpB.next;
        }

        tmpA = nodeA;
        tmpB = nodeB;
        if (lengthA > lengthB) {
            int count = lengthA - lengthB;
            int index =0;
            while (index < count) {
                tmpA = tmpA.next;
                index++;
            }
        } else if (lengthA < lengthB) {
            int count = lengthB - lengthA;
            int index =0;
            while (index < count) {
                tmpB = tmpB.next;
                index++;
            }
        }

        if (tmpA == tmpB) {
            return tmpA;
        }
        while (tmpA != null && tmpB != null) {
            tmpA = tmpA.next;
            tmpB = tmpB.next;
            if (tmpA == tmpB) {
                return tmpA;
            }
        }

        return null;
    }

    public ListNode mergeTwoLists(ListNode list1,ListNode list2) {
        
        ListNode preHead = new ListNode(-1);
        ListNode preNode = preHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                preNode.next = list1;
                list1 = list1.next;
            } else {
                preNode.next = list2;
                list2 = list2.next;
            }
            preNode = preNode.next;
        }
        if (list1 != null) {
            preNode.next = list1;
        } else if (list2 != null) {
            preNode.next = list2;
        } else {
            preNode.next = null;
        }

        return  preHead.next;
    }

    /**
     * K个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public static ListNode revertKGroup(ListNode head,int k) {
        // 增加一个哨兵节点，返回该节点的下一个
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return  hair.next;
                }
            }
            // head为当前要翻转的头节点
            // tail为当前要翻转的尾节点
            // 存储下一个要翻转的节点
            ListNode next = tail.next;
            ListNode[]reverse = myReverse(head,tail);
            // 重新将链表接回原链接
            head = reverse[0];
            tail = reverse[1];
            pre.next = head;
            tail.next = next;
            head = tail.next;

        }
        return hair.next;
    }

    private static  ListNode[] myReverse(ListNode head,ListNode tail) {
        ListNode pre = tail.next;
        ListNode temp = head;
        while (pre != tail) {
            ListNode next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        return  new ListNode[]{tail,head};
    }




}
