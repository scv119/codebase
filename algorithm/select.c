#include <stdio.h>
void __sort(int * array, int from , int end);
void __debug(int * array, int from, int end);
void swap(int * array, int from, int end);

int select(int * array, int k, int size)
{
    int i;
    if ( k >= size || k < 0)
        return -1;
    

    if (size <= 5)
    {
        __sort(array, 0, size);
        return array[k];
    }
            
    int val = size /5;
    
    for ( i = 0 ; i < val ; i ++ ){
        __sort(array, i * 5 , i * 5 + 5);
    }

    for ( i = 0 ; i < val ; i ++ ){
        swap (array,  i,  i * 5 + 2);
    }


    int mid = select(array, val/2 ,val);
    
    printf("mid=%d\n", mid);

    int small_p = 0;
    int equal_p = 0;
    int big_p = size-1;

    while ( small_p <=  big_p ){
        int value = array[small_p];
        if (value < mid){
            small_p ++;
        }
        else if(value > mid){
            swap(array, small_p, big_p);
            big_p --;
        }
        else {
            swap(array, equal_p,small_p); 
            equal_p ++;
            small_p ++;
        }
    }
    
    int pos = big_p;

    for (i = 0 ; i < equal_p; i ++){
       swap(array, i, big_p);
       big_p --;
    }

    printf("k=%d, pos=%d\n", k, pos);
 
    if(k > pos)
        return select(array + pos + 1, k - pos - 1 ,size - pos - 1 );
    else if(k < pos)
        return select(array, k,  pos);
    else return mid;
    
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

void __debug(int * array, int from, int end){
   int i = 0;
   printf("from=%d, end=%d\n",from, end);
   for (i = from; i < end; i++){
        printf ("%d ", *(array + i));
   }
   printf ("\n");
}

int main(){
    int array[1000];
    int i;

    for ( i = 0 ; i < 1000; i ++){
        array[i] = 999 - i;
    }
//    printf("%d", select ( array, 5, 3, 0, 5));
    printf("%d", select ( array, 40, 1000));
}
