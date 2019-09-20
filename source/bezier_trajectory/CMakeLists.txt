project(bezier_trajectory)

find_package(Eigen3 REQUIRED NO_MODULE)
find_package(Franka 0.6 REQUIRED)

add_library(trajectory_planner
  trajectory/curve_trajectory.cpp
  trajectory/linear_trajectory.cpp
  trajectory/peg_in_hole_trajectory.cpp
  trajectory/smooth_curve_trajectory.cpp

  utility/smooth_motion_profile.cpp
  utility/curve_smoothener.cpp
  utility/trajectory_iterator.cpp
  utility/trajectory_iterator_velocity.cpp
  utility/gripper_pose.cpp
  utility/kbhit.cpp

)
target_link_libraries(trajectory_planner Franka::Franka)
target_include_directories(trajectory_planner PUBLIC .)

add_executable(${PROJECT_NAME} main.cpp)
target_link_libraries(${PROJECT_NAME} trajectory_planner Franka::Franka)
target_include_directories(${PROJECT_NAME} PUBLIC trajectory)


add_executable(peg_in_hole main_bezier.cpp)
target_link_libraries(peg_in_hole trajectory_planner Franka::Franka)
target_include_directories(peg_in_hole PUBLIC trajectory)
