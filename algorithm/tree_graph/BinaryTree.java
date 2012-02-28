public class BinaryTree{
    public static class Node{
        int value;
        Node leftChild;
        Node rightChild;
    }

    private Node root;

    public BinaryTree(){
        root = null;
    }

    public void add(int value){
        
        Node cur = root;
        Node pre = null;
        boolean left = true;
        
        while(cur != null){
            if (value < cur.value){
                pre = cur;
                cur = cur.leftChild; 
                left = true;
            }
            else{
                pre = cur;
                cur = cur.rightChild;
                left = false;
            }
        }
        
        cur = new Node();
        cur.value = value;

        if(pre != null){
            if(left)
                pre.leftChild = cur;
            else
                pre.rightChild = cur;
        }
    }

    public Node find(int value){
        Node cur = root;
        while(cur != null){
            if(cur.value == value)
                return cur;
            if(cur.value < value)
                cur = cur.rightChild;
            else
                cur = cur.leftChild;
        }
        return null;
    }

    public Node del(int value){
        
    }

}
