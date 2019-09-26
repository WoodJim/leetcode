public class LinkListQua {

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

    public static ListNode swapTwoNode(ListNode pre, ListNode node, ListNode next) {
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
     * 23. Merge k Sorted Lists
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

}
