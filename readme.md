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

```powershell
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

### Step6

### Step7

### Step8

### Step9

### Step10

1、替换CMakeLists.txt和Mathfunctions.txt

2、修改MathFunctions.h和tutorial.cxx

3、cmake --build . 出现warning

```powershell
(base) C:\code\helloworld-c>cd CMake

(base) C:\code\helloworld-c\CMake>cd Step10

(base) C:\code\helloworld-c\CMake\Step10>cmake -G "MinGW Makefiles"
CMake Warning:
  No source or binary directory provided.  Both will be assumed to be the
  same as the current working directory, but note that this warning will
  become a fatal error in future CMake releases.


-- The C compiler identification is GNU 12.2.0
-- The CXX compiler identification is GNU 12.2.0
-- Detecting C compiler ABI info
-- Detecting C compiler ABI info - done
-- Check for working C compiler: C:/msys64/mingw64/bin/cc.exe - skipped
-- Detecting C compile features
-- Detecting C compile features - done
-- Detecting CXX compiler ABI info
-- Detecting CXX compiler ABI info - done
-- Check for working CXX compiler: C:/msys64/mingw64/bin/c++.exe - skipped
-- Detecting CXX compile features
-- Detecting CXX compile features - done
-- Configuring done (1.7s)
-- Generating done (0.1s)
-- Build files have been written to: C:/code/helloworld-c/CMake/Step10

(base) C:\code\helloworld-c\CMake\Step10>cmake --build .
[ 11%] Building CXX object MathFunctions/CMakeFiles/MakeTable.dir/MakeTable.cxx.obj
[ 22%] Linking CXX executable ..\MakeTable.exe
[ 22%] Built target MakeTable
[ 33%] Generating Table.h
[ 44%] Building CXX object MathFunctions/CMakeFiles/SqrtLibrary.dir/MathFunctions.cxx.obj
C:\code\helloworld-c\CMake\Step10\MathFunctions\MathFunctions.cxx:12:10: warning: 'double mathfunctions::mysqrt(double)' redeclared without dllimport attribute: previous dllimport ignored [-Wattributes]
   12 |   double mysqrt(double x)
      |          ^~~~~~
[ 55%] Linking CXX static library ..\libSqrtLibrary.a
[ 55%] Built target SqrtLibrary
[ 66%] Building CXX object MathFunctions/CMakeFiles/MathFunctions.dir/MathFunctions.cxx.obj
[ 77%] Linking CXX shared library ..\libMathFunctions.dll
[ 77%] Built target MathFunctions
[ 88%] Building CXX object CMakeFiles/Tutorial.dir/tutorial.cxx.obj
[100%] Linking CXX executable Tutorial.exe
[100%] Built target Tutorial

Computing sqrt of 123245 to be 15408.2
Computing sqrt of 123245 to be 7708.12
Computing sqrt of 123245 to be 3862.06
Computing sqrt of 123245 to be 1946.98
Computing sqrt of 123245 to be 1005.14
Computing sqrt of 123245 to be 563.878
Computing sqrt of 123245 to be 391.223
Computing sqrt of 123245 to be 353.124
The square root of 123245 is 353.124

(base) C:\code\helloworld-c\CMake\Step10>cmake --build . 
[ 22%] Built target MakeTable
[ 55%] Built target SqrtLibrary
[ 77%] Built target MathFunctions
[100%] Built target Tutorial

```

4、解决方法：

set the [`POSITION_INDEPENDENT_CODE`](https://cmake.org/cmake/help/latest/prop_tgt/POSITION_INDEPENDENT_CODE.html#prop_tgt:POSITION_INDEPENDENT_CODE "POSITION_INDEPENDENT_CODE") target property of SqrtLibrary to be `True` when building shared libraries.

**MathFunctions/CMakeLists.txt**

```cmake
# state that SqrtLibrary need PIC when the default is shared libraries
set_target_properties(SqrtLibraryPROPERTIES
POSITION_INDEPENDENT_CODE${BUILD_SHARED_LIBS}
)
```

### Step11

1、修改MathFunctions中的CMakeLists.txt关于install的内容：


```cmake
install(TARGETS${installable_libs}
EXPORTMathFunctionsTargets
DESTINATIONlib)
```


问题：

```powershell
(base) C:\code\helloworld-c>cd CMake

(base) C:\code\helloworld-c\CMake>cd Step11 

(base) C:\code\helloworld-c\CMake\Step11>cmake -G "MinGW Makefiles" .
-- The C compiler identification is GNU 12.2.0
-- The CXX compiler identification is GNU 12.2.0
-- Detecting C compiler ABI info
-- Detecting C compiler ABI info - done
-- Check for working C compiler: C:/msys64/mingw64/bin/cc.exe - skipped
-- Detecting C compile features
-- Detecting C compile features - done
-- Detecting CXX compiler ABI info
-- Detecting CXX compiler ABI info - done
-- Check for working CXX compiler: C:/msys64/mingw64/bin/c++.exe - skipped
-- Detecting CXX compile features
-- Detecting CXX compile features - done
-- Configuring done (4.9s)
CMake Error in MathFunctions/CMakeLists.txt:
  Target "MathFunctions" INTERFACE_INCLUDE_DIRECTORIES property contains
  path:

    "C:/code/helloworld-c/CMake/Step11/MathFunctions"

  which is prefixed in the build directory.


-- Generating done (0.1s)
CMake Generate step failed.  Build files cannot be regenerated correctly.
```


2、分析：

What CMake is trying to say is that during generating the export information it will export a path that is intrinsically tied to the current machine and will not be valid on other machines. The solution to this is to update the `MathFunctions `[`target_include_directories()`](https://cmake.org/cmake/help/latest/command/target_include_directories.html#command:target_include_directories "target_include_directories")to understand that it needs different `INTERFACE `locations when being used from within the build directory and from an install / package.

3、解决方法：

**MathFunctions/CMakeLists.txt**

```cmake
target_include_directories(MathFunctions
INTERFACE
$<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}>
$<INSTALL_INTERFACE:include>
)
```
