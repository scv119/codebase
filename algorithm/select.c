#include <stdio.h>
void __sort(int * array, int from , int end);
void swap(int * array, int from, int end);

int select(int * array, int length, int k, int from, int end)
{
    int i;
    if ( k >= length || length <= 0 || from < 0 || end > length)
        return -1;

    if (end - from <= 5)
    {
        __sort(array, from, end);
        return array[from + k];
    }
            
    int val = (end - from )/5;
    

    for ( i = 0 ; i < val ; i ++ ){
        __sort(array, from + i * 5 , from + i * 5 + 5);
    }

    for ( i = 0 ; i < val ; i ++ ){
        swap (array, from + i, from + i * 5 + 3);
    }

    int mid = select(array, length, ( end - from )/2 ,from , end);
    int mid_idx = from + 2 * val + (end - from)/2;

    if (mid_idx == k + from)
       return mid;
    int start = from;
    int endp   = end-1;

    while(start < endp){
        if (array[start] > mid){
            swap(array, start, endp);
            endp --;
        }
        else 
            start ++ ;
    }
    if(k > start)
        return select(array, length, k - start - 1, from + start + 1, end);
    else
        return select(array, length, k, from, from + start - 1);
    
}

void __sort(int * array, int from, int end){
    int i,j;
    for (i = from; i < end - 1; i ++){
        int min_idx = -1;
        int min_val = 0;
        for (j = i ; j < end; j++){
           if (-1 == min_idx){
                min_idx = j;
                min_val = array[j];
           }
           else if (array[j] < min_val)
           {
                min_idx = j;
                min_val = array[j];
           }
        }
        swap(array, i, min_idx);
    }
}

void swap(int * array, int a, int b){
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
}

int main(){
    int array[10] = {1,2,3,4,5,6,7,8,9,10} ;
    printf("%d", select ( array, 10, 5, 0, 10));
}
