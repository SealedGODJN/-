package com.NPU.面向对象设计.chapter4;


/**
 * 建模雇员的类
 *
 * @author author name
 * @version 1.0.0
 */
public class Employee extends Person {

    /* 雇员薪水 */
    private double salary;

    /**
     * 构造函数
     *
     * @param initialName    初始化雇员的名字
     * @param initialAddress 初始化雇员的地址
     * @param initialSalary  初始化雇员的薪水
     */
    public Employee(String initialName, String initialAddress,
                    double initialSalary) {

        super(initialName, initialAddress);
        salary = initialSalary;
    }

    /**
     * 返回雇员的薪水
     *
     * @return 雇员薪水
     */
    public double getSalary() {

        return salary;
    }

    /**
     * 更新雇员的薪水
     *
     * @param newSalary 更新的薪水
     */
    public void setSalary(double newSalary) {

        salary = newSalary;
    }

    /**
     * 比较两个雇员对象是否相等，重写Object中的equals方法
     *
     * @param o 比较对象
     * @return ture 或false
     */
    public boolean equals(Object o) {

        if (o instanceof Employee) {
            Employee e = (Employee) o;
            return this.getName().equals(e.getName())
                    && this.getAddress().equals(e.getAddress())
                    && this.getSalary() == e.getSalary();
        } else {
            return false;
        }
    }

//    /**
//     * 返回代表雇员的属性信息的字符串
//     * @return 代表雇员的属性信息的字符串
//     */
//    public String toString() {
//        return "name：  "+ getName() + "_address：  " + getAddress() + "_salary：  " + salary;
//    }

    /**
     * 返回代表雇员的属性信息的字符串
     *
     * @return 代表雇员的属性信息的字符串
     */
    public String toString() {
        return super.toString() + "_salary：  " + salary;
    }

    public static void main(String[] args) {
//        Employee  employee = new Employee("Joe", "100 Ave", 3.0);
//        Person  person =  employee;
//        String name = person.getName(); //合法
//        String address = person.getAddress();   //合法
////        double salary = person.getSalary(); //非法，即编译器报错
//        double salary = ((Employee)person).getSalary(); //合法

//        Person person = new Person ("Joe ", "10 Main Ave");
//        double salary = ((Employee) person).getSalary();//该句将抛出ClassCastException类型的异常

        // 示例4.14  向下强制类型转换与instanceof操作符结合使用
        Person person = new Employee("Joe Smith", "100 Main Ave", 1);
//                ……
        if (person instanceof Employee) {
            double salary = ((Employee) person).getSalary();
        }

        // 示例4.17 Object中equals方法功能的验证
        Employee employee1 = new Employee("xiao", "nwpu", 200);
        Employee employee2 = new Employee("xiao", "nwpu", 200);
        Employee employee3 = employee1;
        //同一个Employee对象的比较
        if (employee1.equals(employee3)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        //内容相同的不同Employee对象的比较
        if (employee1.equals(employee2)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        //同一个Employee对象的比较
        if (employee1 == employee3) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        //内容相同的不同Employee对象的比较
        if (employee1 == employee2) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        person = new Person("xiao", "nwpu");
//4：employee对象和Person对象的比较，person指向的是一个Person对象
        if (employee1.equals(person)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
