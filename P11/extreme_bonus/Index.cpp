#include "Index.h"

void Index::add_word(const Index::Word& word, const std::string& filename, int line) {
    _index.insert(std::make_pair(word, Location(filename, line)));
}
std::ostream& operator<<(std::ostream& ost, const Index& index) {
    Index::Word last_word = "";
    Location last_location("", 0);

    for (const auto& entry : index._index) {
        const Index::Word& current_word = entry.first;
        const Location& current_location = entry.second;

        if (current_word == last_word && current_location == last_location) {
            continue;
        }

        ost << current_word << ": " << current_location << "\n";
 
        last_word = current_word;
        last_location = current_location;
    }

    return ost;
}
