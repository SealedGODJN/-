/*
    作者：黄江南
    目的：熟悉斐波那契堆的实现
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <float.h>
#include "FibonacciHeap.h"

#if 0
#define LOG2(x) ({ \
        unsigned int _i = 0; \
        __asm__("bsr %1, %0" : "=r" (_i) : "r" ((x))); \
        _i; })
#else   // 注意：通过gcc编译时，要添加 -lm 选项。
#define LOG2(x) ((log((double)(x))) / (log(2.0)))
#endif

