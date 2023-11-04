#ifndef RECTANGLE_H
#define RECTANGLE_H

#include "Shape.h"

class Rectangle : public Shape // This is equivalent to "public class Rectangle extends Shape" in Java.
{
private:
    double _width;
    double _height;

public:
    Rectangle(double width, double height);
    std::string name() const override;
    double area() const override;
};

#endif 
