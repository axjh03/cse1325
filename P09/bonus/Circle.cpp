#include "Circle.h"
#include <cmath>

const double PI = 3.14159; // This is a global constant.

// The constructor for the Circle class.
Circle::Circle(double radius) : _radius(radius) {} 
// this "_radius(radius)" is done by initialization list.
// it's equivalent to: this->_radius = radius;

std::string Circle::name() const {
    return " Circle of radius " + std::to_string(_radius);
}

double Circle::area() const {
    return PI * _radius * _radius; // PI * r^2
}
