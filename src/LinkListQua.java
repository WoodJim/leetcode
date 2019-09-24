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
        head = swapTwoNode(null,head,nextNode);

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
            ListNode tmp  = swapTwoNode(preNode,first,second);
            if (tmp != null) {
                preNode = tmp.next;
            } else{
                preNode = null;
            }

        }

        return head;
    }

    public static ListNode swapTwoNode(ListNode pre,ListNode node,ListNode next) {
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

}
