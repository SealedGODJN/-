#include <stdio.h>
#define MAXK 10001
int k, a[MAXK], dp[MAXK], start[MAXK], end[MAXK];
int main()
{
    int i;
    scanf("%d", &k);
    for (i = 0; i < k; i++)
        scanf("%d", &a[i]);
    dp[0] = a[0];
    start[0] = 0;
    end[0] = 0;
    for (i = 1; i < k; i++)
    {
        if (a[i] + dp[i - 1] >= a[i])
        {
            dp[i] = a[i] + dp[i - 1];
            start[i] = start[i - 1];
            end[i] = i;
        }
        else
        {
            dp[i] = a[i];
            start[i] = i;
            end[i] = i;
        }
    }
    int maxsum = dp[0], s = start[0], e = end[0];
    for (i = 1; i < k; i++)
    {
        if (maxsum < dp[i])
        {
            maxsum = dp[i];
            s = start[i];
            e = end[i];
        }
    }
    if (maxsum < 0)
    {
        printf("%d %d %d\n", 0, a[0], a[k - 1]);
    }
    else
        printf("%d %d %d\n", maxsum, a[s], a[e]);
    return 0;
}