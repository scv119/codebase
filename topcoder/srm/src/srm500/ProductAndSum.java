package srm500;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class ProductAndSum
{
	private int modulo = 500500573;
	private BigInteger bModulo = BigInteger.valueOf(modulo);
	
	public int getSum(int p2, int p3, int p5, int p7, int S)
	{
		S = S - 5 * p5 - 7 * p7;
		if(S < 0)
			return 0;
		int p1,p4,p6,p8,p9;
		int input[] = new int[10];
		int result = 0;
		for(p9 = 0; p9 <= (p3/2); p9 ++)
		for(p8 = 0; p8 <= (p2/3); p8 ++)
		for(p4 = 0; p4 <= ((p2 - p8 *3 )/2); p4 ++)
		for(p6 = 0; p6 <= (p3 - p9*2) && p6 <= (p2 - p8 * 3 - p4 *2); p6++){
			int _p2 = p2 - 3 * p8 - 2 * p4 - p6;
			int _p3 = p3 - 2 * p9 - p6;
			p1 = S - p9 * 9 - p8 * 8 - p4 * 4 - p6 * 6 - _p3 * 3 - _p2 * 2;
			if(p1 < 0)
				continue;
			input[0] = 0;
			input[1] = p1; input[2] = _p2; input[3] = _p3; input[4] = p4; input[5] = p5; input[6] = p6; input[7] = p7;
			input[8] = p8; input[9] = p9;
			int sum = 0;
			for(Integer v : input)
				sum += v;
			result += left(input, sum);
			result = result % modulo;
			
				
		}
		return result;
	}
	
	private int left(int p[], int sum){
		BigInteger result = BigInteger.valueOf(0);
		for(int i = 1 ; i <= 9 ; i++){
			if(p[i] > 0)
			{
				p[i] --;
				result = result.add(c(p, sum - 1).multiply(BigInteger.valueOf(i)));
				p[i] ++;
			}
		}
		BigInteger mul = BigInteger.valueOf(1);
		for(int i = 1 ; i < sum ; i++){
			mul = mul.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(1));
		}
		result = result.multiply(mul);
		result = result.mod(bModulo);
		return Integer.parseInt(result.toString());
	}
	
	private BigInteger c(int p[], int sum){
		if(sum == 0)
			return BigInteger.valueOf(1);
		BigInteger result = BigInteger.valueOf(1);
		for(int i = 1; i <= 9 ; i ++){
			if(p[i] == 0)
				continue;
			int k = p[i];
			for(int j = 0; j < k; j ++){
				result = result.multiply(BigInteger.valueOf(sum - j));
			}
			for(int j = k ; j>= 1 ; j--){
				result = result.divide(BigInteger.valueOf(j));
			}
			sum = sum - k;
		}
		return result;
	}
	
	public static void main(String args[]){
		ProductAndSum a = new ProductAndSum();
		System.out.println(a.getSum(2, 0,0,0, 5));
	}
}