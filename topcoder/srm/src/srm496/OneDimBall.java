package srm496;

import java.util.*;
public class OneDimBall{
    int [][] edges;
    Set<Integer> visited;
    List<Set<Integer>> scc;
    public int solve(int[] first, int[] second){
        Arrays.sort(first);
        int count = 0;
        Set<Integer> vs = new HashSet<Integer>();
        for(int s:second){
            int v = Math.abs(first[0] - s);
            int[] left = new int[first.length];
            int[] right= new int[first.length];
            Arrays.fill(left, -1);
            Arrays.fill(right, -1);
            boolean failed = false;
            for(int i = 0; i < first.length; i ++){
                for(int j = 0 ; j < second.length; j ++){
                    if(first[i] - v == second[j])
                        left[i] = j;
                    if(first[i] + v == second[j])
                        right[i] = j;
                }
                if(left[i] == right[i] && left[i] == -1)
                {
                    failed = true;
                    break;
                }
            }
            if(failed)
                continue;
            if(vs.contains(v) || v == 0)
                continue;
            vs.add(v);
            System.out.println(v);
            edges = new int[first.length][first.length];
            for(int i = 0 ; i < first.length; i ++)
            for(int j = 0 ; j < first.length; j ++){
                if(left[i] == right[j] && left[i] != -1)
                    edges[i][j] = edges[j][i] = 1;
            }
            dfs();
            int result = 1;
            for(Set<Integer> cc:scc){
                Set<Integer>  sec = new HashSet<Integer>();
                for(int f:cc){
                    if(left[f] >= 0)
                        sec.add(left[f]);
                    if(right[f] >= 0)
                        sec.add(right[f]);
                }

                System.out.println(cc.size() + " " + sec.size());
                if(cc.size() == sec.size()){
                    result *= 1;
                }
                if(cc.size() >  sec.size()){
                    result = 0;
                    break;
                }
                if(cc.size()  < sec.size()){
                    result *= sec.size();
                }
            }
            
            count += result;
        }
        return count;
    }

    void dfs(){
        visited = new HashSet<Integer>();
        scc     = new ArrayList<Set<Integer>>();
        Set<Integer> cc;
        for(int i =0 ; i < edges.length; i ++){
        	cc = new HashSet<Integer>();
            dfs(i, cc);
            if(cc.size() > 0)
                scc.add(cc);
        }
    }
    
    void dfs(int start, Set<Integer> cc){
        if(visited.contains(start))
            return;
        visited.add(start);
        cc.add(start);
        for(int i = 0 ; i < edges.length; i ++){
            if(edges[start][i] > 0)
                dfs(i, cc);
        }
    }

    public static void main(String args[]){
        OneDimBall ball = new OneDimBall();       
        System.out.println(ball.solve(new int[]{6,2,4}, new int[]{1,2,3,4,5,7,8}));
    }
}
