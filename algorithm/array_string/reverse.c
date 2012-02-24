
void reverse(char* s){
    char* start;
    char* end;
    char tmp;
    start = end = s;
    while((*end) != '\0'){
        end ++ ;
    }
    while(start != end){
        end --;
        tmp = *start;
        *start = *end;
        *end = tmp;
        start ++;
    }
}


int main(){
    char str[] = "this is a string";
    reverse(str);
    printf("%s\n", str);
    return 0;
}
