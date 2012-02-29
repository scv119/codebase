#include <stdio.h>
void swap(int * array, int p, int q);
void quick_sort(int * array, int size){
    if(size <= 1)
        return; 
    int small_p = 1;
    int k       = array[0];
    int i = 0;
    for(i = 1; i < size; i++){
        int value = array[i];
        if(value < k){
            swap(array,small_p,i);
            small_p ++;
        }
    }
    swap(array, 0 , small_p - 1);
    quick_sort(array, small_p - 1);
    quick_sort(array+small_p, size - small_p);
}

void swap(int * array, int p , int q){
    int tmp = array[p];
    array[p] = array[q];
    array[q] = tmp;
}

void print_array(int * array, int len){
    int i  = 0;
    for (;i< len ; i++){
        printf("%d ", *(array + i));
    }
    printf("\n");
}


int main(){
    int array[100];
    int i = 0 ;
    for (; i < 100; i ++){
        array[i] = 99 - i;
    }
    quick_sort(array, 100);
    print_array(array, 100);
    return 0;
}
