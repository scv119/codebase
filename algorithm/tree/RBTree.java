import java.util.*;
public class RBTree<K,V>{
    private static final Node NIL = new Node();
    
    static{
        NIL.color = Color.BLACK;
    }

    private enum  Color{
        RED,
        BLACK;
    }
    private static class Node<K,V>{
        Node(){
            color = Color.RED;
            p = l = r = NIL;
        }
        Node(K k, V v){
            color = Color.RED;
            p = l = r = NIL;
            key = k;
            value = v;
        }
        Color color;
        Node p;
        K    key;
        V    value;
        Node l;
        Node r;
    }

    private Node root;
    private Comparator<K> c;

    public RBTree(){
        root = NIL;
        c = new Comparator<K>(){
           @Override
           public int compare(K k1, K k2){
                return k1.hashCode() - k2.hashCode();
           }
        };
    }
    public RBTree(Comparator<K> comp){
        root = NIL;
        c    = comp;
    }

    public V get(K k){
        return null;
    }

    public void put(K k, V v){
        Node node = new Node(k,v);
        insert(node);
    }

    private void insert(Node<K,V> node){
        if(root == NIL)
        {
            root = node;
            node.color = Color.BLACK;
        }
        else{
            Node<K,V> cur = root;
            while(true){
                if(c.compare(cur.key, node.key) <=0){
                    if(cur.r == NIL){
                        cur.r = node;
                        node.p = cur;
                        break;
                    }
                    else{
                        cur = cur.r; 
                        continue;
                    }
                }
                else{
                    if(cur.l == NIL){
                        cur.l = node;
                        node.p = cur;
                        break;
                    }
                    else{
                        cur = cur.l;
                        continue;
                    }
                }
            }
            fix_insert(node);
        }
    }

    private void fix_insert(Node node){
        Node p = node.p;
        if(p.color == Color.BLACK)
            return;
        if(p == p.p.l){
        //p is left child of p.p
            Node uncle = p.p.r;
            if(uncle.color == Color.RED){
                if(p.p == root){
                    p.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                }
                else{
                    p.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    p.p.color = Color.RED;
                    fix_insert(p.p);
                }
            }
            else{
                Node anc = p.p;
               if(p.r == node){
                    rotate(node, true);
               }
               rotate(anc.l, false);
               anc.color = Color.RED;   
               anc.p.color = Color.BLACK;
            }
        }
        else{
            Node uncle = p.p.l;
            if(uncle.color == Color.RED){
                if(p.p == root){
                    p.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                }
                else{
                    p.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    p.p.color = Color.RED;
                    fix_insert(p.p);
                }
            }
            else{
                Node anc = p.p;
               if(p.l == node){
                    rotate(node, false);
               }
               rotate(anc.r, true);
               anc.color = Color.RED;   
               anc.p.color = Color.BLACK;
            }
        }
    }
    
    public V remove(K k){
        return null;
    }

    private void rotate(Node a, boolean left){
        Node p = a.p;
        Node b = null;
        if(left){
            b = a.r;
            Node y = p.l;
            a.r    = y;
            y.p    = a;
            b.l    = a;
            a.p    = b;
            b.p    = p;
        }
        else{
            b = a.l;
            Node y = p.r;
            a.l    = y;
            y.p    = a;
            b.r    = a;
            a.p    = b;
            b.p    = p;
        }
        if(p == NIL)
            root = b;
        else{
            if (p.l == a)
                p.l = b;
            else
                p.r = b;
        }
    }

    public boolean validate_RB(){
        int[] result = blackMaxMinSize(root);
        return result[2] == 0;
    }

    private int[] blackMaxMinSize(Node a){
        int[] result = new int[3];  
        if (a == NIL)
        {
            result[0] = 1;
            result[1] = 1;
        }
        else{
            if(a == root && a.color != Color.BLACK)
            {
                result[2] = 1;
                return result;
            }

            int isBlack = 0 ;
            if(a.color == Color.BLACK)
                isBlack = 1;
            else{
                if(a.l.color == Color.RED || a.r.color == Color.RED)
                {
                   result[2] = 1;
                   return result;
                }
            }
            int[] lMinMax = blackMaxMinSize(a.l);
            int[] rMinMax = blackMaxMinSize(a.r);

            result[2] = lMinMax[2] + rMinMax[2];
            if(result[2] >= 0)
                return result;
            result[0] = Math.min(lMinMax[0], rMinMax[0]) + isBlack;
            result[1] = Math.max(lMinMax[1], rMinMax[1]) + isBlack;
            if(result[0] != result[1])
                result[2] = 1;
        }
        return result;
    }

    public static void main(String args[]){
        RBTree<Integer,Integer> tree = new RBTree<Integer,Integer>();
        for(int i = 0 ; i < 3; i ++)
        {
            tree.put(i,i);
            System.out.println(tree.validate_RB());
            tree.dumpTree();
        }
    }

    public void dumpTree(){
        dumpTree(root);
    }

    private void dumpTree(Node<K,V> node){
        if(node == NIL)
            return;
        dumpTree(node.l);
        if(node.p != NIL)
            System.out.println(node.key + " " + node.p.key +" "+ node.color);
        else
            System.out.println(node.key + " root  "+ node.color);
        dumpTree(node.r);
    }
}
