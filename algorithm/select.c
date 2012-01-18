#include <stdio.h>
void __sort(int * array, int from , int end);
void __debug(int * array, int from, int end);
void swap(int * array, int from, int end);

int select(int * array, int length, int k, int from, int end)
{
    printf("start k=%d,from=%d,end=%d\n",k,from,end);
    int i;
    if ( k >= length || length <= 0 || from < 0 || end > length)
        return -1;
    

    if (end - from <= 5)
    {
        __sort(array, from, end);
        __debug(array, from, end);
        printf("mid=%d\n", array[k]);
        printf("k=%d, pos=%d\n", k, k);
        return array[k];
    }
            
    int val = (end - from )/5;
    
    for ( i = 0 ; i < val ; i ++ ){
        __sort(array, from + i * 5 , from + i * 5 + 5);
    }

    for ( i = 0 ; i < val ; i ++ ){
        swap (array, from + i, from + i * 5 + 2);
    }


    int mid = select(array, length,  from + val/2 ,from , val + from);
    
    __debug(array, from, end);
    printf("mid=%d\n", mid);

    int start = from;
    int equal = from;
    int endp   = end-1;

    while(start <= endp){
        if (array[start] > mid){
            swap(array, start, endp);
            endp --;
        }
        else if (array[start] < mid){
            start ++;
        }
        else {
            swap(array, equal, start); 
            equal ++;
            start ++;
        }
    }

    int pos = start - 1;

    for ( i = from; i < equal; i ++ ){
        swap(array, i, --start);
    }

    __debug(array, from, end);
    printf("k=%d, pos=%d\n", k, pos);
 
    if(k > pos)
        return select(array, length, k ,  pos + 1, end);
    else if(k < pos)
        return select(array, length, k, from,  pos - 1);
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
    int array[100];
    int i;

    for ( i = 0 ; i < 100; i ++){
        array[i] = 99 - i;
    }
//    printf("%d", select ( array, 5, 3, 0, 5));
    printf("%d", select ( array, 100, 40, 0, 100));
}
