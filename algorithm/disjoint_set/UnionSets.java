import java.util.*;

public class UnionSets{
    public static class Node{
        int value;
        int rank;
        Node parent;
    }

    private Map<Integer,Node> nodeMap;

    public UnionSets(){
        nodeMap = new HashMap<Integer,Node>();
    }
    
    public void makeSet(int value){
        Node node = new Node();
        node.rank = 0;
        node.value = value;
        node.parent = node;
        nodeMap.put(value, node);
    }

    public Integer findSet(int value){
        if(!nodeMap.containsKey(value))
            return null;
        return findSet(nodeMap.get(value)).value;
    }

    private Node findSet(Node node){
        if(node.parent != node)
            node.parent = findSet(node.parent);
        return node.parent;
    }
    
    public void profile(){
    	for(int key:nodeMap.keySet()){
    		Node node = nodeMap.get(key);
    		while(node.parent != node){
    			System.out.print(node.value + " -> ");
    			node = node.parent;
    		}
    		System.out.println(node.rank + "|" + node.value);
    	}
    	
    }

    public Integer union(int value1, int value2){
        Node node1,node2;
        node1 = nodeMap.get(value1);
        node2 = nodeMap.get(value2);
        if(node1 == null || node2 == null)
            return null;

        Node p1 = findSet(node1);
        Node p2 = findSet(node2);
        int value;        

        if(p1.rank > p2.rank){
            p2.parent = p1;
            value = p1.value;
        }
        else{
            p1.parent = p2;
            p2.rank = Math.max(p2.rank, p1.rank + 1);
            value = p2.value;
        }

        return value;
    }
    
    public static void main(String args[]){
        UnionSets us = new UnionSets();
        for(int i = 0 ; i < 16; i ++){
            us.makeSet(i);
        }

        
        us.union(1,2);
        us.union(3,2);
        us.union(4,5);
        us.union(14,2);
        us.union(4,2);
        

        us.profile();
        

        System.out.println(us.findSet(2) + " "+  us.findSet(14) +" "+  us.findSet(3));

    }
}
