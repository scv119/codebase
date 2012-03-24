package srm500;

import java.math.*;


public class ProductAndSum
{

	int modulo = 500500573;
	BigInteger bModulo = BigInteger.valueOf(modulo);
	BigInteger ONE  = BigInteger.valueOf(1);
	BigInteger ZERO = BigInteger.valueOf(0);
	BigInteger TEN  = BigInteger.valueOf(10);

	public int getSum(int p2, int p3, int p5, int p7, int S)
	{
		int p[] = new int[10];
		p[7] = p7;
		p[5] = p5;
		S = S - p[5] * 5 - p[7] * 7;
		int result = 0;
		for(p[8] = 0 ; p[8] <= (p2/3) ; p[8] ++)
		for(p[4] = 0 ; p[4] <= ((p2 - p[8] * 3)/2) ; p[4] ++)
		for(p[9] = 0 ; p[9] <= (p3/2) ; p[9] ++)
		for(p[6] = 0 ; p[6] <= (p2 - p[8] * 3 - p[4] * 2) && p[6] <= (p3 - p[9] *2 ); p[6] ++){
			p[3] = p3 - 2 * p[9] - p[6];
			p[2] = p2 - 2 * p[4] - 3 * p[8];
			p[1] = S - 2 * p[2] - 3 * p[3] - 4 * p[4] - 6 * p[6] - 8 * p[8] - 9 * p[9];
			if(p[1] < 0 )
				continue;
			
			result = (result + get_mod(p))%modulo; 
		}
		return result;
	}
	
	
	private int get_mod(int p[]){
		int sum = 0;
		for(int i:p)
			sum += i;
		BigInteger result = ZERO;
		for(int i = 1 ; i < 10 ; i++){
			if(p[i] > 0){
				p[i] -- ;
				result = result.add(c(p, sum -1).multiply(BigInteger.valueOf(i)));
				p[i] ++ ;
			}
		}
		
		BigInteger mul = ONE;
		for(int i = 1 ; i < sum ; i ++){
			mul = mul.multiply(TEN).add(ONE);
		}
		result = result.multiply(mul).mod(bModulo);
		return Integer.parseInt(result.toString());
	}
	
	BigInteger c(int p[] , int sum){
		BigInteger result = ONE;
		for(int i = 1 ; i < 10 ; i ++){
			if(p[i] == 0)2
				continue;
			for(int j = 0 ; j < p[i]; j ++)
				result = result.multiply(BigInteger.valueOf(sum - j));
			for(int j = p[i] ; j > 0 ; j --)
				result = result.divide(BigInteger.valueOf(j));
			sum = sum - p[i];
		}
		return result;
	}
	
	public static void main(String args[]){
		ProductAndSum a = new ProductAndSum();
		System.out.println(a.getSum(0, 0, 0, 0, 10));
	}
}