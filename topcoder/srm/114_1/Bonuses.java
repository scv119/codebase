import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class Bonuses
{
	
	static class A implements Comparable{
		A(int idx, int value){
			this.idx = idx;
			this.value = value;
		}
		int idx;
		int value;
		public int compareTo(Object o){
			A a = (A)o;
			int cmp = value - a.value;
			return cmp != 0? cmp:(a.idx - idx);
		}
	}
	public int[] getDivision(int[] points)
	{
		int sum = 0;
		int result[] = new int[points.length];
		int len = points.length;
		for(int p:points)
			sum += p;
		for(int i = 0 ; i< len; i ++)
		{	
			result[i] = points[i] * 100/sum;
		}
		sum = 100;
		for(int i:result)
			sum -=i;
		A[] list = new A[len];
		for(int i = 0 ; i < len; i++)
			list[i] = new A(i, result[i]);
		Arrays.sort(list);
		for(int i = 0 ; i < sum ; i++){
			result[list[(len-1-i)].idx] ++;
		}
		return result;
		
	}
	
	private static boolean KawigiEdit_RunTest(int testNum, int[] p0, boolean hasAnswer, int[] p1) {
		System.out.print("Test " + testNum + ": [" + "{");
		for (int i = 0; p0.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p0[i]);
		}
		System.out.print("}");
		System.out.println("]");
		Bonuses obj;
		int[] answer;
		obj = new Bonuses();
		long startTime = System.currentTimeMillis();
		answer = obj.getDivision(p0);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.print("\t" + "{");
			for (int i = 0; p1.length > i; ++i) {
				if (i > 0) {
					System.out.print(",");
				}
				System.out.print(p1[i]);
			}
			System.out.println("}");
		}
		System.out.println("Your answer:");
		System.out.print("\t" + "{");
		for (int i = 0; answer.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(answer[i]);
		}
		System.out.println("}");
		if (hasAnswer) {
			if (answer.length != p1.length) {
				res = false;
			} else {
				for (int i = 0; answer.length > i; ++i) {
					if (answer[i] != p1[i]) {
						res = false;
					}
				}
			}
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
		
		int[] p0;
		int[] p1;
		
		// ----- test 0 -----
		p0 = new int[]{1,2,3,4,5};
		p1 = new int[]{6,13,20,27,34};
		all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = new int[]{5,5,5,5,5,5};
		p1 = new int[]{17,17,17,17,16,16};
		all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = new int[]{485,324,263,143,470,292,304,188,100,254,296,255,360,231,311,275,93,463,115,366,197,470};
		p1 = new int[]{8,6,4,2,8,5,5,3,1,4,5,4,6,3,5,4,1,8,1,6,3,8};
		all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
		// ------------------
		
		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
	

}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!