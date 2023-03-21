#include <ctime>
#include <iostream>

#define ROW 100000
#define COL 100000

int main()
{
    // std::cout << "132456";

    long start = time(NULL);
    std::cout << "start:" << start << std::endl;

    // int N = 10000;
    // int A[N][N];
    // for (int i = 0; i < N; i++) {
    //     for (int j = 0; j < N; j++) {
    //         A[i][j] = 0;
    //     }
    // }
    // std::cout << std::endl << A[0][0];

    int(*p)[COL] = new int[ROW][COL];
    // for(int i=0; i<ROW; ++i)
    // {
    //     for(int j=0; j<COL; ++j)
    //     {
    //         p[i][j] = i+j;
    //     }
    // }

    for (int j = 0; j < COL; ++j)
    {
        for (int i = 0; i < ROW; ++i)
        {
            p[i][j] = i + j;
        }
    }

    std::cout << "end:" << time(NULL) - start << std::endl;
    delete[] p;
}