#include <array>

#include <eigen3/Eigen/Eigen>

namespace Eigen {

/// 6d vector representation (used for [x,y,z,roll_x,pitch_y,yaw_z])
typedef Matrix<double, 6, 1> Vector6d;

/// Matrix with 6 rows stored colwise (fast colwise access)
typedef Matrix<double, 6, Eigen::Dynamic, Eigen::ColMajor> Matrix6dynd;

} // namespace Eigen


/** \brief convert Franka's homogeneous transformation (std::array) to a 6D pose with [x,y,z,
 * roll,pitch,yaw].
 *
 * \arg[in] 16 dimensional array (flattened homogeneous transformation)
 * \returns 6d pose vector with 3 cartesian positions and 3 rpy rotations (r_x, p_y, y_z)
 *
 * \warning The rotations are constrained to get a reproducible construction, i.e.
 *          roll_x \in [0,π], pitch_y \in [-π, π], yaw_z \in [-π, π].
 *
 * \todo change rotations to less constrained ones
 */
Eigen::Vector6d homogeneousTfArray2PoseVec(const std::array<double, 16> &pose_TF_as_array);

/** \brief convert a 6D pose with [x,y,z, roll,pitch,yaw] to Franka's homogeneous transformation
 * (std::array)
 *
 * \arg[in] pose the 6 d pose (3 translations, 3 rpy rotations)
 * \returns 16 dimensional array (flattened homogeneous transformation)
 *
 * \warning The rotations are constrained to get a reproducible construction, i.e.
 *          roll_x \in [0,π], pitch_y \in [-π, π], yaw_z \in [-π, π].
 *
 * \todo change rotations to less constrained ones
 */
std::array<double, 16> poseVec2HomogeneousTfArray(const Eigen::Vector6d &pose);

