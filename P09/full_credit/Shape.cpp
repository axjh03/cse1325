#include "Shape.h"

std::string Shape::name() const {
    return "Shape";
}

double Shape::area() const {
    return 0.0;
}

std::string Shape::to_string() const {
    return name() + " with area " + std::to_string(area());
}
