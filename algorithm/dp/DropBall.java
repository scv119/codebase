import java.util.*;
public class DropBall{
    private static Map<String, Integer> map = new HashMap<String,Integer>();
    public static int drop_ball(int n, int k){
        if (k == 1){
            return n;
        }

        if ( n == 0 ){
            return 0;
        }

        if ( n == 1){
            return 1;
        }

        String key = n + " "+ k;
        if(map.containsKey(key))
            return map.get(key);
        int min = Integer.MAX_VALUE;
        for(int i = 1 ; i <= n ; i ++){
            int value = Math.max(drop_ball(i - 1, k -1), drop_ball(n - i, k)) + 1; 
            if (value < min){
                min = value;
            }
        }

        map.put(key,min);
        return min;
    }

    public static int drop_ball_dp(int n , int k){
        int [][] array = new int[n+1][k+1];
        for(int j = 0 ; j <= k ; j++){
            for (int i = 0 ; i <= n; i ++){
                if ( i == 1){
                    array[i][j] = j;
                }

                else if ( j == 0){
                    array[i][j] = 0;
                }

                else if (j == 1){
                    array[i][j] = 1;
                }

                else{
                    
                    int min = Integer.MAX_VALUE;
                    for( int l = 1 ; l <= i ; l++){
                        int value = Math.max(array[l -1][ j -1] , array[i - l][ j]) + 1;
                        if(value < min){
                            min = value;
                        }
                    }
                    array[i][j] = min;
                }

            }
        }

        return array[n][k];

    }

    public static void main(String args[]){
        System.out.println(drop_ball(300,3));
        System.out.println(drop_ball_dp(300,3));
        System.out.println(map.size());
    }
}
