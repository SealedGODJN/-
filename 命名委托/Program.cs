using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace code_1
{
    class LambdaClass {
        public static int add(int a, int b) => a + b;
    }
    public static class Program {
        public delegate void MyDelegate();

        public static void helloWorld() {
            Console.WriteLine("hello delegate");
        }

        static void Main(string[] args) {
            // lambda表达式
            Console.WriteLine(LambdaClass.add(1, 2));

            MyDelegate myDelegate = new MyDelegate(Program.helloWorld);
            myDelegate();

        }
    }
}
