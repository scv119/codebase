import java.util.*;
public class DropBall{
    public static int drop_ball(int n, int k, int[][] map){
        if (k == 1){
            return n;
        }

        if ( n == 0 ){
            return 0;
        }

        if ( n == 1){
            return 1;
        }

        if( map == null){
            map = new int[n+1][k+1];
            for(int i = 0; i < n + 1 ; i ++){
                for(int j = 0 ; j < k + 1 ;j ++){
                    map[i][j] = -1;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1 ; i <= n ; i ++){
            int first = map[i-1][k-1];
            if(first == -1){
                first = drop_ball(i - 1, k - 1, map);
            }
            int second = map[n-i][k];
            if(second == -1){
               second = drop_ball(n-i, k, map);
            }
            int value = Math.max(first, second) + 1; 
            if (value < min){
                min = value;
            }
        }

        map[n][k] = min;
        return min;
    }

    public static int drop_ball_dp(int n , int k){
        int [][] array = new int[n+1][k+1];
        for(int i = 1 ; i <= n ; i ++){
            array[i][1] = i;
        }
        for (int j = 2; j<=k ;j ++){
            for(int i = 1; i<= n ; i ++){
                int min = Integer.MAX_VALUE;
                for ( int r = 1; r <= i; r++){
                    int value = Math.max(array[r-1][j-1], array[i-r][j]) + 1;
                    if(value < min)
                        min = value;
                }
                array[i][j] = min;
            }
        }
        return array[n][k];
        

    }

    public static void main(String args[]){
        System.out.println(drop_ball(300,3, null));
        System.out.println(drop_ball_dp(300,3));
        System.out.println(drop_ball(10000,3, null));
        System.out.println(drop_ball_dp(10000,3));
    }
}
