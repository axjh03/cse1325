#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"
#include <iostream>
#include <vector>

int main() {
    std::vector<Shape*> shapes;

    // Instantiate objects of Rectangle and Circle and add them to the vector
    Rectangle rectangle(5.0, 3.0);
    Circle circle(4.0);

    // Add the addresses of the objects to the vector
    shapes.push_back(&rectangle);
    shapes.push_back(&circle);

    // Iterate over the vector, calling to_string for each shape
    for (const auto& shape : shapes) {
        std::cout << shape->to_string() << std::endl;
    }

    // Clean up memory if needed for heap-allocated objects
    return 0;
}
