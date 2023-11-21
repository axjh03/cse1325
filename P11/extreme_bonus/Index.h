#ifndef INDEX_H
#define INDEX_H

#include <iostream>
#include <string>
#include <map>
#include <set>
#include "Location.h"

class Index {
private:
    using Word = std::string;
    using Locations = std::multimap<Word, Location>;  

    Locations _index;

public:
    void add_word(const Word& word, const std::string& filename, int line);
    friend std::ostream& operator<<(std::ostream& ost, const Index& index);
};

#endif 
