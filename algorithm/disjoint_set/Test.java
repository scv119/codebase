import java.util.*;

public class Test{
    public static void test_lca(){
        Tree t = new Tree();
        t.addNode(null, 0);
        Tree.Node root = t.getRoot();
        for(int i = 1; i <= 5; i ++){
            Tree.Node node = t.addNode(root, i);
            for(int j = 1; j <= 5; j ++){
                t.addNode(node, i*5 + j);
            }
        }
        
        List<Tree.Pair> lists = new ArrayList<Tree.Pair>();
        lists.add(new Tree.Pair(1,2)); 
        lists.add(new Tree.Pair(17,2)); 
        lists.add(new Tree.Pair(9,8)); 
        lists.add(new Tree.Pair(9,12)); 
        lists.add(new Tree.Pair(22,21)); 
        lists.add(new Tree.Pair(17,5)); 

        Map<Tree.Pair,Integer> result = Tree.LCA(t, lists);
        for(Tree.Pair p:result.keySet()){
            System.out.print(p.i+" "+p.j+" "+result.get(p)+"\n");
        }
    }

    public static void main(String args[]){
        test_lca();
    }
}
