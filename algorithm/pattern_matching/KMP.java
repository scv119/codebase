public class KMP{
    public static int match(String pattern, String source){
        if(pattern == null || pattern.length() == 0 || source == null || source.length() == 0){
            return -1;
        }
        int next[] = new int[pattern.length()];
        get_next(pattern, next);

        int p_i = 0;
        int s_i = 0;
        while(true){
            if(p_i >= pattern.length())
                return s_i - p_i;

            if(s_i >= source.length())
                return -1;
            
            if(pattern.charAt(p_i) == source.charAt(s_i)){
                p_i ++;
                s_i ++;
                continue;
            }

            if(p_i != 0){
                p_i = next[p_i - 1];
            }
            else{
                s_i ++;
            }
        }
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

        System.out.println(match(s,"aaaabcabdabcabdabcabc"));
    }
}
