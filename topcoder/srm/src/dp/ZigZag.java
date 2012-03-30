package dp;

public class ZigZag {
	public int longestZigZag(int[] s){
		int len = s.length;
		int dp[][] = new int[len][2];
		dp[0][0] = 1;
		dp[0][1]  = 1;
		for(int i = 1 ; i < len; i ++){
			int max_up = 1;
			int max_down = 1;
			for(int j = 0 ; j < i; j ++){
				if(s[i] == s[j])
					continue;
				if(s[i] > s[j] && dp[j][0] + 1> max_up)
					max_up = dp[j][0] + 1;
				if(s[i] < s[j] && dp[j][1] + 1 > max_down)
					max_down = dp[j][1] + 1;
			}
			dp[i][1] = max_up;
			dp[i][0] = max_down;
		}
		return Math.max(dp[len-1][1], dp[len-1][0]);
	}
	
	public static void main(String args[]){
		ZigZag zz = new ZigZag();
		System.out.println(zz.longestZigZag(new int[]{ 1, 7, 4, 9, 2, 5 }));
		System.out.println(zz.longestZigZag(new int[]{ 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
				600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
				67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
				477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
				249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }));
		System.out.println(zz.longestZigZag(new int[]{ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }));
		System.out.println(zz.longestZigZag(new int[]{ 44 }));
	}
}
