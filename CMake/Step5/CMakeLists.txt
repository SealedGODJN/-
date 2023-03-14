# 设置CMake最低版本
cmake_minimum_required(VERSION 3.15)

# 设置项目名称及版本
project(Tutorial VERSION 1.0)

set(gcc_like_cxx "$<COMPILE_LANG_AND_ID:CXX,ARMClang,AppleClang,Clang,GNU,LCC>")
set(msvc_cxx "$<COMPILE_LANG_AND_ID:CXX,MSVC>")

add_library(tutorial_compiler_flags INTERFACE)
target_compile_features(tutorial_compiler_flags INTERFACE cxx_std_11)

# target_compile_options(tutorial_compiler_flags INTERFACE
#   "$<${gcc_like_cxx}:-Wall;-Wextra;-Wshadow;-Wformat=2;-Wunused>"
#   "$<${msvc_cxx}:-W3>"
# )
# 只希望在构建期间使用这些警告标志
target_compile_options(tutorial_compiler_flags INTERFACE
  "$<${gcc_like_cxx}:$<BUILD_INTERFACE:-Wall;-Wextra;-Wshadow;-Wformat=2;-Wunused>>"
  "$<${msvc_cxx}:$<BUILD_INTERFACE:-W3>>"
)

# 生成可执行文件
add_executable(Tutorial tutorial.cxx)

# 设置选项
option(USE_MYMATH "use Turotial math" ON)

# 配置文件：拷贝文件到另一位置，并且修改其内容
configure_file(TutorialConfig.h.in TutorialConfig.h)

if(USE_MYMATH)
  add_subdirectory(MathFunctions)
  list(APPEND extra_libs MathFunctions)
endif()

# target_link_libraries(Tutorial PUBLIC ${extra_libs})
target_link_libraries(Tutorial PUBLIC ${extra_libs} tutorial_compiler_flags)

# 打印信息
message("PROJECT_BINARY_DIR=${PROJECT_BINARY_DIR}")

# 增加include路径到目标，否则会无法include生成的TutorialConfig.h
target_include_directories(Tutorial PUBLIC "${PROJECT_BINARY_DIR}")

install(TARGETS Tutorial DESTINATION bin)
install(FILES "${PROJECT_BINARY_DIR}/TutorialConfig.h"
  DESTINATION include
  )