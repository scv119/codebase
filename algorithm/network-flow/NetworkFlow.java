public class NetworkFlow{
    int [][] res;
    int [][] cur;
    Set<Integer> visited;
    int []pre;
    int n;
    int s;
    int e;
    public int solve(int [][] graph, int s, int e){
        n = graph.length;
        this.s = s;
        this.e = e;
        res = new int[n][n]; 
        cur = new int[n][n];
        for(int i = 0 ; i < n; i ++)
        for(int j = 0 ; j < n; j ++){
            res[i][j] = graph[i][j];
        }
        int result = 0 ;
        while(find_res()){
            int min = Integer.MAX_VALUE; 
            for(int i = 0;i < n; i++){
                int j = pre[i];
                int value = res[i][j];
                if(value < min)
                    min = value;
            }
            result += min;
            for(int i = 0 ; i < n ; i++)
            for(int j = 0 ; j < n ; j++){
                cur
            }
        }
    }

    boolean find_res(){
        visited = new HashSet<Integer>();
        pre = new int[n];
        return dfs(s);
    }

    boolean dfs(int i){
        if(visited.contains(i))
            return false;
        visited.add(i);
        boolean result = false;
        for(int j = 0 ; j < n ; j ++){
            if(res[i][j] > 0){
                pre[j] = i;
                if(j == e)
                    return true;
                result = dfs(j); 
                if(result)
                    return result;
            }
        }
        return result;
    }
}
