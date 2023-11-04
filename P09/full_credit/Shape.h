#ifndef SHAPE_H
#define SHAPE_H

#include <string>

class Shape 
{
public: 
    virtual std::string name() const; // The virtual keyword allows the function to be overridden by a derived class.
    virtual double area() const;
    std::string to_string() const;
};

#endif 
