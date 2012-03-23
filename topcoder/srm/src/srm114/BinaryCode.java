package srm114;

public class BinaryCode{
	private static final String NONE = "NONE";
	public static String[] decode(String message){
		String[] result = new String[] {NONE, NONE};
		int len = message.length();
		char[] r0 = new char[len];
		char[] r1 = new char[len];
		r0[0] = '0';
		r1[0] = '1';
		boolean f0 = false;
		boolean f1 = false;
		for(int i = 0 ; i < len -1; i++){
			int value = message.charAt(i) - '0';
			int v0    = value - (r0[i] - '0');
			int v1    = value - (r1[i] - '0');
			
			if( i > 0){
				v0 = v0 - (r0[i-1]-'0');
				v1 = v1 - (r1[i-1]-'0');
			}
			
			if(v0 >= 0)
				r0[i+1] = (char)(v0 + '0');
			else
				f0      = true;
			
			if(v1 >= 0)
				r1[i+1] = (char)(v1 + '0');
			else
				f1      = true;
		}
		
		if(len > 1){
			if((message.charAt(len-1) - '0') != (r0[len-1]-'0' + r0[len-2]-'0'))
				f0 = true;
			if((message.charAt(len-1) - '0') != (r1[len-1]- '0' + r1[len-2] - '0'))
				f1 = true;
		}
		if(!f0)
			result[0] = new String(r0);
		if(!f1)
			result[1] = new String(r1);
		return result;
	}
	
	public static void main(String args[])
	{
		System.out.println(decode("0")[0] + " "+ decode("1")[1]);
	}
}