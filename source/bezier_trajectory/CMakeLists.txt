project(bezier_trajectory)

find_package(Eigen3 REQUIRED NO_MODULE)
find_package(Franka 0.6 REQUIRED)

add_library(trajectory_planner
  trajectory/curve_trajectory.cpp
  trajectory/linear_trajectory.cpp
  trajectory/peg_in_hole_trajectory.cpp
  trajectory/smooth_curve_trajectory.cpp

  trajectory_utility/smooth_motion_profile.cpp
  trajectory_utility/curve_smoothener.cpp
  trajectory_utility/trajectory_iterator.cpp

  gripper_pose.cpp
  kbhit.cpp

)
target_link_libraries(trajectory_planner Franka::Franka)
target_include_directories(trajectory_planner PUBLIC .)

add_executable(${PROJECT_NAME} main.cpp)
target_link_libraries(${PROJECT_NAME} trajectory_planner Franka::Franka)
target_include_directories(${PROJECT_NAME} PUBLIC trajectory)
