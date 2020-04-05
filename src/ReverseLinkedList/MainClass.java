package ReverseLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null)return head;
        ListNode i = null;
        ListNode j = head;
        while(m>1){
            i = j;
            j=j.next;
            m--;
            n--;
        }
        ListNode conn = i;
        ListNode tail = j;
        while(n>0){
            ListNode tmp = j.next;
            j.next = i;
            i = j;
            j = tmp;
            n--;
        }
        if(conn!=null)conn.next = i;
        else head = i;
        tail.next = j;
        return head;
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);
            line = in.readLine();
            int m = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);

            ListNode ret = new Solution().reverseBetween(head, m, n);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}