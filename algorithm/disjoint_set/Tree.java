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
        for(Pair pair:pairs){
            result.put(pair, -1);
        }
        Set<Integer> visited = new HashSet<Integer>();
        Map<Integer, Integer> ancestor= new HashMap<Integer,Integer>();
        lca(root, result, us, visited, ancestor);
        return result;
    }

    private static void lca(Node node, Map<Pair, Integer> pairs, UnionSets us, Set<Integer> visited,Map<Integer,Integer> ancestor){
        us.makeSet(node.value);
        ancestor.put(us.findSet(node.value), node.value);

        for(Node child:node.children){
            lca(child, pairs, us, visited, ancestor);
            us.union(node.value, child.value);
            ancestor.put(us.findSet(child.value), node.value);
        }
        
        visited.add(node.value);
        for(Pair pair :pairs.keySet()){
            if (pairs.get(pair) != -1)
                continue;
            if(pair.i == node.value && visited.contains(pair.j)){
                pairs.put(pair, us.findSet(pair.j));
            }
            else if(pair.j == node.value && visited.contains(pair.i)){
                pairs.put(pair, us.findSet(pair.i));
            }
        }
    }

    
}
