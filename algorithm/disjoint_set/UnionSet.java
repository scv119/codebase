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
        Node node = new Node()
        node.rank = 0;
        node.value = value;
        node.parent = node;
        nodeMap.put(value, node);
    }

    public Integer findSet(int value){
        if(!nodeMap.contains(value))
            return null;
        return findSet(node).value;
    }

    private Node findSet(Node node){
        if(node.parent != node)
            node.parent = findSet(node.parent);
        return node.parent;
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

        if(p1.rank > p2.rank)
            p2.parent = p1;
            value = p1.value;
        else{
            p1.parent = p2;
            p2.rank = Math.max(p2.rank, p1.rank + 1);
            value = p2.value;
        }

        return value;
    }

}
