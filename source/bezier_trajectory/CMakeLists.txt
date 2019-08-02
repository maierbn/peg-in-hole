project(bezier_trajectory)

find_package(Eigen3 REQUIRED NO_MODULE)
find_package(Franka 0.6 REQUIRED)

add_library(trajectory_planner
  trajectory/trajectory.cpp
  trajectory/linear_trajectory.cpp
  trajectory/curve_trajectory.cpp
  trajectory_iterator.cpp
  motion_profile.cpp
  eigen_utility.cpp
  kbhit.cpp
  path.cpp
)
target_link_libraries(trajectory_planner Franka::Franka)
target_include_directories(trajectory_planner PUBLIC trajectory)

add_executable(${PROJECT_NAME} main.cpp)
target_link_libraries(${PROJECT_NAME} trajectory_planner Franka::Franka)
