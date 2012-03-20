import java.util.*;
public class LinkedList<T>{
    private class Node<T>{
        T value;
        Node<T> next;
        Node(T t){
            this.value = t;
            next = null;
        }
    }

    public LinkedList(Comparator<T> c){
        this.c = c;
        this.head = this.tail = null;
    }

    public void clear(){
        this.head = this.tail = null;
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

    public boolean valid(){
        Node<T> p = head;
        T previous = null; 
        while(p != null){
            if(previous != null && c.compare(previous, p.value) > 0)
                return false;
            previous = p.value;
            p = p.next;
        }
        return true;
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
            if(cur == end)
                meet = true;
            Node<T> cur_Next = cur.next;
            if(c.compare(pivot.value, cur.value) < 0){
                if(big == null)
                {
                    big_head = big = cur;
                }
                else{
                    big.next = cur;
                    big = big.next;
                }
            }
            else{
                if(small == null){
                    small_head = small = cur;
                }
                else{
                    small.next = cur;
                    small = small.next;
                }
            }
            cur = cur_Next; 
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
        tail.next = null;
        
        if(pre_pivot != null)
            quickSort(pre, start, pre_pivot);
        if(post_pivot != null)
            quickSort(pivot, post_pivot, end);
   }

    public void mergeSort(){
         mergeSort(head, tail, true); 
    }

    private Node<T> mergeSort(Node<T> start, Node<T> end, boolean isFirst){
        if(start == end)
            return start;

        Node<T> mid= start, pEnd = start.next;
        while(true){
            if(pEnd == end)
                break;
            pEnd = pEnd.next;
            if(pEnd == end)
                break;
            mid = mid.next;
            pEnd = pEnd.next;
        }
        
        Node<T> midNext = mid.next;
        mid.next = end.next = null;

        Node<T> first = mergeSort(start, mid, false);
        Node<T> second = mergeSort(midNext, end, false);

        Node<T> combinedHead = null;
        Node<T> combined = null;
        while(true){
            if(first == null && second == null)
                break;
            boolean pickFirst = true;
            if(first == null)
                pickFirst = false;
            else if(second == null)
                pickFirst = true;
            else if(c.compare(first.value, second.value) <= 0)
                pickFirst = true;
            else
                pickFirst = false;
            if(pickFirst){
                if(combined != null)
                    combined.next = first;
                if(combinedHead == null)
                    combinedHead = first;
                combined = first;
                first = first.next;
            }
            else{
                if(combined != null)
                    combined.next = second;
                if(combinedHead == null)
                    combinedHead = second;
                combined = second; 
                second= second.next;
            }
        }
        combined.next = null;
        if(isFirst)
        {
            head = combinedHead;
            tail = combined;
        }
        return combinedHead; 
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

        for(int i = 0 ; i < 10000 ; i ++){
            list.add((int)(10000 * Math.random()));
        }
        list.quickSort();
        System.out.println(list.valid());
        list.clear();
        for(int i = 0 ; i < 10 ; i ++){
            list.add((int)(10000 * Math.random()));
        }
        list.mergeSort();
        System.out.println(list.valid());
        list.print();
   }
}
