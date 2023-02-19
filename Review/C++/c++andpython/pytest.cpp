// pytest.cpp : 定义控制台应用程序的入口点。

// #include "stdafx.h"

#include "Python.h"

int main(int argc, char* argv[])
{
    //初始化python

    Py_Initialize();

    //定义python类型的变量

    PyObject *pModule = NULL;

    PyObject *pFunc = NULL;

    PyObject *pArg = NULL;

    PyObject *result = NULL;

    PyObject *pClass = NULL;

    PyObject *pInstance = NULL;

    PyObject *pDict = NULL;

    //直接运行python代码
    PyRun_SimpleString("print 'python start'");

    //引入模块
    pModule = PyImport_ImportModule("test_code");

    //获取模块字典属性
    pDict = PyModule_GetDict(pModule);

    //直接获取模块中的函数
    pFunc = PyObject_GetAttrString(pModule, "hello");

    //参数类型转换，传递一个字符串。将c/c++类型的字符串转换为python类型，元组中的python类型查看python文档
    pArg = Py_BuildValue("(s)", "hello charity");

    //调用直接获得的函数，并传递参数
    PyEval_CallObject(pFunc, pArg);

    //从字典属性中获取函数
    pFunc = PyDict_GetItemString(pDict, "arg");

    //参数类型转换，传递两个整型参数
    pArg = Py_BuildValue("(i, i)", 1, 2);

    //调用函数，并得到python类型的返回值
    result = PyEval_CallObject(pFunc, pArg);

    //c用来保存c/c++类型的返回值
    int c;

    //将python类型的返回值转换为c/c++类型
    PyArg_Parse(result, "i", &c);

    //输出返回值
    printf("a+b=%d\n", c);

    //通过字典属性获取模块中的类
    pClass = PyDict_GetItemString(pDict, "Test");

    //实例化获取的类
    pInstance = PyInstance_New(pClass, NULL, NULL);

    //调用类的方法
    result = PyObject_CallMethod(pInstance, "say_hello", "(s)", "charity");

    //输出返回值
    char* name=NULL;

    PyArg_Parse(result, "s", &name);   //这个函数的第二个参数相当扯淡，具体看下文的英文，类型使用字符来表示的，例如“s”代表

                                                            //str "i" 代表int，个人感觉相当扯淡

    printf("%s\n", name);

    PyRun_SimpleString("print 'python end'");

    //释放python
    Py_Finalize();

    getchar();

    return 0;

}