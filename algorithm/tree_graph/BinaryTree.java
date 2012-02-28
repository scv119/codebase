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

    public Node getRoot(){
        return root;
    }

    public void add(int value){
        
        Node cur = root;
        Node pre = null;
        boolean left = true;
        
        while(cur != null){
            if(value == cur.value){
                return;
            }
            else if (value < cur.value){
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
        else 
            root = cur;
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

    public void delete(Node node, Node parent){
        boolean isLeftChild = true;
        if (parent != null){
            if(parent.leftChild != node)
                isLeftChild = false;
        }
        if(node.leftChild == null){
            if(parent != null) {
                if(isLeftChild)
                    parent.leftChild = node.rightChild;
                else
                    parent.rightChild = node.rightChild;
            }
            else{
                root = node.rightChild;
            }
        }
        else if(node.rightChild == null){
            if(parent != null){
                if(isLeftChild)
                    parent.leftChild = node.leftChild;
                else
                    parent.rightChild = node.leftChild;
            }
            else{
                root = node.leftChild;
            }
        }
        else{
            Node successorParent = node;
            Node successor = node.rightChild;

            while(successor.leftChild != null){
                successorParent = successor;
                successor = successor.leftChild;
            }
    
            Node successorRightChild = successor.rightChild;
            
            if(parent != null){
                if(isLeftChild)
                    parent.leftChild = successor;
                else
                    parent.rightChild = successor;
            }
            else{
                root = successor;
            }

            successor.leftChild = node.leftChild;   
            
            if(successorParent != node){
                successor.rightChild = node.rightChild;
                successorParent.leftChild = successorRightChild;
            }
        }
    }

    public void inOrderTravasal(Node node){
        if(node != null){
            inOrderTravasal(node.leftChild);
            System.out.print(node.value + " ");
            inOrderTravasal(node.rightChild);
        }
    }

    public static void main(String args[]){
        BinaryTree t = new BinaryTree();
        for(int i = 0 ;i < 15; i ++){
            t.add(i);
        }

        t.inOrderTravasal(t.getRoot());
        System.out.println();
        System.out.println(t.find(5).value); 
        
        Node node = t.find(5);
        t.delete(node.rightChild, node);
        t.inOrderTravasal(t.getRoot());
        System.out.println();
    }
}
