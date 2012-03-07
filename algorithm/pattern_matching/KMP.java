public class KMP{
    public static int match(String pattern, String source){
        return 0;    
    }

    private static void get_next(String pattern, int[] next){
        if(pattern == null || pattern.length() == 0)
            return;
        next[0] = 0;
        for(int i =1 ;i < pattern.length(); i++){
            int n = i - 1;
            while(pattern.charAt(i) != pattern.charAt(next[n])){
                if(next[n] == 0)
                    break;
                n = next[n]-1;
            }
            if(pattern.charAt(i) == pattern.charAt(next[n])){
                next[i] = next[n] + 1;
            }
            else{
                next[i] = 0;
            }
        }
    }

    public static void main(String args[]){
        String s = "abcabdabcabc";
        int next[] = new int[s.length()];
        get_next(s, next);
        for(int i = 0 ;i < s.length(); i++){
            System.out.print(s.charAt(i)+" ");
        }
        System.out.println();
        for(int i = 0 ;i < s.length(); i++){
            System.out.print(next[i]+" ");
        }
        System.out.println();
    }
}
