using System;

public class Program {
    // 购买商品委托
    public delegate void OrderDelegate();
  //  static void Main(string[] args) {
		//OrderDelegate order = new OrderDelegate(Order.OrderFastfood);
  //      //order += OrderFastfood;
  //      order += Order.OrderCake;
  //      order += Order.OrderFlowers;
		//order();
  //  }
}

public class Order {
    public static void OrderFastfood() {
        Console.WriteLine("预定快餐");
    }
    public static void OrderCake() {
        Console.WriteLine("预定蛋糕");
    }
    public static void OrderFlowers() {
        Console.WriteLine("预定鲜花");
    }
}