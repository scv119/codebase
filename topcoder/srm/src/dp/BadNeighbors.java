package dp;

public class BadNeighbors {
	public int maxDonations(int[] d){
		int len = d.length;
		int dp[][] = new int[len][2];
		dp[1][0] = 0;
		dp[1][1] = d[1];
		
		for(int i = 2 ; i < len; i ++){
			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
			dp[i][1] = dp[i-1][0] + d[i];
		}
		
		int max = Math.max(dp[len-1][0], dp[len-1][1]);
		if(len == 2)
			return max;
		
		dp = new int[len][2];
		
		
		
		dp[2][0] = d[0];
		dp[2][1] = d[0] + d[2];
		for(int i = 3 ; i < len-1; i ++){
			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
			dp[i][1] = dp[i-1][0] + d[i];
		}
		
		int max1 = Math.max(dp[len-2][0],dp[len-2][1]);
		
		return Math.max(max1, max);
	}
	
	public static void main(String args[]){
		BadNeighbors bn = new BadNeighbors();
		System.out.println(bn.maxDonations(new int[]{ 10, 3, 2, 5, 7, 8 }));
		System.out.println(bn.maxDonations(new int[]{ 7,7,7,7,7,7,7 }));
		System.out.println(bn.maxDonations(new int[]{ 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 }));
		System.out.println(bn.maxDonations(new int[]{ 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
				  6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
				  52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 }));
	}
}
