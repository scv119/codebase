import java.util.*;

public class BigInteger{
    private boolean positive;
    private List<Integer> values;
    private static final int DEC_LEN = 9;
    private static final long DIV    = 1000000000;

    public BigInteger(String s){
        s = s.trim();
        values = new ArrayList<Integer>(s.length()/DEC_LEN + 1);
        char[] arr = s.toCharArray();
        int end= 0;
        if(arr[0] == '-'){
            positive = false;
            end = 1;
        }
        else
            positive = true;
        
        int cur_len = 0;
        int k = 1;
        for(int i = arr.length - 1 ; i >= end; i -- ){
            if(cur_len%DEC_LEN == 0)
            {
                k = 1;
                values.add(0);
            }
            cur_len ++ ;
            int rs = values.get(values.size() - 1);
            rs = rs + k * (arr[i] - '0');
            values.set(values.size() - 1, rs);
            k *= 10;
        }
    }

    public BigInteger multi(BigInteger n){
        this.positive = (this.positive == n.positive);
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < n.values.size() ; i ++){
            List<Integer> tmp = multi(this.values, n.values.get(i), i);
            add(result, tmp);
        }
        this.values = result;
        return this;
    }

    private List<Integer> multi(List<Integer> a, int n, int offset){
        List<Integer>result = new ArrayList<Integer>();
        for(int i  = 0; i < a.size() ; i++){
            List<Integer> tmp = multi(a.get(i), n, i);
            add(result, tmp);
        }
        List<Integer>off_result = new ArrayList<Integer>(result.size() + offset);
        for(int  i = 0 ; i < offset; i ++)
            off_result.add(0);

        for(int i:result)
            off_result.add(i);
        return off_result;
    }

    private List<Integer> multi(int i, int j, int offset){
        long k = (long)(i) * j;
        List<Integer> result = new ArrayList<Integer>(offset + 2);
        for(int m = 0 ; m < offset; m ++){
            result.add(0);
        }
        result.add((int)(k%DIV));
        k = k / DIV;
        if(k > 0)
        result.add((int)(k));
        return result; 
    }

    private void add(List<Integer> self, List<Integer> a){
        int s_v = 0;
        int a_v = 0;
        int tmp = 0;

        int self_size = self.size();
        for(int i = 0 ; i < a.size() || i < self_size; i ++){
            if(i < self_size)
                s_v = self.get(i);
            else
                s_v = 0;

            if(i < a.size())
                a_v = a.get(i);
            else
                a_v = 0;
            
            long rs = (long)s_v + a_v + tmp;
            s_v = (int)(rs%DIV);
            tmp = (int)(rs/DIV);

            if(i < self_size)
                self.set(i,s_v);
            else
                self.add(s_v);
        }
        if(tmp > 0)
            self.add(tmp);
    }


    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        if(!positive)
            sb.append('-');
        boolean begin = true;
        for(int k = values.size() - 1 ; k >= 0 ; k --){
           int i = values.get(k);
           if(begin){
                sb.append(i);
                begin = false;
           }
           else{
                String s = i + "";
                int zero_length = DEC_LEN - s.length();
                for(int j = 0 ; j < zero_length; j ++){
                    sb.append(0);
                }
                sb.append(s);
           }
        }
        return sb.toString();
    }
    
    public static void main(String args[]){
        BigInteger b1 = new BigInteger("12345678900000000000");
        BigInteger b2 = new BigInteger("-987654321000000000000");
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1.multi(b2));

    }
}
