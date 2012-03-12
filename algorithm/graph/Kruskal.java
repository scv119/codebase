public class Kruskal{
    protected int size;
    protected int[][] edges;
    protected int edge_size;

    public Kruskal(int size){
        this.size = size;
        this.edges = new int[size][size];
        this.edge_size  = 0;
    }

    public Kruskal add(int u,int v,int w){
        if( u >= 0 && v >= u && size >v && w >0) 
        {
            this.edges[u][v] = this.edges[v][u] = w; 
            this.edge_size ++;
        }
        return this;
    }

    public int[][] mst(){
        int[][] result = new int[size-1][2];
        edge[] n_edges   = new edge[this.edge_size]; 
        int r = 0;
        int k = 0;
        for(int u = 0; u < size; u ++ ){
            for(int v = 0; v < u; v ++){
                if(this.edges[u][v] > 0)
                {
                    edge e = new edge();
                    e.u = u;
                    e.v = v;
                    e.weight = this.edges[u][v];
                    n_edges[k++] = e; 
                }
            }
        }

        sort(n_edges, 0, k-1);
        UnionSet us = new UnionSet(this.size);
        for(int i =0 ; i < size;i ++)
            us.makeSet(i);
        for(edge e:n_edges){
           int u_s = us.findSet(e.u);
           int v_s = us.findSet(e.v);

           if(u_s != v_s){
                System.out.println(e.u +" "+ e.v);
                us.unionSet(u_s, v_s);
                result[r][0] = e.u;
                result[r++][1] = e.v;
           }
           if (r >= this.edge_size)
               break; 
        }
        return result;
    }

    public static class edge{
        int u;
        int v;
        int weight;
    }

    public void sort(edge[] n_edges, int start, int end){
        if(start >= end)
            return;
        int i = start+1;
        int j = end;
        while(i < j){
            if(n_edges[i].weight > n_edges[start].weight){
                swap(n_edges, i, j);
                j --;
            }
            else{
                i++;
            }
        }
        int k = 0;
        if(n_edges[i].weight > n_edges[start].weight){
            swap(n_edges, start, i-1);
            k = i - 1;
        }
        else{
            swap(n_edges, start, i);
            k = i;
        }
        sort(n_edges, start,  k - 1);
        sort(n_edges, k + 1,  end);
    }

    private void swap(edge[] n_edges, int i, int j){
        if(i == j)
            return;
        edge tmp = n_edges[j];
        n_edges[j] = n_edges[i];
        n_edges[i] = tmp;
    }

    private static class UnionSet{
        int parent[];
        int rank  [];
        public UnionSet(int n){
            parent = new int[n];
            rank   = new int[n];

        }
        public void makeSet(int n){
           parent[n] = n;
           rank[n]   = 0;
        }

        public int findSet(int n){
            while(parent[n] != n){
                n = findSet(parent[n]);
            }
            return n;
        }

        public void unionSet(int n, int j){
            n = findSet(n);
            j = findSet(j);
            if(rank[n] < rank[j])
                parent[n] = j;
            else if(rank[n] > rank[j])
                parent[j] = n;
            else{
                parent[j] = n;
                rank[n] ++;
            }
        }
    }

    public static void main(String args[]){
        Kruskal k = new Kruskal(9);
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
        int[][] result = k.mst();
    }
}
