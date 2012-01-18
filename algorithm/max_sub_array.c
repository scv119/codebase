#include <stdio.h>
#include <string.h>

int max(int a, int b);
int find_max_sub_array(int* array, int size){
    int max_now = 0;
    int max_all = 0;
    
    int i = 0;
    for( i = 0 ; i < size; i ++){
        if (i == 0)
        {
            max_now = max_all = array[i];
        }
        else{
            max_now = max(0, max_now + array[i]);
            max_all = max(max_all, max_now);
            printf("%d %d %d\n", max_now, max_all, array[i]);
        }
    }
    return max_all;
}

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int array[10] = {2, 2, -3, 4, -7, 6, -2, -8, 3};
    printf("%d", find_max_sub_array(array, 9));
    return 0;
}
