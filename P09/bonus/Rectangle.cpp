#include "Rectangle.h"

// The constructor for the Rectangle class.
Rectangle::Rectangle(double width, double height) : _width(width), _height(height) {}

// The name() and area() functions are overridden from the Shape class.
std::string Rectangle::name() const {
    return std::to_string(_height) + "x" + std::to_string(_width) + " Rectangle";
}

double Rectangle::area() const {
    return _width * _height;
}
