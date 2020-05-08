package Dijkstra;

import java.util.*;

public class MainClass {

    PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.distFromRoot - b.distFromRoot);
    Set<Character> visited = new HashSet<Character>();

    private void printDistancesFrom(Node root) {
        root.distFromRoot = 0;
        pq.offer(root);
        while(!pq.isEmpty()){
            Node currentNode = pq.poll();
            visited.add(currentNode.data);
            for(Map.Entry<Node,Integer> e : currentNode.next.entrySet()){
                Node nextNode = e.getKey();
                if(visited.contains(nextNode.data))continue;
                int distToNode = e.getValue();
                if(currentNode.distFromRoot + distToNode < nextNode.distFromRoot){
                    nextNode.distFromRoot = currentNode.distFromRoot + distToNode;
                    pq.offer(nextNode);
                }
            }
        }
        printTree(root,new HashSet<Character>());
    }
    /*
    pq[]
    visited[]
    a = 0
    b = 5
    c = 2
    d = 6
    e = 12
    f = 8
    g = 15
    h = 11
    i = 9
    j = 8
    k = 21


     */

    public void printTree(Node node,Set<Character> v){
        System.out.println(node);
        v.add(node.data);
        for(Map.Entry<Node,Integer> e : node.next.entrySet()){
            if(v.contains(e.getKey().data))continue;
            printTree(e.getKey(),v);
        }
    }

    public static void main(String[] args) {
        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');
        Node f = new Node('F');
        Node g = new Node('G');
        Node h = new Node('H');
        Node i = new Node('I');
        Node j = new Node('J');
        Node k = new Node('K');
        a.next.put(b,5);
        a.next.put(j,8);
        a.next.put(c,2);
        b.next.put(e,7);
        b.next.put(f,6);
        c.next.put(f,6);
        c.next.put(d,4);
        d.next.put(h,5);
        e.next.put(i,16);
        e.next.put(f,1);
        f.next.put(i,1);
        f.next.put(d,7);
        g.next.put(k,9);
        g.next.put(i,9);
        h.next.put(g,4);
        h.next.put(j,10);
        i.next.put(k,12);

        MainClass main = new MainClass();
        main.printDistancesFrom(a);
    }
}
