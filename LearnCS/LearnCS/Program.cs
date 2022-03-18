using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LearnCS
{
	class Program
	{
		public delegate void MyDelegate();

		public /*static*/ void helloWorld()
		{
			Console.WriteLine("hello delegate");
		}

		//static void Main(string[] args)
		//{
		//	// lambda表达式
		//	Console.WriteLine(LambdaClass.add(1, 2));

		//	// 静态方法
		//	//MyDelegate myDelegate = new MyDelegate(Program.helloWorld);

		//	// 实例方法
		//	//Program program = new Program();
		//	//MyDelegate myDelegate = new MyDelegate(program.helloWorld);
		//	MyDelegate myDelegate = new MyDelegate(new Program().helloWorld);
		//	myDelegate();

		//}
	}

	class LambdaClass
	{
		public static int add(int a, int b) => a + b;
	}
}
