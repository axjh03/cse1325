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

    static void next_word();
};

#endif 
