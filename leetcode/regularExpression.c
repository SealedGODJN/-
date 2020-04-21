#include <stdio.h>
#include <stdlib.h>

#define bool int

bool isMatch(char * s, char * p){
    if(*p == NULL) return *s == NULL;
    bool result = *s && (*(s) == *(p) || *(p) == '.');
    if(*(p+1) == '*')
    {
        return isMatch(s,p+2) || (result && isMatch(s+1,p));
    }
    else return result && isMatch(s+1,p+1); // 判断第一个字符是否相同 且 后续的字符是否相同或符合正规表达式定义
}

int main()
{
    char *s = (char *)malloc(2 * sizeof(char));
    char *p = (char *)malloc(2 * sizeof(char));
    // *s = 'a';
    // *(s+1) = 'a';
    // *(s+2) = 'b';
    // *p = 'c';
    // *(p+1) = '*';
    // *(p+2) = 'a';
    // *(p+3) = '*';
    // *(p+4) = 'b';
    int a = isMatch(s, p);
    printf("%d\n", a);
}