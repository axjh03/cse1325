// Location.h
#ifndef LOCATION_H
#define LOCATION_H

#include <iostream>
#include <string>

class Location {
private:
    std::string _filename;
    int _line;
    static std::string last_filename;

public:
    Location(const std::string& filename, int line);
    bool operator==(const Location& rhs) const;
    bool operator!=(const Location& rhs) const;
    bool operator<(const Location& rhs) const;
    bool operator>(const Location& rhs) const;
    bool operator<=(const Location& rhs) const;
    bool operator>=(const Location& rhs) const;
    friend std::ostream& operator<<(std::ostream& ost, const Location& location);

    // Static method to set last_filename to an empty string
    static void next_word();
};

#endif // LOCATION_H
