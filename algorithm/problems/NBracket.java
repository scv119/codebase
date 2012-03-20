public class NBracket{
    public void printAll(int n){
        char[] arr = new char[2*n];
        f(0,n,n,arr);
    }

    private void f(int idx, int lb, int rb, char[] arr){
        if(idx >= arr.length)
            System.out.println(arr);
        else{
            if(lb < rb){
               arr[idx] = ')';
               f(idx + 1, lb, rb -1 ,arr);
            }
            if(lb <= rb && lb > 0){
               arr[idx]  = '(';
               f(idx + 1, lb - 1, rb, arr);
            }
        }
    }

    public static void main(String args[]){
        NBracket nb = new NBracket();
        nb.printAll(5);
    }
}
