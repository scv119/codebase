public class KMP{
    public static int match(String pattern, String source){
        
    }

    private static void get_next(String pattern, int[] next){
        if(pattern == null || pattern.length() == 0)
            return;
        next[0] = 0;
        for(int i =1 ;i < pattern.length(); i++){
            int n = i - 1;
            if(pattern[i] == pattern[next[n]+1]){
                next[i] = next[n] + 1;
            }
            else{
                
            }
        }
    }
}
