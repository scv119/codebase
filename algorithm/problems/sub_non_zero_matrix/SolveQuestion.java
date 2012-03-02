public class SolveQuestion{
    private int[][] matrix;
    private int height;
    private int width;

    private int stack[];
    private int bs;

    public SolveQuestion(int[][] matrix){
        this.matrix = matrix;
        if(matrx != null){
            this.height = matrix.length;
            if(this.height > 0)
                this.width = matrix[0].length;
        }
        stack = new int[width*2];
    }

    private int rst(){
        bs = 0;
    }
    private int push(int idx, int value){
        int idx;
        while(bs > 0){
            if(stack[bs-1] > value)
                bs = bs - 2;
            else
                idx = stack[bs-2] + 1 
                break;
        }
        if(bs == 0)
            idx = 0;
        stack[bs++] = idx;
        stack[bs++] = value;
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
                r[j] = width - 1 - push(j, h[j]); 
            }
            for(int j = 0 ;j < widht; j ++){
                int sq = h[j] * (r[j] - l[j] + 1);
                if(sq>max)
                    max = sq;
            }
            
        }

        return max;
    }
}
