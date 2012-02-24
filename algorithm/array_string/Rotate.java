public class Rotate{
    public static void rotate(int[][] matrix, int n){
         int x = n/2;
         int y = n/2 + n%2;

         n = n - 1;
         for (int i = 0; i < x ;i ++ ){
            for ( int j = 0; j < y ; j ++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j][i];
                matrix[n-j][i] = matrix[n-i][n-j];
                matrix[n-i][n-j] = matrix[j][n-i];
                matrix[j][n-i] = temp;
            }
         }
    }

    public static void print(int [][] matrix, int n){
        for( int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }

        System.out.println("");
    }

    public static void main(String args[]){
        int[][] matrix = new int[5][];
        for(int i = 0; i < 5; i ++){
            matrix[i] = new int[5];
            for (int j = 0 ; j < 5; j ++){
                matrix[i][j] = i * 5 + j;
            }
        }

        print(matrix,5);
        for(int i = 0 ; i < 5; i ++){
        rotate(matrix,5);
        print(matrix,5);
        }
    }
}
