# 学习c#的特点

## 目的

深入了解“基于Unity3d的互动式剧情游戏”中的项目难点

## c#语法知识点

### delegate

委托从字面上理解就是一种代理，类似于房屋中介，由租房人委托中介为其租赁房屋。

在 [C#](http://c.biancheng.net/csharp/) 语言中，委托则委托某个方法来实现具体的功能。

委托是一种引用类型，虽然在定义委托时与方法有些相似，但不能将其称为方法。

委托在使用时遵循三步走的原则，即定义声明委托、实例化委托以及调用委托。

从[数据结构](http://c.biancheng.net/data_structure/)来讲，委托是和类一样是一种用户自定义类型。

委托是方法的抽象，它存储的就是一系列具有相同签名和返回回类型的方法的地址。

调用委托的时候，委托包含的所有方法将被执行。

委托是 C# 语言中的一个特色，通常将委托分为命名方法委托、多播委托、匿名委托，其中命名方法委托是使用最多的一种委托。

#### 命名委托

在 [C#](http://c.biancheng.net/csharp/) 语言中命名方法委托是最常用的一种委托，其定义的语法形式如下。

```c#
修饰符  delegate  返回值类型  委托名 ( 参数列表 );
```

从上面的定义可以看出，委托的定义与方法的定义是相似的。例如定义一个不带参数的委托，代码如下。

```c#
public delegate void MyDelegate(); // 此delegate的参数为空
```


在定义好委托后就到了实例化委托的步骤，命名方法委托在实例化委托时必须带入方法的具体名称。

实例化委托的语法形式如下。

```c#
委托名  委托对象名 = new 委托名 ( 方法名 );
```


委托中传递的方法名既可以是==静态方法的名称==，也可以是==实例方法的名称==。

需要注意的是，在委托中所写的方法名必须与委托定义时的返回值类型和参数列表相同。

在实例化委托后即可调用委托，语法形式如下。

```c#
委托对象名 ( 参数列表 );
```

在这里，参数列表中传递的参数与委托定义的参数列表相同即可。

下面分别通过两个实例来演示在委托中应用静态方法和实例方法的形式。

##### 【实例 1】

创建委托，在委托中传入静态方法于控制台输出“Hello Delegate!”。

根据题目要求，代码如下。

```c#
class Program
{
    public delegate void MyDelegate();
    static void Main(string[] args)
    {
        MyDelegate myDelegate = new MyDelegate(Test.SayHello);
        myDelegate();
    }
}
class Test
{
    public static void SayHello()
    {
        Console.WriteLine("Hello Delegate!");
    }
}
```

若使用静态方法，在向委托中传递方法名时只需要用“`类名.方法名`”的形式。

##### 【实例 2】

将实例 1 中的静态方法改成实例方法。

根据题目要求，代码如下。

```c#
class Program
{
    public delegate void MyDelegate();
    static void Main(string[] args)
    {
        MyDelegate myDelegate = new MyDelegate(new Test().SayHello);
        myDelegate();
    }
}
class Test
{
    public void SayHello()
    {
        Console.WriteLine("Hello Delegate!");
    }
}
```

##### 【实例 3】

使用委托完成将图书信息按照价格升序排序的操作。

根据题目要求，先定义图书信息类，然后定义对图书价格排序的方法。图书信息类的代码如下。

```c#
class Book:IComparable<Book>
{
    //定义构造方法为图书名称和价格赋值
    public Book(string name,double price)
    {
        Name = name;
        Price = price;
    }
    //定义图书名称属性
    public string Name { get; set; }
    //定义图价格属性
    public double Price { get; set; }
    //实现比较器中比较的方法
    public int CompareTo(Book other)
    {
        return (int)(this.Price - other.Price);
    }
    //重写ToString方法，返回图书名称和价格
    public override string ToString()
    {
        return Name + ":" + Price;
    }
    //图书信息排序
    public static void BookSort(Book[] books)
    {
        Array.Sort(books);
    }
}
```



```c#
class Program
{
    //定义对图书信息排序的委托
    public delegate void BookDelegate(Book[] books);
    static void Main(string[] args)
    {
        BookDelegate bookDelegate = new BookDelegate(Book.BookSort);
        Book[] book = new Book[3];
        book[0] = new code_1.Book("计算机应用", 50);
        book[1] = new code_1.Book("C# 教程", 59);
        book[2] = new code_1.Book("VS2015应用", 49);
        bookDelegate(book);
        foreach(Book bk in book)
        {
            Console.WriteLine(bk);
        }
    }
}
```

由于 Book[] 数组是引用类型，因此通过委托调用后其值也发生了相应的变化，即 book 数组中的值已经是完成了排序操作后的结果。

#### 多播委托

在 [C#](http://c.biancheng.net/csharp/) 语言中多播委托是指在一个委托中注册多个方法，在注册方法时可以在委托中使用==加号运算符或者减号运算符来实现添加或撤销方法==。

在现实生活中，多播委托的实例是随处可见的，例如某点餐的应用程序，既可以预定普通的餐饮也可以预定蛋糕、鲜花、水果等商品。

在这里委托相当于点餐平台，每一个类型的商品可以理解为在委托上注册的一个方法。

下面通过实例来演示多播委托的应用。

##### 【实例】

模拟点餐平台预定不同类型的商品。

根据题目要求，在实例中分别预定快餐、蛋糕、鲜花三类商品，代码如下。

```c#
class Program {
    // 购买商品委托
    public delegate void OrderDelegate();
    static void Main(string[] args) {
		OrderDelegate order = new OrderDelegate(Order.OrderFastfood);
        //order += OrderFastfood;
        order += Order.OrderCake;
        order += Order.OrderFlowers;
		order();
    }
}

class Order {
    public void OrderFastfood() {
        Console.WriteLine("预定快餐");
    }
    public void OrderCake() {
        Console.WriteLine("预定蛋糕");
    }
    public void OrderFlowers() {
        Console.WriteLine("预定鲜花");
    }
}
```

撤销购买鲜花操作的代码如下。

```c#
orderDelegate -= Order.BuyFlower;
```

#### 匿名委托

在 [C#](http://c.biancheng.net/csharp/) 语言中匿名委托是指使用匿名方法注册在委托上，实际上是在委托中通过定义代码块来实现委托的作用，具体的语法形式如下。

```c#
//1. 定义委托
修饰符  delegate  返回值类型  委托名 ( 参数列表 );
```

```c#
//2. 定义匿名委托
委托名  委托对象 = delegate
{
    //代码块
};
```

```c#
//3. 调用匿名委托
委托对象名 ( 参数列表 );
```

通过上面 3 个步骤即可完成匿名委托的定义和调用，需要注意的是，在定义匿名委托时代码块结束后==要在 `{}` 后加上分号==。

##### 【委托】

使用匿名委托计算长方形的面积。

根据题目要求，代码如下。

```c#
class Program
{
    public delegate void AreaDelegate(double length, double width);
    static void Main(string[] args)
    {
        Console.WriteLine("请输入长方形的长：");
        double length = double.Parse(Console.ReadLine());
        Console.WriteLine("请输入长方形的宽：");
        double width = double.Parse(Console.ReadLine());
        AreaDelegate areaDelegate = delegate
        {
            Console.WriteLine("长方形的面积为：" + length * width);
        };
        areaDelegate(length, width);
    }
}
```

从上面的执行效果可以看岀，在使用匿名委托时并没有定义方法，而是在实例化委托时直接实现了具体的操作。

由于匿名委托并不能很好地实现代码的重用，匿名委托通常适用于实现一些仅需要使用一次委托中代码的情况，并且代码比较少。
