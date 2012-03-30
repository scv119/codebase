package srm493;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class AmoebaCode
{
	public int find(String code, int K)
	{
		for(int i = K; i >= 2; i --){
			boolean find = true;
			Set<String> now = new HashSet<String>(), next = new HashSet<String>();
			for(int m = 0 ; m < code.length() ; m++){
				char c = code.charAt(m);
				if(m == 0 ){
					if( c == '0')
					for(int j = 1 ; j <= K ; j++){
						next.add(new String((char)('0' + j) + ""));
					}
					else
						next.add(new String(c + ""));
				}
				else{
					for(String s: now){
						if(s.length() >= i)
							s  = s.substring(1);
					//	System.out.println(s);

						if( c == '0'){
							for(int j = 1 ; j <= K ; j ++){
								char n  = (char)(j + '0');
								boolean contains = false;
								for(int k = 0 ; k < s.length() ; k++)
									if(s.charAt(k) == n){
										contains = true;
										break;
									}
								if(contains)
									continue;
								String news = s + n;
								next.add(news);
							}
						}
						else{
								boolean contains = false;
								for(int k = 0 ; k < s.length() ; k++)
									if(s.charAt(k) == c){
										contains = true;
										break;
									}
								if(contains)
									continue;
							String news = s + c;
							next.add(news);
						}
					}
				}
				if(next.size() == 0){
					find = false;
					break;
				}else{
					now = next;
					next = new HashSet<String>();
				}
				
/*				for(String ss:now){
					System.out.print(ss+" ");
				}
				System.out.println(m); */
			}
			if(find)
				return i;
		}
		return 1;	
	}
	
	private static boolean KawigiEdit_RunTest(int testNum, String p0, int p1, boolean hasAnswer, int p2) {
		System.out.print("Test " + testNum + ": [" + "\"" + p0 + "\"" + "," + p1);
		System.out.println("]");
		AmoebaCode obj;
		int answer;
		obj = new AmoebaCode();
		long startTime = System.currentTimeMillis();
		answer = obj.find(p0, p1);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + p2);
		}
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		if (hasAnswer) {
			res = answer == p2;
		}
		if (!res) {
			System.out.println("DOESN'T MATCH!!!!");
		} else if ((endTime - startTime) / 1000.0 >= 2) {
			System.out.println("FAIL the timeout");
			res = false;
		} else if (hasAnswer) {
			System.out.println("Match :-)");
		} else {
			System.out.println("OK, but is it right?");
		}
		System.out.println("");
		return res;
	}
	public static void main(String[] args) {
		boolean all_right;
		all_right = true;
		
		String p0;
		int p1;
		int p2;
		
		// ----- test 0 -----
//		p0 = "01";
//		p1 = 1;
//		p2 = 1;
//		all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = "1001";
		p1 = 2;
		p2 = 1;
		all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = "1010";
		p1 = 2;
		p2 = 2;
		all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = "01001";
		p1 = 3;
		p2 = 3;
		all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = "10012031001";
		p1 = 3;
		p2 = 2;
		all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
		// ------------------
		
		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
}