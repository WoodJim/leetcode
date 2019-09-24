public class Main {

    public static void main(String[] args) {
        System.out.println("hello");
        ListNode nodeFirst = new ListNode(1);
        ListNode nodeScenod = new ListNode(2);
        ListNode nodeThird = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        nodeFirst.next = nodeScenod;
        nodeScenod.next = nodeThird;
        nodeThird.next = node4;
        node4.next = node5;
        node5.next = null;
        String test="34556&783020&=";
        System.out.println(test);

       ListNode head =  LinkListQua.swapParis(nodeFirst);
       ListNode expect = equalListNodeFirst();
       if (head.equals(expect)) {
           System.out.println("Success");
       } else {
           System.out.println("failure");
       }
        System.out.println(head);

    }

    public static ListNode equalListNodeFirst() {
//        before:1->2>3->4->5
//        after:2->1->4->3 ->5

        ListNode nodeFirst = new ListNode(1);
        ListNode nodeScenod = new ListNode(2);
        ListNode nodeThird = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        nodeScenod.next = nodeFirst;
        nodeFirst.next = node4;
        node4.next = nodeThird;
        nodeThird.next = node5;
        node5.next = null;

        return nodeScenod;
    }
}
