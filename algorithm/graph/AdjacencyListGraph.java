import java.util.*;
public class AdjacencyListGraph{
    private int size;
    private List<List<Integer>> adjacencyList;

    public AdjacencyListGraph(int size){
        this.size = size;
        adjacencyList = new ArrayList<List<Integer>>(size);
        for(int i = 0 ; i < size; i ++){
            adjacencyList.add(new ArrayList<Integer>());
        }
    }
    
    public AdjacencyListGraph add(int n, int k){
        if( n >= 0 && n < size && k >= 0 && k < size)
        {
            adjacencyList.get(n).add(k);
        }
        return this;
    }

    public void BFS(int s){
        if (s < 0 || s >= size)
            return;
       Set<Integer> inQueue = new HashSet<Integer>(size); 
       Set<Integer> visited = new HashSet<Integer>(size);
       Queue<Integer> queue = new LinkedList<Integer>();
       int depth[] = new int[size];   

       depth[s] = 0;
       inQueue.add(s);
       queue.add(s);

       Integer next;
       while((next = queue.poll()) != null){
            visited.add(next);
            System.out.println(next + "visited");
            for(Integer t:adjacencyList.get(next)){
                if(inQueue.contains(t))
                    continue;
                depth[t] = depth[next] + 1;
                inQueue.add(t);
                queue.add(t);
            }
       }
    }

    public void DFS(){
        int parent[] = new int[size];
        int d[]      = new int[size];
        int f[]      = new int[size];

        Set<Integer> discovered= new HashSet<Integer>(size);
        Set<Integer> finished  = new HashSet<Integer>(size);
        
        int time = 0;
        for( int i = 0 ; i < size; i ++ )
        {
            if(discovered.contains(i))
                continue;
            time = DFS_visit(time, i, parent, d, f, discovered, finished);
        }

        for(int i  = 0 ; i < size; i ++)
            System.out.println(parent[i]+" "+d[i]+" "+f[i]);
    }

    private int DFS_visit(int time, int s, int parent[] , int d[], int f[], Set<Integer>discovered, Set<Integer>finished){
        discovered.add(s);
        d[s] = time ++;
        for(Integer i:adjacencyList.get(s)){
            if(discovered.contains(i))
                continue;
            parent[i] = s;
            time = DFS_visit(time, i, parent, d, f, discovered, finished);
        }
        f[s] = time++;
        finished.add(s);
        return time;
    }

    public static void main(String args[]){
        AdjacencyListGraph graph = new AdjacencyListGraph(6);  
           graph.add(0,1)
                .add(1,2)
                .add(0,3)
                .add(1,3)
                .add(1,4)
                .add(2,4)
                .add(3,4)
                .add(3,5)
                .add(4,5);
        graph.topologicalSort();
    }

    public void topologicalSort(){
        List<Integer> result = new ArrayList<Integer>();        
        Set<Integer> discovered = new HashSet<Integer>();
        int[]        parent  = new int[size];
        
        for(int i = 0 ;i < size; i ++){
            if(discovered.contains(i))
                continue;
            
            parent[i] = -1;
            ts_visit(i, parent, discovered, result);
        }

        for(Integer i:result)
            System.out.println(i);
    }

    private void ts_visit(int i , int[] parent, Set<Integer>discovered, List<Integer> result){
        discovered.add(i);        
        for(Integer j:adjacencyList.get(i)){
            if(discovered.contains(j)){
                int p = i;
                while(p != j){
                    if(parent[p] != -1)
                        p = parent[p];
                    else
                        break;
                }
                if(p == j)
                    System.out.println("LOOP discovered");
                continue;
            }
            parent[j] = i;
            ts_visit(j, parent, discovered, result);
        }
        result.add(i);
    }
}

