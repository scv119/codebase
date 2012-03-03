public class SolveQuestion{
    private int[][] matrix;
    private int height;
    private int width;

    private int stack[];
    private int bs;

    public SolveQuestion(int[][] matrix){
        this.matrix = matrix;
        if(matrix != null){
            this.height = matrix.length;
            if(this.height > 0)
                this.width = matrix[0].length;
        }
        stack = new int[width*2];
    }

    public SolveQuestion mark(int i, int j ){
    	if(i < height && j < width){
    		this.matrix[i][j] = 1;
    	}
    	return this;
    }
    
    private void rst(){
        bs = 0;
    }

    private int push(int idx, int value){
        int rs = -1;
        while(bs > 0){
            if(stack[bs-1] >= value) {
                bs = bs - 2;
            }
            else{
                rs = stack[bs-2] + 1;
                break;
            }
        }
        if(bs == 0)
            rs = 0;
        stack[bs++] = idx;
        stack[bs++] = value;
        return rs;
    }

    public int solve(){
        int max = 0;
        int h[] = new int[width];
        int l[] = new int[width];
        int r[] = new int[width];
        for( int i = 0 ; i < height; i ++){
            rst();
            for(int j = 0 ; j < width ;j ++){
               h[j] = matrix[i][j] == 1 ? h[j] + 1: 0; 
               l[j] = push(j, h[j]);
            }
            rst();
            for(int j = width-1  ; j >= 0; j --){
                r[j] = width - 1 - push(width - 1 - j, h[j]); 
            }

            for(int j = 0 ;j < width; j ++){
                System.out.print(h[j]+"/"+l[j]+"/"+r[j]+"\t");
                int sq = h[j] * (r[j] - l[j] + 1);
                if(sq>max)
                    max = sq;
            }
            System.out.println();
            
        }

        return max;
    }

    public static void main(String args[]){
        int matrix[][] = new int[5][];
        for(int i = 0 ; i < 5; i ++){
            matrix[i] = new int [5];
            for(int j = 0 ;j < 5; j ++){
                matrix[i][j] = 0;
            }
        }

        SolveQuestion sq = new SolveQuestion(matrix);
        sq.mark(1,1).mark(1, 2).mark(1,3).mark(1,4).mark(2, 2).mark(2,3).mark(3, 2).mark(3, 3).mark(4,2).mark(0,2);
        System.out.println(sq.solve());
    }
}
