import java.util.*;
public class LCS{
    public static final int EQUAL = 0;
    public static final int FIRST = -1;
    public static final int SECOND = 1;

    public static String recursive(String s1, String s2){
        if(s1 == null || s2 == null)
            return null;
        Map<String,Integer> map = new HashMap<String,Integer>();
        int[][] matrix = new int[s1.length()][s2.length()];
        int len = rec(s1, s2, 0, 0, map, matrix);
        StringBuffer sb = new StringBuffer();
        int x = 0, y = 0;
        while(sb.length() < len){
           int direct = matrix[x][y]; 
           switch(direct){
                case EQUAL:
                    sb.append(s1.charAt(x));
                    x++;
                    y++;
                    break;
                case FIRST:
                    x++;
                    break;
                case SECOND:
                    y++;
                    break;
           }
        }
        System.out.println(map.size());
        return sb.toString();
    }

    public static String dp(String s1, String s2){
        if(s1 == null || s2 == null)
            return null;

        int[][] path= new int[s1.length()][s2.length()];
        int[][] sub = new int[s1.length()+1][s2.length()+1];

        for ( int i = 0 ; i <=  s1.length(); i ++ ){
            sub[i][s2.length()] = 0;
        }
        for ( int i = 0 ; i <=  s2.length(); i ++ ){
            sub[s1.length()][i] = 0;
        }

        for ( int i = s1.length() -1 ; i >= 0 ;i --){
            for ( int j = s2.length() - 1 ; j >= 0 ;j --){
                if(s1.charAt(i) == s2.charAt(j)){
                    path[i][j] = EQUAL;
                    sub[i][j] = 1 + sub[i+1][j+1];
                }
                else{
                    int l = sub[i+1][j];
                    int r = sub[i][j+1];
                    sub[i][j] = Math.max(l, r);
                    if(sub[i][j] == l){
                        path[i][j] = FIRST;
                    }
                    else
                    {
                        path[i][j] = SECOND;
                    }
                }
            }
        }

        int len= sub[0][0];
        StringBuffer sb = new StringBuffer();
        int x = 0, y = 0;
        while(sb.length() < len){
           int direct = path[x][y]; 
           switch(direct){
                case EQUAL:
                    sb.append(s1.charAt(x));
                    x++;
                    y++;
                    break;
                case FIRST:
                    x++;
                    break;
                case SECOND:
                    y++;
                    break;
           }
        }
        return sb.toString();
    }

    
    private static int rec(String s1, String s2, int s1_idx, int s2_idx, Map<String,Integer>map, int [][]matrix){
        if(s1_idx >= s1.length() || s2_idx >= s2.length())
            return 0;
        String key = s1_idx + "" + s2_idx;
        if(map.containsKey(key))
            return map.get(key);
        
        int result = 0;
        if(s1.charAt(s1_idx) == s2.charAt(s2_idx)){
            matrix[s1_idx][s2_idx] = EQUAL;
            result = 1 + rec(s1, s2, s1_idx +1, s2_idx + 1, map, matrix);
        }
        else {
            int lcs1 = rec(s1, s2, s1_idx +1, s2_idx, map, matrix);
            int lcs2 = rec(s1, s2, s1_idx, s2_idx +1, map, matrix);
            result = Math.max(lcs1, lcs2);
            if(result == lcs1){
                matrix[s1_idx][s2_idx] = FIRST;
            }
            else{
                matrix[s1_idx][s2_idx] = SECOND;
            }
        }

        map.put(key,result);
        return result;
    }

    public static void main(String args[]){
        System.out.println(dp("ABCCCDEFSD","iFBCCFDFES"));
    }
}
