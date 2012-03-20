import java.util.*;
public class LinkedList<T>{
    private class Node<T>{
        T value;
        Node<T> next;
        Node(T t){
            this.value = t;
        }
    }

    public LinkedList(Comparator<T> c){
        this.c = c;
    }

    private Node<T> head; 
    private Node<T> tail;
    private Comparator<T> c;

    public LinkedList add(T t){
        Node<T> node = new Node<T>(t);
        if(head == null){
            head = node;
            tail = node;
        }
        else{
            tail.next = node;
            tail = node;
        }
        return this;
    }

    public void print(){
        Node<T> p = head;
        while(p != null){
            System.out.println(p.value);
            p = p.next;
        }
    }

   public void quickSort(){
        if(head != tail)
            quickSort(null, head, tail);
   }

   private void quickSort(Node<T> pre, Node<T> start, Node<T> end){
        if(start == end)
            return;
        Node<T> next =  end.next;
        Node<T> pivot = start;
        Node<T> cur  = pivot.next; 
        Node<T> small_head = null , small = null; 
        Node<T> big_head = null , big   = null; 
        Node<T> pre_pivot = null;
        Node<T> post_pivot = null;
        while(true){
            boolean meet = false;
            if(start == end)
                meet = true;
            if(c.compare(pivot.value, start.value) < 0){
                if(big == null)
                {
                    big_head = big = start;
                }
                else{
                    big.next = start;
                    big = big.next;
                }
            }
            else{
                if(small == null){
                    small_head = small = start;
                }
                else{
                    small.next = start;
                    small = small.next;
                }
            }
            start = start.next;
            if(meet)
                break;
        }
        if(small_head != null){
            start = small_head;
            small.next = pivot;
            end   = pivot;
            pre_pivot = small;
        }
        else{
            start = pivot;
            end   = pivot;
        }
        if(big_head != null){
            post_pivot = big_head;
            end.next = big_head;
            end  = big;
        }
        if(pre != null){
            pre.next = start;
        }
        else
            head = start;

        if(next != null){
            end.next = next;
        }
        else
            tail  = end;

        if(pre_pivot != null)
            quickSort(pre, start, pre_pivot);
        if(post_pivot != null)
            quickSort(pivot, post_pivot, end);
   }

   public void mergeSort(){
   }

   public void bubbleSort(){
   }

   public void insertSort(){
   }

   public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<Integer>(new Comparator<Integer>(){
                public int compare(Integer a, Integer b){
                    return a - b;
                }
            });

        list.add(5).add(4).add(3).add(2).add(1);
        list.print();
        list.quickSort();
        list.print();
   }
}
