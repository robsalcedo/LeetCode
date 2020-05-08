package Dijkstra;

import java.util.*;

public class Node {
    char data;
    Map<Node,Integer> next;
    int distFromRoot;

    public Node(char c){
        this.data = c;
        this.next = new HashMap<Node,Integer>();
        this.distFromRoot = Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return this.data+": "+this.distFromRoot;
    }
}
