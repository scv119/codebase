#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    struct Node* next;
    int value;
} Node;

void _remove_next_node(Node* node);
void de_dup(Node* head);
Node* init_linked_list(int* arr, int len);

void _remove_next_node(Node* node){
    if (node == NULL)
        return;
    Node* next = node -> next;
    if (next == NULL)
        return;
    node -> next = next -> next;
    free(next);
}

void de_dup(Node* head){
    Node* i = head;
    Node* j;

    while( NULL != i){
        j = i; 
        while (j -> next){
            if ( j -> next -> value == i -> value){
                _remove_next_node(j);
            }
            else{
                j = j -> next;
            }
        }
        i = i -> next;
    }
}

Node* init_linked_list(int* arr, int len){
    if(len <= 0 )
        return NULL;

    printf("%d\n", len);

    Node* head ; 
    Node* pre = NULL;
    int i;
    for( i = 0 ; i < len ; i ++ ){
        Node* now;
        now = malloc(sizeof(Node));
        memset(now, 0, sizeof(Node));
        now -> value = arr[i];
        if(NULL == pre){
            head = now; 
        }else{
            pre -> next = now; 
        }
        pre = now;
    }
    return head;
}

int main(){
    int array[] = {1, 2, 4, 7,  3, 4, 5, 7};
    Node* list = init_linked_list(array, sizeof(array)/sizeof(int));
    Node* head = list;
    de_dup(head);
    list = head;
    while(list != NULL){
        printf("%d ", list-> value);
        list = list -> next;
    }
    return 0;
}
