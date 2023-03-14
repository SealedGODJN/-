add_library(MathFunctions MathFunctions.cxx)

# TODO2: INTERFACE-使用者需要该目录，但提供者不需要该目录
target_include_directories(MathFunctions
    INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}
)

target_link_libraries(MathFunctions tutorial_compiler_flags)