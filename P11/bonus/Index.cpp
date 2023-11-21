#include "Index.h"

void Index::add_word(const Index::Word& word, const std::string& filename, int line) {
    Locations& locations = _index[word];
    locations.insert(Location(filename, line));
}

std::ostream& operator<<(std::ostream& ost, const Index& index) {
    for (const auto& entry : index._index) {
        ost << entry.first << ": ";
        for (const auto& location : entry.second) {
            ost << location << ", ";
        }
        ost << '\n';
    }
    return ost;
}
