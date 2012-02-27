public class RemoveZero{
    public static void removeZero(int [][] matrix, int n ){
        int[] zeroRow = new int[n];
        int[] zeroCol = new int[n];

        for (int i = 0 ; i < n ; i ++){
            for ( int j = 0 ; j < n ; j ++){
                if(matrix[i][j] == 0){
                    zeroRow[i] = 1;
                    zeroCol[j] = 1;
                }
            }
        }


        for ( int i  = 0 ; i < n ; i ++){
            for( int j= 0 ; j < n ; j ++){
                if(zeroRow[i] == 1 || zeroCol[j] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
    }
    

    public static void print(int [][] matrix, int n ){
        for ( int i = 0 ; i < n ; i++){
            for ( int j = 0 ;j < n ; j++){
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String []args){
        int[][] matrix = new int[5][];
        for ( int i = 0  ; i < 5; i ++){
            matrix[i] =  new int[5];
            for ( int j = 0 ; j < 5; j ++){
                matrix[i][j] = i * 5 + j ; 
            }
        }
        
        matrix [1][3] = matrix [2][0] = matrix [3][4] = 0;
        print(matrix, 5);
        removeZero(matrix, 5);
        print(matrix, 5);

    }
}
