import array.ArrayQuaTest;
import list.ListNode;

public class Main {

    public static void main(String[] args) {
//        System.out.println("hello");
//        ListNode node1 = newListNode1();
//        ListNode node2 = newListNode2();
//        ListNode[] nodeList = new ListNode[] {node1,node2};
//        LinkListQua.mergeKLists(nodeList);

    }

    public static ListNode newListNode2() {
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

        return nodeFirst;
    }

    public static ListNode newListNode1() {
        ListNode nodeFirst = new ListNode(1);
        ListNode nodeScenod = new ListNode(3);
        ListNode nodeThird = new ListNode(3);
        ListNode node4 = new ListNode(4);

        nodeFirst.next = nodeScenod;
        nodeScenod.next = nodeThird;
        nodeThird.next = node4;
        node4.next = null;

        return nodeFirst;
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
