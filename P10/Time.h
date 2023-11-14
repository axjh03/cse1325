#ifndef TIME_H
#define TIME_H

#include <iostream>

class Time {
public:
  Time(int hour, int minute, int second);
  Time();

  Time operator+(const Time& time) const;
  Time operator+(int seconds) const;
  friend Time operator+(int seconds, Time& time);

  Time& operator++();
  Time operator++(int);

  bool operator==(const Time& time) const;
  bool operator!=(const Time& time) const;
  bool operator<(const Time& time) const;
  bool operator>(const Time& time) const;
  bool operator<=(const Time& time) const;
  bool operator>=(const Time& time) const;

  friend std::ostream& operator<<(std::ostream& os, const Time& time);
  friend std::istream& operator>>(std::istream& is, Time& time);

private:
  int _hour;
  int _minute;
  int _second;

  void rationalize();
  int compare(const Time& time) const;  
};

#endif