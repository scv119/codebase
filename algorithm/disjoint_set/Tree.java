import java.util.*;

public class Tree{
    public static class Node{

        int value;
        List<Node> children;

        public Node(){
            children = new ArrayList<Node>();
        }
    }

    private Node root;

    public Node getRoot(){
        return root;
    }
    
    public Tree(){
        root = null;
    }

    public Node addNode(Node parent, int value){
        if(parent == null && root == null){
            root = new Node();
            root.value = value;
            return root;
        }else if(parent != null){
            Node node = new Node();
            node.value = value;
            parent.children.add(node);
            return node;
        }
        return null;
    }

    public static class Pair{
        public Pair(int i , int j){
            this.i = i;
            this.j = j;
        }
        public int i;
        public int j;
    }

    public static Map<Pair, Integer> LCA(Tree tree, List<Pair> pairs){
        UnionSets us = new UnionSets(); 
        Node root = tree.getRoot();
        Map<Pair,Integer> result = new HashMap<Pair,Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        Map<Integer, Integer> ancestor= new HashMap<Integer,Integer>();
        lca(root, pairs, us, visited, ancestor,  result);
        return result;
    }

    private static void lca(Node node, List<Pair> pairs, UnionSets us, Set<Integer> visited,Map<Integer,Integer> ancestor,  Map<Pair,Integer> result){
        us.makeSet(node.value);
        ancestor.put(us.findSet(node.value), node.value);

        for(Node child:node.children){
            lca(child, pairs, us, visited, ancestor, result);
            us.union(node.value, child.value);
            ancestor.put(us.findSet(child.value), node.value);
        }
        
        visited.add(node.value);
        for(Pair pair :pairs){
            if(pair.i == node.value && visited.contains(pair.j)){
                result.put(pair, us.findSet(pair.j));
            }
            else if(pair.j == node.value && visited.contains(pair.i)){
                result.put(pair, us.findSet(pair.i));
            }
        }
    }

    
}
