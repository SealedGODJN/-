#include<iostream>
using namespace std;
int x[100];    //当前作业调度————其中一种排列顺序
int bestx[100]; //当前最优作业调度
int m[100][100];////各作业所需的处理时间
                //M[j][i]代表第j个作业在第i台机器上的处理时间
int f1=0;//机器1完成处理时间
int f2=0;//机器2完成处理时间
int cf=0;//完成时间和
int bestf=10000;//当前最优值，即最优的处理时间和
int n;//作业数
 
void swap(int &a,int &b)
{
    int temp=a;
    a=b;
    b=temp;
}
 
void Backtrack(int t)
{    //t用来指示到达的层数（第几步，从0开始），同时也指示当前执行完第几个任务/作业
    int tempf,j;
    if(t>n) //到达叶子结点，搜索到最底部
    {
        if(cf<bestf)
        {
            for(int i=1; i<=n; i++)
                bestx[i]=x[i];//更新最优调度序列
            bestf=cf;//更新最优目标值
        }
    }
    else    //非叶子结点
    {
        for(j=t; j<=n; j++) //j用来指示选择了哪个任务/作业（也就是执行顺序）
        {
            f1+=m[x[j]][1];//选择第x[j]个任务在机器１上执行，作为当前的任务
            tempf=f2;//保存上一个作业在机器2的完成时间
            f2=(f1>f2?f1:f2)+m[x[j]][2];//保存当前作业在机器2的完成时间
            cf+=f2;               //在机器2上的完成时间和
            //如果该作业处理完之后，总时间已经超过最优时间，就直接回溯。
            //剪枝函数
            if(cf<bestf) //总时间小于最优时间
            {
                swap(x[t],x[j]);  //交换两个作业的位置，把选择出的原来在x[j]位置上的任务调到当前执行的位置x[t]
                Backtrack(t+1);   //深度搜索解空间树，进入下一层
                swap(x[t],x[j]); //进行回溯，还原，执行该层的下一个任务 //如果是叶子节点返回上一层
            }
            //回溯需要还原各个值
            f1-=m[x[j]][1];
            cf-=f2;
            f2=tempf;
        }
    }
}
 
int main()
{
    int i,j;
 
    cout<<"请输入作业数："<<endl;
    cin>>n;
 
    cout<<"请输入在各机器上的处理时间"<<endl;
    for(i=1; i<=2; i++) //ｉ从１开始
        for(j=1; j<=n; j++)
            cin>>m[j][i];//第j个作业,第i台机器的时间值
    for(i=1; i<=n; i++)
        x[i]=i;//初始化当前作业调度的一种排列顺序
    Backtrack(1);
 
    cout<<"调度作业顺序："<<endl;
    for(i=1; i<=n; i++)
        cout<<bestx[i]<<' ';
    cout<<endl;
 
    cout<<"处理时间："<<endl;
    cout<<bestf;
    return 0;
}
/*
测试数据：
3
2 3 2
1 1 3
3
2 5 4
3 2 1
*/