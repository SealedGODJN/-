#ifndef _HUFFMAN_CODE_H_
#define _HUFFMAN_CODE_H_

typedef char **huffmancode;

typedef struct {
    int weigth;
    int parent, lchild, rchild;
} htnode, *huffmantree;

#endif