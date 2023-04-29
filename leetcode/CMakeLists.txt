cmake_minimum_required(VERSION 3.15)

# set the project name and version
project(Leetcode VERSION 1.0)


#====================================================================================
# 编译警告配置
#====================================================================================

# specify the C++ standard
add_library(Leetcode_compiler_flags INTERFACE)
target_compile_features(Leetcode_compiler_flags INTERFACE cxx_std_11)

add_executable(Leetcode 1971_isExistedPath.cpp)