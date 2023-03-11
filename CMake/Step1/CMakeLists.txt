# [TODO 1]
cmake_minimum_required(VERSION 3.10)

# 设置项目名称[TODO 2]
# project(Tutorial)
# 设置项目名称和版本[TODO 7]
project(Tutorial VERSION 1.0)

# Make sure to add the CMAKE_CXX_STANDARD declarations above the call to add_executable()[TODO 6]
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED True)

# 添加可执行文件[TODO 3]
add_executable(Tutorial tutorial.cxx)

# [TODO 8]
configure_file(TutorialConfig.h.in TutorialConfig.h)

target_include_directories(Tutorial PUBLIC
    "${PROJECT_BINARY_DIR}"
    )