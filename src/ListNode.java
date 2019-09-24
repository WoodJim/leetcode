import java.util.Comparator;

public class ListNode {
    int val;
    ListNode next;
    public ListNode(int x) {
        val = x;
    }


    @Override
    public int hashCode() {
        int hash = next == null ? 0 : next.hashCode();
        return val + hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  ListNode)) {
            return false;
        }
        ListNode tmp = (ListNode)obj;
        if (tmp.val != this.val) {
            return false;
        }

        if (tmp.next == null && this.next == null) {
            return true;
        }

        if (tmp.next != null && this.next != null) {
            return tmp.next.equals(this.next);
        }

        return false;
    }
}
