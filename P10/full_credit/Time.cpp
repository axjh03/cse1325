// Time.cpp
#include "Time.h"
#include <iomanip>
#include <cmath> 

Time::Time(int hour, int minute, int second) : _hour(hour), _minute(minute), _second(second) {
  rationalize(); 
}

void Time::rationalize() {
  if (_second < 0) {
    _minute -= 1; 
    _second += 60; 
  }
  
  _hour = (_hour + (_minute + (_second / 60)) / 60) % 24;
  _minute = (_minute + (_second / 60)) % 60;
  _second = _second % 60;
} 

int Time::getHour() const {
  return _hour;
}

int Time::getMinute() const {
  return _minute;  
}

int Time::getSecond() const {
  return _second;
}

Time Time::operator+(const Time& other) const {
  int hour = _hour + other._hour;
  int minute = _minute + other._minute;
  int second = _second + other._second;
  
  hour += minute/60;
  minute %= 60;
  
  minute += second/60;
  second %= 60;
  
  Time result(hour, minute, second);
  result.rationalize();
  return result;
}

Time& Time::operator++() {
  ++_second;
  rationalize();
  return *this;
}

Time Time::operator++(int) {
  Time temp = *this;
  ++*this;
  return temp;
}

bool Time::operator==(const Time& other) const {
  return (_hour == other._hour) && (_minute == other._minute) && (_second == other._second); 
}

bool Time::operator!=(const Time& other) const {
  return !(*this == other);
}

bool Time::operator<(const Time& other) const {
  if (_hour != other._hour) return _hour < other._hour;
  if (_minute != other._minute) return _minute < other._minute;
  return _second < other._second;
}

bool Time::operator>(const Time& other) const {
  return other < *this;
}

bool Time::operator<=(const Time& other) const {
  return !(*this > other);
}

bool Time::operator>=(const Time& other) const {
  return !(*this < other);
}

std::ostream& operator<<(std::ostream& os, const Time& time) {
  os << std::setfill('0') << std::setw(2) << time._hour << ":"
     << std::setfill('0') << std::setw(2) << time._minute << ":"
     << std::setfill('0') << std::setw(2) << time._second;
  return os;
}

std::istream& operator>>(std::istream& is, Time& time) {
  char colon;
  is >> time._hour >> colon >> time._minute >> colon >> time._second;
  if (!is.fail() && colon == ':') {
    time.rationalize();
  } else {
    is.setstate(std::ios::failbit); 
  }
  return is;
}