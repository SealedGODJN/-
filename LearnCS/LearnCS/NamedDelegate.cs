using System;

public class Class1
{
	public Class1()
	{
	}

	// 在class Book 前添加 public。
	// 类或结构的默认访问类型是internal，是不能在类外访问。
	public delegate void SortBookPrice(Book[] books);

	// 同一个工程中，只能有一个Main函数
	//static void Main(string[] args)
	//{
	//	Book[] book = new Book[3];
	//	book[0] = new Book("计算机应用", 50);
	//	book[1] = new Book("C# 教程", 59);
	//	book[2] = new Book("VS2015应用", 49);
	//	SortBookPrice sortBookPrice = new SortBookPrice(Book.BookSort);
	//	sortBookPrice(book);

	//	for(int i = 0; i < book.Length; i++)
	//	{
	//		Console.WriteLine(book[i].ToString());
	//	}
	//}

}

public class Book : IComparable<Book>
{
	//定义构造方法为图书名称和价格赋值
	public Book(string name, double price)
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

