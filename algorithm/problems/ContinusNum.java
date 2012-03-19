public class ContinusNum{
    public long getSize(String message){
        long dp[][] = new long[message.length()+2][message.length()+2];
        dp[1][1] = 1;
        for(int i = 0 ; i < message.length(); i ++ ){
            int j = i + 2;
            for(int k = 1; k <= j ; k ++){
                if(message.charAt(i) == 'A'){
                    for(int m = 1; m < k; m ++)
                        dp[j][k] += dp[j-1][m];
                }
                else{
                    for(int m = k; m <= j; m ++)
                        dp[j][k] += dp[j-1][m];
                }
            }
        }
        int r = message.length() + 1;
        long result = 0;
        for(int i = 1; i <= r; i++)
            result += dp[r][i];
        return result;
    }

    public static void main(String args[]){
        ContinusNum c = new ContinusNum();
        System.out.println(c.getSize("ABAAAAABBBBB"));
    }
}
