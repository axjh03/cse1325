#include "Time.h"
#include <iomanip>

Time::Time(int hour, int minute, int second)
    : _hour(hour), _minute(minute), _second(second)
{
    rationalize();
}

Time::Time()
    : _hour(0), _minute(0), _second(0) {}

Time Time::operator+(const Time &time) const
{
    int h = _hour + time._hour;
    int m = _minute + time._minute;
    int s = _second + time._second;

    Time result(h, m, s);
    result.rationalize();
    return result;
}

Time Time::operator+(int seconds) const
{
    int s = _second + seconds;
    int m = _minute;
    int h = _hour;

    if (s >= 60)
    {
        m += s / 60;
        s %= 60;
    }
    if (m >= 60)
    {
        h += m / 60;
        m %= 60;
    }

    Time result(h, m, s);
    result.rationalize();
    return result;
}

Time operator+(int seconds, Time &time)
{
    return time + seconds;
}

Time &Time::operator++()
{
    ++_second;
    rationalize();
    return *this;
}

Time Time::operator++(int)
{
    Time copy = *this;
    ++(*this);
    return copy;
}

bool Time::operator==(const Time &time) const
{
    return compare(time) == 0;
}

bool Time::operator!=(const Time &time) const
{
    return !(*this == time);
}

bool Time::operator<(const Time &time) const
{
    return compare(time) < 0;
}

bool Time::operator>(const Time &time) const
{
    return compare(time) > 0;
}

bool Time::operator<=(const Time &time) const
{
    return !(*this > time);
}

bool Time::operator>=(const Time &time) const
{
    return !(*this < time);
}

std::ostream &operator<<(std::ostream &os, const Time &time)
{
    os << std::setfill('0')
       << std::setw(2) << time._hour << ":"
       << std::setw(2) << time._minute << ":"
       << std::setw(2) << time._second;
    return os;
}

std::istream &operator>>(std::istream &is, Time &time)
{
    char colon1, colon2;
    is >> time._hour >> colon1 >> time._minute >> colon2 >> time._second;
    if (!is)
    {
        is.clear(std::ios::failbit);
    }
    return is;
}

void Time::rationalize()
{
    if (_second < 0)
    {
        _minute += (_second - 59) / 60;
        _second = 59 - ((_second + 1) % 60);
        if (_minute < 0)
        {
            _hour += (_minute + 1) / 60;
            _minute = 59 - ((_minute + 1) % 60);
            if (_hour < 0)
            {
                _hour += 24;
            }
        }
    }

    else
    {
        if (_second >= 60)
        {
            _minute += _second / 60;
            _second %= 60;
        }
        if (_minute >= 60)
        {
            _hour += _minute / 60;
            _minute %= 60;
        }
        if (_hour >= 24)
        {
            _hour %= 24;
        }
    }
}

int Time::compare(const Time &time) const
{
    if (_hour < time._hour)
        return -1;
    if (_hour > time._hour)
        return 1;
    if (_minute < time._minute)
        return -1;
    if (_minute > time._minute)
        return 1;
    if (_second < time._second)
        return -1;
    if (_second > time._second)
        return 1;
    return 0;
}