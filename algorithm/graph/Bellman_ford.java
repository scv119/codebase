public class Bellman_ford extends Kruskal{ 
    public Bellman_ford(int size){
        super(size);
    }
    public int[] bf(int s){
       int []distance = new int[size];
       int []pre      = new int[size];
       for(int i = 0 ; i < size; i ++){
           distance[i] = Integer.MAX_VALUE; 
           pre[i]      = -1;
       }

       distance[s] = 0;
        
        for(int i  = 0 ; i< size -1; i ++){
            for(int j = 0; j <size; j++)
                for(int k =0; k <j; k ++){
                    if(edges[j][k] <= 0)
                        continue;
                    relax(distance,pre,j,k);
                    relax(distance,pre,k,j);
                }
        }

        for(int i = 0 ; i < size; i++){
            System.out.println(i + " "+ distance[i]);
        }

        return null;
    }

    private void relax(int[] distance, int pre[],int u,int v){
        if(distance[u] > (distance[v] + edges[v][u])){
            distance[u] = (distance[v] + edges[v][u]);
            pre[u]      = v;
        }
    }

    public static void main(String args[]){
        Bellman_ford k = new Bellman_ford(9);
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
         k.bf(0);
    }
}
