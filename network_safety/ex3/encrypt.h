/*encrypt.h*/
#ifndef ENCRYPT_H
#define ENCRYPT_H

/*在一个安全实现中，Huge 最少要400位10进制数字*/
typedef unsigned long Huge; 

/*为RSA公钥定义一个数据结构*/
typedef struct RsaPubKey_
{
    Huge e;
    Huge n;
}RsaPubKey;

/*为RSA私钥定义一个数据结构*/
typedef struct RsaPriKey_
{
    Huge d;
    Huge n;
}RsaPriKey;

/*函数声明*/
void des_encipher(const unsigned char *plaintext, unsigned char *ciphertext, const unsigned char *key);
void des_decipher(const unsigned char *ciphertext, unsigned char *plaintext, const unsigned char *key);
void rsa_encipher(Huge plaintext, Huge *ciphertext, RsaPubKey pubkey);
void rsa_decipher(Huge ciphertext,Huge *plaintext, RsaPriKey prikey);

#endif // ENCRYPT_H