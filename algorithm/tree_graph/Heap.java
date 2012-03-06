import java.util.*;
public class Heap<T>{
    private Comparator<T> comp;
    private List<T> array;

    public Heap(List<T> list, Comparator<T> comp){
        this.comp = comp;
        this.array = new ArrayList<T>();
        for(T t:list){
            array.add(t);
        }
        for(int n = array.size()/2 -1; n >= 0; n --){
            heapify_down(n);
        }
    }

    private void swap(int idx1, int idx2){
        if(idx1 == idx2)
            return;
        T t = array.get(idx1);
        array.set(idx1, array.get(idx2));
        array.set(idx2, t);
    }

    public void add(T t){
        array.add(t);
        int idx = array.size() - 1;
        int parent_idx = (idx + 1)/2 - 1;
        while(parent_idx >= 0){
            if(comp.compare(array.get(parent_idx), array.get(idx)) < 0){
                swap(idx, parent_idx);
                idx = parent_idx;
                parent_idx = (idx + 1)/2 - 1;
            }
            else{
                break;
            }
        }
    }

    public T pop(){
        if(array.size() == 0)
            return null;
        swap(0, array.size() - 1);
        T result = array.remove(array.size() -1);
        heapify_down(0);
        return result;
    }

    private void heapify_down(int idx){
        if(idx >= array.size())
            return;

        int lc_idx = idx * 2 + 1;
        int rc_idx = idx * 2 + 2;

        if(lc_idx >= array.size())
            return;
        else if(rc_idx >= array.size()){
            if(comp.compare(array.get(idx), array.get(lc_idx))< 0){
               swap(lc_idx, idx); 
            }
        }
        else{
            T p = array.get(idx);
            T lchild = array.get(lc_idx);
            T rchild = array.get(rc_idx);

            if (comp.compare(p, lchild) < 0 || comp.compare(p, rchild) < 0){
                if(comp.compare(lchild,rchild) < 0){
                    swap(idx, rc_idx);
                    heapify_down(rc_idx);
                }
                else{
                    swap(idx, lc_idx);
                    heapify_down(lc_idx);
                }
            }
        }
    }

    public static void main(String [] args){
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0 ; i < 15; i ++){
            list.add(i);
        }
        Heap<Integer> heap = new Heap<Integer>(list, new Comparator<Integer>(){
            @Override 
            public int compare(Integer o1, Integer o2){
                return o1 - o2;
            }
            @Override 
            public boolean equals(Object obj){
                return false; 
            }
        });
        
        heap.add(17);
        heap.add(15);
        heap.add(4);

        Integer i;
        while((i = heap.pop()) != null)
            System.out.println(i);

    }
}
