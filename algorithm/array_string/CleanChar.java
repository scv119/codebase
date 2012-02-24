public class CleanChar{
    public static void clean(char[] str){
        if (str == null || str.length < 2)
            return;

        int tail = 1;
        for (int i = 1; i < str.length; i ++){
            int j  = 0;
            for( ; j < tail ;j ++)
            {
                if(str[i] == str[j])
                    break;
            }

            if( j == tail)
            {
                str[tail++] = str[j];
            }
        } 
        str[tail] = '\0';
    }


    public static void main(String args[]){
        char[] a = "test123\0".toCharArray();
        clean(a);
        System.out.println(a);
        a = "abababab\0".toCharArray();
        clean(a);
        System.out.println(a);
    }
}
