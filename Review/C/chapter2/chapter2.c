#include <stdio.h>

void jolly()
{
    printf("For he's jolly good fellow!\n");
}

void deny()
{
    printf("Which nobody can deny");
}

int main()
{
    for (int i = 0; i < 3; i++) 
    {
        jolly();
    }
    deny();
    getchar();
}