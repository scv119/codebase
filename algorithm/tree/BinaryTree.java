import java.util.*;

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

    public static void inOrderTravasal(Node node){
        if(node != null){
            inOrderTravasal(node.leftChild);
            System.out.print(node.value + " ");
            inOrderTravasal(node.rightChild);
        }
    }

    public static int minDepth(Node node){
        if(node == null)
            return 0;
        return Math.min(1 + minDepth(node.leftChild), 1 + minDepth(node.rightChild));
    }

    public static int maxDepth(Node node){
        if(node == null)
            return 0;
        return Math.max(1 + maxDepth(node.leftChild), 1 + maxDepth(node.rightChild));
    }

    public static List<List<Node>> findLevelLinkedList(Node start){
        List<List<Node>> result= new ArrayList<List<Node>>();
        List<Node> cur = new LinkedList<Node>();
        List<Node> tmp = null;
        cur.add(start);

        while(cur.size()>0){
            result.add(cur);
            tmp = cur; 
            cur = new LinkedList<Node>();
            for(Node node:tmp){
                if(node.leftChild != null)
                    cur.add(node.leftChild);
                if(node.rightChild != null)
                    cur.add(node.rightChild);
            }
        }
        return result;
    }

    public static void main(String args[]){
        BinaryTree t = new BinaryTree();
        for(int i = 0 ;i < 15; i ++){
            t.add(i);
        }

        inOrderTravasal(t.getRoot());
        System.out.println();
        System.out.println(t.find(5).value); 
        System.out.println(maxDepth(t.getRoot()));
        System.out.println(minDepth(t.getRoot()));

        
        Node node = t.find(5);
        t.delete(node.rightChild, node);
        inOrderTravasal(t.getRoot());
        System.out.println();
    }
}
