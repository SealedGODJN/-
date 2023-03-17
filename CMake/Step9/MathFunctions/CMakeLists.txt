add_executable(MakeTable MakeTable.cxx)

add_custom_command(
  OUTPUT ${CMAKE_CURRENT_BINARY_DIR}/Table.h
  COMMAND MakeTable ${CMAKE_CURRENT_BINARY_DIR}/Table.h
  DEPENDS MakeTable
  )

# add_library(MathFunctions MathFunctions.cxx)
add_library(MathFunctions
  MathFunctions.cxx
  ${CMAKE_CURRENT_BINARY_DIR}/Table.h
  )

# target_include_directories(MathFunctions
#     INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}
# )
target_include_directories(MathFunctions
  INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}
  PRIVATE   ${CMAKE_CURRENT_BINARY_DIR}
  )

target_link_libraries(MathFunctions tutorial_compiler_flags)

set(installable_libs MathFunctions tutorial_compiler_flags)
install(TARGETS ${installable_libs} DESTINATION lib)
install(FILES MathFunctions.h DESTINATION include)