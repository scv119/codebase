import java.util.*;
public class DirectedGraph{
    private int v_size;
    private int e_size;
    private List<List<Node>> adjList;

    private class Node{
        public Node(int id, int w){
            this.id = id;
            this.w = w;
        }
        int id;
        int w;
    }
    public DirectedGraph(int size){
        if(size < 0)
            throw new RuntimeException("size max larger than 0");
        this.v_size = size; 
        adjList = new ArrayList<List<Node>>(size);
        for(int i = 0 ; i < size; i++){
            adjList.add(new ArrayList<Node>()); 
        }
    }

    public DirectedGraph add(int from, int to, int weight){
        if(from < 0 || from >= v_size || to < 0 || to >= v_size || weight <= 0)
            throw new RuntimeException("from,to, weight is not allowed");
        adjList.get(from).add(new Node(to, weight));
        e_size ++;
        return this;
    }

    public int[][] mr_floyd_warshall()
    {
        int [][][] map = new int[v_size][v_size][v_size];
        for(int i = 0 ; i <v_size; i++)
            for(int j  = 0 ; j < v_size; j++)
                for(int k = 0 ; k < v_size; k++)
                    map[i][j][k] = -1;

        int[][] result = new int[v_size][v_size];
       for(int i = 0 ; i < v_size; i ++){
            for(int j = 0 ; j< v_size; j ++){
                result[i][j] = fw(i, j, v_size - 1, map);
            }
       }
       return result;
    }

    public int[][] dp_floyd_warshall()
    {
        int [][][] map = new int[v_size][v_size][v_size];
        for(int i = 0 ; i <v_size; i++)
            for(int j  = 0 ; j < v_size; j++)
                for(int k = 0 ; k < v_size; k++)
                    map[i][j][k] = -1;

        for(int k = 0 ; k < v_size; k++){
            for(int i = 0; i < v_size; i++)
                for(int j = 0 ;j < v_size; j++){
                    if(i == j){
                        map[i][j][k] = 0;
                    }
                    else
                    if(k == 0){
                        map[i][j][k] = w(i, j);
                    }
                    else{
                        int value1 = map[i][j][k-1];
                        int value2_1 = map[i][k][k-1];
                        int value2_2 = map[k][j][k-1];
                        int value2  = 0;
                        if(value2_1 == Integer.MAX_VALUE || value2_2 == Integer.MAX_VALUE){
                            value2 = Integer.MAX_VALUE;
                         }
                        else{
                            value2 = value2_1 + value2_2;
                        }
                        map[i][j][k] = value1<value2?value1:value2;
                    }
                }

        }

        int[][] result = new int[v_size][v_size];
        for(int i = 0 ; i < v_size; i ++){
            for(int j = 0 ; j< v_size; j ++){
                result[i][j] = map[i][j][v_size-1]; 
            }
       }
       return result;
    }

    private int w(int i, int j){
            List<Node> adj = adjList.get(i);
            for(Node node:adj){
                if(node.id == j)
                {
                    return node.w;
                }
            }
            return Integer.MAX_VALUE;
    }

    private int fw(int from, int to, int k, int[][][]map){
        if(map[from][to][k] != -1)
            return map[from][to][k];
        
        if(from == to)
            return 0;
        if(k == 0){
            List<Node> adj = adjList.get(from);
            for(Node node:adj){
                if(node.id == to)
                {
                    map[from][to][k] = node.w;
                    return node.w;
                }
            }
            map[from][to][k] = Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        int value1 = fw(from, to, k-1, map);
        int value2_1 = fw(from,  k, k-1, map);
        int value2_2 = fw(k,    to, k-1, map);
        int value2  = 0;
        if(value2_1 == Integer.MAX_VALUE || value2_2 == Integer.MAX_VALUE){
            value2 = Integer.MAX_VALUE;
        }
        else{
            value2 = value2_1 + value2_2;
        }
        map[from][to][k] = value2<value1?value2:value1;
        return map[from][to][k];
    }

    public int[] bellman_ford(int s){
        int[] distance = new int[v_size];
        int[] prev     = new int[v_size];

        for(int i = 0 ; i < v_size;i ++){
            distance[i] = Integer.MAX_VALUE;
            prev[i]     = -1;
        }

        distance[s] = 0;

        for(int i = 1 ; i < v_size; i++){
            for(int j = 0 ; j < v_size;j ++){
                for(Node adj:adjList.get(j)){
                    if(distance[j] == Integer.MAX_VALUE)
                        continue;
                    if(distance[adj.id] > (distance[j] + adj.w)){
                        distance[adj.id] = distance[j] + adj.w;
                    }
                }
            }
        }

        return distance;
    }
    public int[] dijkstra(int s){
        if( s < 0 || s >= v_size)
            throw new RuntimeException("bad input");
           
        Heap heap = new Heap(v_size);
        int [] prev     = new int[v_size];
        Set<Integer> discovered = new HashSet<Integer>();
        Node[] distance = new Node[v_size];
        for(int i = 0 ;i < v_size; i ++){
            distance[i] = new Node(i, Integer.MAX_VALUE);
            prev[i]     = -1;
        }
        distance[s].w = 0;
        heap.buildHeap(distance);

        while(discovered.size() < v_size){
           Node node = heap.pop_min();          
           discovered.add(node.id);
           int idx = node.id;
           List<Node> adj = adjList.get(idx);
           for(Node next:adj){
                if(discovered.contains(next.id)){
                    continue; 
                }
                if(distance[node.id].w == Integer.MAX_VALUE)
                    continue;
                if(distance[next.id].w > (distance[node.id].w + next.w)){
                    distance[next.id].w = distance[node.id].w + next.w;
                    prev[next.id] = node.id;
                    heap.decrease(next);
                }
           }
        }

        int [] result = new int[v_size];
        for(int i = 0 ; i< v_size; i ++){
            result[i] = distance[i].w;
        }
        return result;
    }
    
    private class Heap{
        Node[] array;
        Map<Node, Integer> indexMap;
        int cur_size;
        private Heap(int size){
            array = new Node[size];
            cur_size = 0;
            indexMap = new HashMap<Node, Integer>(size);
        }

        private void buildHeap(Node[] array){
            for(Node node:array){
                this.array[cur_size++] = node;
                indexMap.put(node, cur_size - 1);
            }
            for(int i = (cur_size/2 - 1); i >= 0 ;i --){
                heapify_down(array[i]);
            }
        }

        private void decrease(Node node){
            if(!indexMap.containsKey(node))
                return;
            int idx = indexMap.get(node); 
            while(idx != 0){
                int parent = (idx + 1)/2 - 1;
                if(array[parent].w > array[idx].w){
                    swap(parent, idx);
                    idx = parent;
                }
                else{
                    break;
                }
            }
        }
        private Node pop_min(){
            if(cur_size == 0)
                return null;
            Node result = array[0];    
            swap(0, --cur_size);
            indexMap.remove(result);
            if(cur_size > 0)
                heapify_down(array[cur_size-1]);  
            return result;
        }

        private void heapify_down(Node node){
            if(!indexMap.containsKey(node))
                return;
            int idx = indexMap.get(node);
            int lchild = idx*2 + 1;
            int rchild = idx*2 + 2;
            if(rchild < cur_size){
                if(array[rchild].w < array[idx].w || array[lchild].w < array[idx].w){
                    if(array[rchild].w < array[lchild].w){
                        swap(rchild, idx);
                        heapify_down(array[rchild]);
                    }
                    else{
                        swap(lchild, idx);
                        heapify_down(array[lchild]);
                    }
                }
            }
            else if(lchild < cur_size){
                if(array[lchild].w < array[lchild].w){
                    swap(lchild, idx);
                }
                
            }
        }

        private void swap(int i, int j){
            Node tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            indexMap.put(array[i],  i);
            indexMap.put(array[j],  j);
        }
    }
    

    public static void main(String args[]){
        DirectedGraph g = new DirectedGraph(5);  
        g.add(0, 1, 10)
         .add(1, 2, 1)
         .add(0, 3, 5)
         .add(1, 3, 2)
         .add(3, 1, 3)
         .add(3, 2, 9)
         .add(3, 4, 2)
         .add(4, 0, 7)
         .add(2, 4, 4)
         .add(4, 2, 6);

        int[] result = g.dijkstra(0);
        g.print(result);
        result       = g.bellman_ford(0);
        g.print(result);
        int[][] result1 = g.mr_floyd_warshall();
        g.print1(result1);
        result1 = g.dp_floyd_warshall();
        g.print1(result1);
    }

    private void print(int[] arr){
        for(int i = 0 ; i< arr.length; i++)
        {
            System.out.println(i + " "+ arr[i]);
        }
    }
    private void print1(int[][] arr){
        for(int i = 0 ; i< arr.length; i++)
        {
            for(int j = 0 ; j < arr.length; j ++)
            System.out.println(i + " " + j + " "+ arr[i][j]);
        }
    }
}
