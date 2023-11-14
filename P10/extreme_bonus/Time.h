#ifndef TIME_H
#define TIME_H

#include <iostream>

class Time {
public:
    // Constructors
    Time(int hour, int minute, int second);
    Time(); // Default constructor
    Time(int hour, int minute); // New constructor without default values

    // Getters
    int getHour() const;
    int getMinute() const;
    int getSecond() const;

    // Math operators
    Time operator+(const Time& other) const;
    Time operator+(int seconds) const; // New operator for adding seconds
    friend Time operator+(int seconds, const Time& time); // Friend function for adding seconds

    // Increment operators
    Time& operator++();
    Time operator++(int);

    // Comparison operators
    bool operator==(const Time& other) const;
    bool operator!=(const Time& other) const;
    bool operator<(const Time& other) const;
    bool operator>(const Time& other) const;
    bool operator<=(const Time& other) const;
    bool operator>=(const Time& other) const;

    // Streaming operators
    friend std::ostream& operator<<(std::ostream& os, const Time& time);
    friend std::istream& operator>>(std::istream& is, Time& time);

    // Subscript operator
    int operator[](int index) const;

private:
    int _hour;
    int _minute;
    int _second;

    void rationalize();
};

#endif
