package srm114;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class Masterbrain
{
	public int possibleSecrets(String[] guesses, String[] results)
	{
		int c[] = new int[results.length];
		int p[] = new int[results.length];
		
		for(int i = 0 ; i < results.length; i ++){
			c[i] = results[i].charAt(0) - '0';
			p[i] = results[i].charAt(3) - '0';
		}
		
		int result = 0;
		int arr[]  = new int[4];
		
		for(int i = 1111; i <= 7777; i ++){
			int radix = 1000;
			int v = i;
			boolean bad_value = false;
			for(int j = 0 ; j < 4 ; j ++){
				arr[j] = v / radix;
				v = v % radix;
				radix = radix / 10;
				if(arr[j] == 0 || arr[j] > 7)
				{
					bad_value = true;
					break;
				}
			}
			if(bad_value)
				continue;
			
			
			int succ = 0;
			int fail = 0;
			
			boolean bad_answer = false;
			
			for(int j = 0 ; j < guesses.length; j++){
				String s = guesses[j];
				int cc = 0;
				int pp = 0;
				for( int k = 0 ; k < 4; k ++)
					if(arr[k] == (s.charAt(k) - '0'))
						cc ++;
					else{
						for(int l = 0 ;l < 4; l ++){
							if(arr[k] == (s.charAt(l) - '0')){
								pp ++;
								break;
							}
						}
					}
				
				if(cc == c[j] && pp == p[j])
					succ ++;
				else
					fail ++;
				
				if(fail > 1){
					bad_answer = true;
					break;
				}
			}
			
			if(!bad_answer && fail == 1){
				result ++;
			}
			else{
				System.out.println(i);
			}
			
			
		}
		return result;
	}
	
	private static boolean KawigiEdit_RunTest(int testNum, String[] p0, String[] p1, boolean hasAnswer, int p2) {
		System.out.print("Test " + testNum + ": [" + "{");
		for (int i = 0; p0.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print("\"" + p0[i] + "\"");
		}
		System.out.print("}" + "," + "{");
		for (int i = 0; p1.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print("\"" + p1[i] + "\"");
		}
		System.out.print("}");
		System.out.println("]");
		Masterbrain obj;
		int answer;
		obj = new Masterbrain();
		long startTime = System.currentTimeMillis();
		answer = obj.possibleSecrets(p0, p1);
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
		
		String[] p0;
		String[] p1;
		int p2;
		
		// ------------------
		
		// ----- test 1 -----
		p0 = new String[]{"1234"};
		p1 = new String[]{"0b 4w"};
		p2 = 2392;
		all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
		// ------------------
		
		
	}
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!