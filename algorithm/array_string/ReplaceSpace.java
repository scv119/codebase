public class ReplaceSpace{
    public static void replace(char[] array, int length){
        
        if(array == null)
            return;

        int space_num = 0;
        for(int i = 0 ; i < length; i ++){
            if(array[i] == ' ')
                space_num ++;
        }

        int new_length = length + 2 * space_num;

        for ( int i = (length - 1); i >= 0 ; i --){
            if(array[i] == ' '){
                array[new_length-1] = '0';
                array[new_length-2] = '2';
                array[new_length-3] = '%';
                new_length = new_length - 3;
            }
            else
                array[--new_length] = array[i];
        }
    }

    public static void main(String args[]){
        String s = "Fuck This Shit !!!";
        String arr = s + "\0\0\0\0\0\0\0\0\0\0\0\0\0";
        char[] arrs = arr.toCharArray();
        replace(arrs, s.length());
        System.out.println(arrs);
    }
}
