#include "linear_trajectory.h"


LinearTrajectory::LinearTrajectory(const Eigen::Vector6d &from, const Eigen::Vector6d &to,
                                   double v_max, double a_max, double dt) {
  this->p_s = std::make_unique<LinearPath>(from, to);
  const double L = (to - from).head(3).norm();
  this->s_t = std::make_unique<MotionProfile>(L, v_max, a_max, dt);
}

Eigen::Matrix6dynd LinearTrajectory::p_t() const {
  const Eigen::VectorXd &s = s_t->getS();
  return p_s->at(s);
}

Eigen::Matrix6dynd LinearTrajectory::dp_dt() const {
  const Eigen::VectorXd &s = s_t->getS();
  const Eigen::VectorXd &ds_dt = s_t->getDsDt();
  return p_s->ds_at(s).cwiseProduct(Eigen::VectorXd::Ones(p_s->dim()) * ds_dt.transpose());
}

double LinearTrajectory::getDt() const { return this->s_t->getDt(); }

double LinearTrajectory::getTEnd() const { return this->s_t->getNumElements() * this->getDt(); }
