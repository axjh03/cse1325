// Inside Time.h
#ifndef TIME_H
#define TIME_H
#include <iostream>

class Time {
private:
    int _hour;
    int _minute;
    int _second;

public:
    // Constructors
    Time(int hour, int minute, int second);

    // Default constructor
    Time() : _hour(0), _minute(0), _second(0) {}

    // Getters
    int getHour() const;
    int getMinute() const;
    int getSecond() const;

    // Math operators
    Time operator+(const Time& other) const;
    Time& operator++(); // pre-increment
    Time operator++(int); // post-increment

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

    // Optional method
    int compare(const Time& other) const; // <<optional>>

private:
    // Helper method
    void rationalize();
};

#endif // TIME_H
