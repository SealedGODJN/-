#include <stdio.h>
#include <iostream>
//#include <Windows.h>
using namespace std;

typedef unsigned char UCHAR;

//x的y次方
long pow(int x, int y)
{
	long nValue = 1;
	while(y-- > 0) nValue *= x;
	return nValue;
}

int main()
{
	cout << "类型长度：" << " short-" << sizeof(short) << " int-" << sizeof(int) 
		<< " long-" << sizeof(long) << " long long-" << sizeof(long long) << endl;

	long nValue = 0x12345678;//16进制，12→34→56→78从前到后为高字节到低字节
	printf("变量地址： %p\n", &nValue);
	long nRealValue = 0;
	for(int i=8; i>=1; i--)//16进制转10进制
	{
		nRealValue += (i*pow(16, 8-i));
	}
	cout << "变量大小： " << nRealValue << endl;
	
	UCHAR *p = (UCHAR*)&nValue;//字符指针p指向变量地址（即变量在内存中存储的第一个低地址字节）
	printf("地址和值：");
	for(int i=0; i<sizeof(long); i++)
	{
		printf(" %p-%02x", p+i, *(p+i));
	}

	//判断大小端（内存地址固定为从低到高，大端：低地址保存高字节，小端：低地址保存低字节）
	cout << endl << "判断结果： ";
	if((*p == 0x78) && (*(p+1)==0x56))
	{
		cout << "小端" << endl;
	}
	else if((*p == 0x12) && (*(p+1)==0x34))
	{
		cout << "大端" << endl;
	}
	else
	{
		cout << "未知" << endl;
	}

	getchar();
	return 0;
}
