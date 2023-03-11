# cmake_minimum_required(VERSION <min>[...<policy_max>] [FATAL_ERROR])
cmake_minimum_required(VERSION 3.26.0)

# project(<PROJECT-NAME> [<language-name>...])
# project(<PROJECT-NAME>
#     [VERSION <major>[.<minor>[.<patch>[.<tweak>]]]]
#     [DESCRIPTION <project-description-string>]
#     [HOMEPAGE_URL <url-string>]
#     [LANGUAGES <language-name>...])
project(helloworld VERSION 1.0.0 DESCRIPTION To achieve the event queue of simscript HOMEPAGE_URL https://gitee.com/sealedgodjn/helloworld/tree/C LANGUAGES C++)

# create an executable using the specified source code files
add_executable()
