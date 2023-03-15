# C++和C的学习汇总

## 2020-6 斐波那契堆

基于斐波那契堆实现事件队列（仿制simscript）

## 2020-11 网络信息安全

### 实验2


### 实验3


## 2023-3 CMake学习

### Step1

1、添加可执行文件

2、设置c++标准

3、通过.in的方式配置头文件和版本

### Step2

1、创建一个library并引入

2、让library可选（通过cmake -DUSE_MATH=ON/OFF）

### Step3

1、设置library的访问权限【INTERFACE关键字】

### Step4

1、学习使用generator Expression

2、设置c++标准和设置编译选项

### Step5

1、install：学习如何自动化安装bin、lib、include

2、test：学习配置自动化cmake测试

```shell
UpdateCTestConfiguration  from :C:/code/helloworld-c/CMake/Step5/DartConfiguration.tcl
UpdateCTestConfiguration  from :C:/code/helloworld-c/CMake/Step5/DartConfiguration.tcl
Test project C:/code/helloworld-c/CMake/Step5
Constructing a list of tests
Done constructing a list of tests
Updating test list for fixtures
Added 0 tests to meet fixture requirements
Checking test dependency graph...
Checking test dependency graph end
test 1
      Start  1: Runs

1: Test command: C:\code\helloworld-c\CMake\Step5\Tutorial.exe "25"
1: Working Directory: C:/code/helloworld-c/CMake/Step5
1: Test timeout computed to be: 10000000
1: Computing sqrt of 25 to be 13
1: Computing sqrt of 25 to be 7.46154
1: Computing sqrt of 25 to be 5.40603
1: Computing sqrt of 25 to be 5.01525
1: Computing sqrt of 25 to be 5.00002
1: Computing sqrt of 25 to be 5
1: Computing sqrt of 25 to be 5
1: Computing sqrt of 25 to be 5
1: Computing sqrt of 25 to be 5
1: Computing sqrt of 25 to be 5
1: The square root of 25 is 5
 1/10 Test  #1: Runs .............................   Passed    0.08 sec
test 2
      Start  2: Usage

2: Test command: C:\code\helloworld-c\CMake\Step5\Tutorial.exe
2: Working Directory: C:/code/helloworld-c/CMake/Step5
2: Test timeout computed to be: 10000000
2: C:/code/helloworld-c/CMake/Step5/Tutorial.exe Version 1.0
2: Usage: C:/code/helloworld-c/CMake/Step5/Tutorial.exe number
 2/10 Test  #2: Usage ............................   Passed    0.01 sec
test 3
      Start  3: StandardUse

3: Test command: C:\code\helloworld-c\CMake\Step5\Tutorial.exe "4"
3: Working Directory: C:/code/helloworld-c/CMake/Step5
3: Test timeout computed to be: 10000000
3: Computing sqrt of 4 to be 2.5
3: Computing sqrt of 4 to be 2.05
3: Computing sqrt of 4 to be 2.00061
3: Computing sqrt of 4 to be 2
3: Computing sqrt of 4 to be 2
3: Computing sqrt of 4 to be 2
3: Computing sqrt of 4 to be 2
3: Computing sqrt of 4 to be 2
3: Computing sqrt of 4 to be 2
3: Computing sqrt of 4 to be 2
3: The square root of 4 is 2
 3/10 Test  #3: StandardUse ......................   Passed    0.01 sec
test 4
      Start  4: Comp4

4: Test command: C:\code\helloworld-c\CMake\Step5\Tutorial.exe "4"
4: Working Directory: C:/code/helloworld-c/CMake/Step5
4: Test timeout computed to be: 10000000
4: Computing sqrt of 4 to be 2.5
4: Computing sqrt of 4 to be 2.05
4: Computing sqrt of 4 to be 2.00061
4: Computing sqrt of 4 to be 2
4: Computing sqrt of 4 to be 2
4: Computing sqrt of 4 to be 2
4: Computing sqrt of 4 to be 2
4: Computing sqrt of 4 to be 2
4: Computing sqrt of 4 to be 2
4: Computing sqrt of 4 to be 2
4: The square root of 4 is 2
 4/10 Test  #4: Comp4 ............................   Passed    0.01 sec
test 5
      Start  5: Comp9

5: Test command: C:\code\helloworld-c\CMake\Step5\Tutorial.exe "9"
5: Working Directory: C:/code/helloworld-c/CMake/Step5
5: Test timeout computed to be: 10000000
5: Computing sqrt of 9 to be 5
5: Computing sqrt of 9 to be 3.4
5: Computing sqrt of 9 to be 3.02353
5: Computing sqrt of 9 to be 3.00009
5: Computing sqrt of 9 to be 3
5: Computing sqrt of 9 to be 3
5: Computing sqrt of 9 to be 3
5: Computing sqrt of 9 to be 3
5: Computing sqrt of 9 to be 3
5: Computing sqrt of 9 to be 3
5: The square root of 9 is 3
 5/10 Test  #5: Comp9 ............................   Passed    0.01 sec
test 6
      Start  6: Comp5

6: Test command: C:\code\helloworld-c\CMake\Step5\Tutorial.exe "5"
6: Working Directory: C:/code/helloworld-c/CMake/Step5
6: Test timeout computed to be: 10000000
6: Computing sqrt of 5 to be 3
6: Computing sqrt of 5 to be 2.33333
6: Computing sqrt of 5 to be 2.2381
6: Computing sqrt of 5 to be 2.23607
6: Computing sqrt of 5 to be 2.23607
6: Computing sqrt of 5 to be 2.23607
6: Computing sqrt of 5 to be 2.23607
6: Computing sqrt of 5 to be 2.23607
6: Computing sqrt of 5 to be 2.23607
6: Computing sqrt of 5 to be 2.23607
6: The square root of 5 is 2.23607
 6/10 Test  #6: Comp5 ............................   Passed    0.01 sec
test 7
      Start  7: Comp7

7: Test command: C:\code\helloworld-c\CMake\Step5\Tutorial.exe "7"
7: Working Directory: C:/code/helloworld-c/CMake/Step5
7: Test timeout computed to be: 10000000
7: Computing sqrt of 7 to be 4
7: Computing sqrt of 7 to be 2.875
7: Computing sqrt of 7 to be 2.65489
7: Computing sqrt of 7 to be 2.64577
7: Computing sqrt of 7 to be 2.64575
7: Computing sqrt of 7 to be 2.64575
7: Computing sqrt of 7 to be 2.64575
7: Computing sqrt of 7 to be 2.64575
7: Computing sqrt of 7 to be 2.64575
7: Computing sqrt of 7 to be 2.64575
7: The square root of 7 is 2.64575
 7/10 Test  #7: Comp7 ............................   Passed    0.01 sec
test 8
      Start  8: Comp25

8: Test command: C:\code\helloworld-c\CMake\Step5\Tutorial.exe "25"
8: Working Directory: C:/code/helloworld-c/CMake/Step5
8: Test timeout computed to be: 10000000
8: Computing sqrt of 25 to be 13
8: Computing sqrt of 25 to be 7.46154
8: Computing sqrt of 25 to be 5.40603
8: Computing sqrt of 25 to be 5.01525
10: Computing sqrt of 0.0001 to be 0.0101202
10: Computing sqrt of 0.0001 to be 0.0100007
10: Computing sqrt of 0.0001 to be 0.01
10: The square root of 0.0001 is 0.01
10/10 Test #10: Comp0.0001 .......................   Passed    0.01 sec

100% tests passed, 0 tests failed out of 10

Total Test time (real) =   0.21 sec

```
