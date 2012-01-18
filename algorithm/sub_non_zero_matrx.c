#include <stdio.h>
#include <string.h>

typedef struct{
    int m;
    int n;
    int **matrix;
}Matrxi;

int max_none_zero_sub_matrx(Matrix* _matrix)
{
    int m  = _matrix -> m;
    int n  = _matrix -> n;
    int **matrix = _matrix -> matrix;
    int lstack[m];
    int rstack[m];
    int nz_size[m];
    memset(nz_size, 0, m * 4);
    memset(lstack, 0, m * 4);
    memset(rstack, 0, m * 4);

    int i = 0;
    int j = 0;
    int max = 0;
    for (;i < n ; i++){
        memset(lstack, 0, m * 4);
        memset(rstack, 0, m * 4);
        for(j = 0; j <n; j++){
            if(matrx[i][j] > 0){
                nz_size[j] = nz_size[j]+1;
            }
            else{
                nz_size[j] = 0;
            }
        }
        int pre = 0;
        for (j = 0; j <n ;j ++){
            
        }
    }
}
