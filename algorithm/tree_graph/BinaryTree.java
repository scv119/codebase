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

    public Node delete(Node node, Node parent){
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
}
