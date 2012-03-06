import java.util.* ; 
public class Test{
    private static long f1_count;
    private static long f2_count;
    private static Map<String, Float> map = new HashMap<String, Float>();
    public static float fun(int girl_num, int boy_num, int continus_num){
        f1_count = 0;
        return fun1(girl_num, boy_num, continus_num, continus_num);
    }

    private static float fun1(int g, int b, int k, int f){
        f1_count ++;
        if(g < 0 || b < 0 || k < 0|| f < 0)
            return 0;
        if(f > b)
            return 0;

        if(g == 0 || f == 0)
            return 1;

        String key = g+"|"+b+"|"+k+"|"+f;
        if(map.containsKey(key))
            return map.get(key);

        float result = (float)g/(g+b)*fun1(g-1, b, k, k) + (float)b/(g+b)*fun1(g, b-1, k, f- 1);
        map.put(key, result);
        return map.get(key);
    }

    public static double brute_force(int girl, int boy, int cont_boy){
        f2_count = 0;
        int arr[] =  new int[girl + boy];
        double success_count = foo(arr, 0, girl, boy, cont_boy);
        int count = girl + boy;
        double full_count = 1;
        while(count >= 1)
            full_count *= count --;
        return (float)success_count/full_count;
    }

    private static double foo(int arr[], int cur_idx, int girl, int boy, int cont_boy){
        f2_count ++;
        if(cur_idx >= arr.length)
            return check(arr, cont_boy);

        double result = 0;
        if(girl > 0)
        {
            arr[cur_idx] = 0;
            result += girl * foo(arr, cur_idx + 1, girl -1, boy, cont_boy);
        }

        if(boy > 0)
        {
            arr[cur_idx] = 1;
            result += boy * foo(arr, cur_idx + 1, girl, boy -1, cont_boy);
        }

        return result;
    }

    private static int check(int arr[], int cont_boy){
        int count = 0;
        for(int  i = 0 ; i < arr.length; i ++){
           if(arr[i] == 1){
                count ++;
                if(count >= cont_boy)
                    return 1;
            }
            else{
                count = 0;
            }
        }
        return 0;
    }

    

    public static void main(String args[]){
        System.out.println(fun(7,14,9));
        System.out.println(brute_force(7,14,9));
        System.out.println(f1_count+" "+f2_count);
    }
}
