import java.util.*;
public class Prim extends Kruskal{
    @Override
    public Prim add(int u, int v, int w){
        super.add(u, v, w);
        return this;
    }
    
    public void mst_prim(){
        int[] distance = new int[size];
        int[] pre      = new int[size];
        Set<Integer> discovered = new HashSet<Integer>(size);
        for(int i = 0; i < size;i ++){
            distance[i] = Integer.MAX_VALUE;
            pre[i] = -1;
        }
        Heap heap = new Heap(size);
        heap.build(distance);
        distance[0] = 0;
        heap.decrease(0,0);
        while(discovered.size() < size){
            int idx = heap.pop();
            System.out.println(idx+" "+pre[idx]);
            discovered.add(idx);
            for(int i = 0 ;i < size; i++){
                if(discovered.contains(i))
                    continue;
                if(edges[idx][i] > 0 && edges[idx][i] < distance[i]){
                    distance[i] = edges[idx][i];
                    heap.decrease(i, distance[i]);
                    pre[i] = idx;
                }
            }
        }
    }

    public Prim(int size){
        super(size);
    }

    public class Heap{
        private int size;
        private int[][]array;
        private int[] map;
        public Heap(int max_size){
            this.size = 0;
            array = new int[max_size][];
            map   = new int[max_size];
        }

        public void build(int []input){
            for(int i =0 ; i < input.length;i ++){
                array[i] = new int[2];
                array[i][0] = i; 
                array[i][1] = input[i];
                map[i] = i;
                size ++;
            }

            for(int i =(input.length+1)/2-1 ;i >= 0 ; i--){
                heapify_down(i);
            }
        }

        public int pop(){
            if(size == 0)
                return -1;
            int id = array[0][0];
            swap(0, --size);
            heapify_down(0);
            map[id] = -1;
            return id;
        }

        public void decrease(int id, int value){
            int idx = map[id];
            array[idx][1] = value;
            heapify_up(idx);
        }

        public void add(int id, int value){
            map[id] = size;
            array[size] = new int[2];
            array[size][0] = id;
            array[size][1] = value;
            heapify_up(size);
            size ++;
        }

        private void heapify_up(int idx){
            while(idx != 0){
                int parent = (idx + 1)/2 - 1;
                if(array[idx][1] < array[parent][1])
                {
                    swap(idx, parent);
                }
                else break;
                idx = parent;
            }
        }

        public void remove(int id){
            int idx = map[id];         
            swap(idx, --size);
            heapify_down(idx);
            map[id] = -1;
        }

        private void heapify_down(int idx){
            int lchild = 2*(idx + 1) - 1;
            int rchild = 2*(idx + 1);

            if(rchild >= size && lchild < size){
                if(array[lchild][1] < array[idx][1]){
                    swap(lchild, idx);
                }
            }
            else if(rchild < size){
                if(array[lchild][1] < array[idx][1] || array[rchild][1] < array[idx][1]){
                    if(array[lchild][1] < array[rchild][1]){
                        swap(lchild, idx);
                        heapify_down(lchild);
                    }
                    else{
                        swap(rchild, idx);
                        heapify_down(rchild);
                    }
                }
            }
        }

        private void swap(int i, int j){
            int tmp_id = array[i][0];
            int tmp_va = array[i][1];
            array[i][0] = array[j][0];
            array[i][1] = array[j][1];
            array[j][0] = tmp_id;
            array[j][1] = tmp_va;
            map[array[i][0]] = i;
            map[array[j][0]] = j;
        }
    }

    public static void main(String args[]){
        Prim k = new Prim(9);
        k.add(0,1,4)
         .add(1,2,8)
         .add(2,3,7)
         .add(3,4,9)
         .add(4,5,10)
         .add(5,6,2)
         .add(6,7,1)
         .add(7,8,7)
         .add(6,8,7)
         .add(0,7,8)
         .add(1,7,11)
         .add(2,8,2)
         .add(2,5,4)
         .add(3,5,14);
        k.mst_prim();
    }
}
