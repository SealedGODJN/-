#include <stdio.h>

#define MAXSIZE 100

double p(int n,double x)
{
    struct stack
    {
        int no;         // 保存n
        double value;   // 保存Pn(x)
    }st[MAXSIZE];
    int top = -1, i; // top：栈st的栈顶指针
    double fv1 = 1, fv2 = 2 * x;

    for (i = n; i >= 2; i--)
    {
        top++; // n决定了栈的深度
        st[top].no = i;
    }
    while (top>=0)
    {
        st[top].value = 2*x*fv2-2*(st[top].no-1)*fv1; 
        // 为何从高层向底层走？我认为应该从低往高走
        // 对，就是从低往高走，P(n)放在栈底，接下来是P(n-1)，...，栈顶是P(2)
        // 【因为P1(x)和P2(x)的值都不用计算】
        fv1 = fv2;
        fv2 = st[top].value;
        top--;
    }
    if (n==0)
    {
        return fv1;
    }

    return fv2;
}

int main()
{
    double result = p(3, 2);
    printf("%f", result);
}