/*rsa.c RSA算法实现*/
#include "encrypt.h"
#include <stdio.h>
#include <stdlib.h>

/*modexp 二进制平方乘算法函数*/
static Huge modexp(Huge a, Huge b, Huge n)
{
    Huge y;
    
    /*使用二进制平方乘法计算 pow(a,b) % n*/
    y=1;
    
    while(b != 0)
    {
        /*对于b中的每个1，累加y*/
        
        if(b & 1)
            y = (y*a) % n;
        
        /*对于b中的每一位，计算a的平方*/
        a = (a*a) % n;
        
        /*准备b中的下一位*/
        b = b>>1;
    }
    
    return y;
}

/*rsa_encipher RSA算法加密*/
void rsa_encipher(Huge plaintext, Huge *ciphertext, RsaPubKey pubkey)
{
    *ciphertext = modexp(plaintext, pubkey.e, pubkey.n);
    return;
}

/*rsa_decipher RSA算法解密*/
void rsa_decipher(Huge ciphertext, Huge *plaintext, RsaPriKey prikey)
{
    *plaintext = modexp(ciphertext, prikey.d, prikey.n);
    return;
}

int main() {
    //T 84 o 111 d 100 a 97 y 121 " " 32 i 105 s 115 " " 32 a 97 " " 32 n 110 i 105 c 99 e 101
    // Huge T = 84; //01010100;
    // Huge o = 111; //01101111;
    // Huge d = 100; //01100100;
    // Huge a = 97;  //01100001;
    // Huge y = 121;
    // Huge blank_1 = 32;
    // Huge i = 105;
    // Huge s = 115;
    // Huge blank_2 = 32;
    // Huge a_1 = 97;
    // Huge blank_3 = 32;
    // Huge n = 110;
    // Huge i_1 = 105;
    // Huge c = 99;
    // Huge e = 101;
    Huge a[15] = {84,111,100,97,121,32,105,115,32,97,32,110,105,99,101};
    Huge* ciphertext = malloc(1*sizeof(Huge));
    RsaPubKey pubkey;
    pubkey.e = 17;
    pubkey.n = 180; // p=11 q=19
    RsaPriKey prikey;
    prikey.d = 53;
    prikey.n = 180;
    // for (int i = 0; i < 15; i++) {
    //     rsa_encipher(a[i], ciphertext, pubkey);
    //     printf("%u ", ciphertext);
    // }
    // printf("\n");
    for (int i = 0; i < 15; i++) {
        rsa_decipher(a[i], ciphertext, prikey);
        printf("%u ", ciphertext);
    }
}